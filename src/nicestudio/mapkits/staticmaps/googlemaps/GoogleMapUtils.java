package nicestudio.mapkits.staticmaps.googlemaps;

import java.util.ArrayList;
import java.util.List;

import nicestudio.mapkits.directions.LocationCoordinate2D;

public class GoogleMapUtils {

	/**
	 * 建立 Google Map 位置的表示方法。
	 * 
	 * @param location
	 *            位置物件。
	 * @return Google Map 位置的表示方法。
	 */
	public static String createLocationQueryString(LocationCoordinate2D location) {
		assert location != null;
		
		return String.format("%f,%f", location.getLatitude(),
				location.getLongitude());
	}
	
	public static List<String> createLocationQueryStringList(LocationCoordinate2D... locations) {
		List<String> results = new ArrayList<String>();
		for (LocationCoordinate2D location : locations) {
			results.add(createLocationQueryString(location));
		}
		return results;
	}

}
