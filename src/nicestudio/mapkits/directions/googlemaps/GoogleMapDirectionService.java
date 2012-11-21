package nicestudio.mapkits.directions.googlemaps;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nicestudio.mapkits.directions.DirectionServiceDelegate;
import nicestudio.mapkits.directions.LocationCoordinate2D;
import nicestudio.mapkits.directions.models.DirectionResult;
import nicestudio.mapkits.directions.models.DirectionTravelMode;
import nicestudio.mapkits.staticmaps.googlemaps.GoogleMapUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GoogleMapDirectionService<E extends LocationCoordinate2D>
		implements DirectionServiceDelegate<E> {

	private static final Map<String, String> DEFAULT_PARAMS = new HashMap<String, String>();

	static {
		DEFAULT_PARAMS.put("unit", "metric");
		DEFAULT_PARAMS.put("sensor", "false");
		DEFAULT_PARAMS.put("mode", DirectionTravelMode.DRIVING.toString());
		DEFAULT_PARAMS.put("language", "zh-TW");
	}

	public URIBuilder createURIBuilder(List<E> locations,
			Map<String, String> params) throws Exception {

		assert locations != null && locations.size() >= 2; // 因為包含開始與結束的位置

		if (params == null || params.size() == 0) {
			params = DEFAULT_PARAMS;
		}

		LocationCoordinate2D startLocation = locations.get(0);

		int size = locations.size();
		LocationCoordinate2D endLocation = locations.get(size - 1);

		URIBuilder builder = new URIBuilder();

		builder.setScheme("http").setHost("maps.googleapis.com")
				.setPath("/maps/api/directions/json");

		builder.addParameter("destination",
				GoogleMapUtils.createLocationQueryString(endLocation))
				.addParameter("origin",
						GoogleMapUtils.createLocationQueryString(startLocation));

		for (String key : params.keySet()) {
			String value = params.get(key);
			builder.addParameter(key, value);
		}

		List<String> waypoints = new ArrayList<String>();
		if (size > 2) {
			// 表示有中間的位置!!
			size = size - 1;
			for (int i = 1; i < size; i++) {
				LocationCoordinate2D location = locations.get(i);
				waypoints.add(GoogleMapUtils
						.createLocationQueryString(location));
			}

			builder.addParameter("waypoints", StringUtils.join(waypoints, '|'));
		}

		return builder;
	}

	@Override
	public DirectionResult getDirectionResult(List<E> locations,
			DirectionTravelMode mode, boolean sensor) throws Exception {

		URIBuilder builder = createURIBuilder(locations, null);
		URI uri = builder.build();

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() >= 400) {
			// TODO: Error!!
		}

		if (response.getEntity().getContentLength() == 0) {
			// TODO: Error!!
		}

		String responseContent = EntityUtils.toString(response.getEntity());

		return GoogleDirectionParser.parse(responseContent);
	}

}
