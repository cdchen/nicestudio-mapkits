package nicestudio.mapkits.staticmaps.googlemaps;

import java.util.List;

import nicestudio.mapkits.directions.LocationCoordinate2D;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

public class GoogleStaticMapURIBuilder<L extends LocationCoordinate2D> {

	public static class InvalidValueException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6292327175414532665L;

		public InvalidValueException() {
			super();
		}

		public InvalidValueException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}

		public InvalidValueException(String arg0) {
			super(arg0);
		}

		public InvalidValueException(Throwable arg0) {
			super(arg0);
		}

	};

	public static enum MapFormat {
		png8, png32, gif, jpg, jpg_baseline;
	};

	public static enum MapType {
		roadmap, satellite, terrain, hybrid;
	};

	public static final int SIZE_WIDTH_MAX = 640;

	public static final int SIZE_HEIGHT_MAX = 640;

	public static final int ZOOM_MIN = 0;

	public static final int ZOOM_MAX = 21;

	private URIBuilder builder = new URIBuilder();

	private MarkerStyle defaultMarkerStyle = new MarkerStyle();

	private PathStyle defaultPathStyle = new PathStyle();

	public GoogleStaticMapURIBuilder() {
		builder.setScheme("http").setHost("maps.google.com")
				.setPath("/maps/api/staticmap");

	}

	public GoogleStaticMapURIBuilder(MarkerStyle defaultMarkerStyle,
			PathStyle defaultPathStyle) {
		this();

		this.defaultMarkerStyle = defaultMarkerStyle;
		this.defaultPathStyle = defaultPathStyle;
	}

	public GoogleStaticMapURIBuilder<L> setSize(int width, int height) {
		if (width < 0 || width > SIZE_WIDTH_MAX) {
			throw new InvalidValueException();
		}

		if (height < 0 || height > SIZE_HEIGHT_MAX) {
			throw new InvalidValueException();
		}

		builder.addParameter("size", String.format("%dx%d", width, height));
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setCenter(L center) {
		builder.setParameter("center",
				GoogleMapUtils.createLocationQueryString(center));
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setZoom(int zoom) {
		if (zoom < ZOOM_MIN || zoom > ZOOM_MAX) {
			throw new InvalidValueException();
		}

		builder.setParameter("zoom", String.valueOf(zoom));
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setMapType(MapType mapType) {
		builder.setParameter("maptype", mapType.toString());
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setFormat(MapFormat format) {
		builder.setParameter("format", format.toString());
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setLanguage(String language) {
		builder.setParameter("language", language);
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setSensor(boolean sensor) {
		builder.setParameter("sensor", String.valueOf(sensor));
		return this;
	}

	public GoogleStaticMapURIBuilder<L> setVisibles(L... locations) {
		builder.setParameter("visible", StringUtils.join(
				GoogleMapUtils.createLocationQueryStringList(locations), '|'));
		return this;
	}

	private StringBuffer createMarkerBuffer(MarkerStyle markerStyle,
			L... markers) {
		StringBuffer buf = new StringBuffer();
		String style = markerStyle.getStyleString();
		if (StringUtils.isEmpty(style) == false) {
			buf.append(style);
			buf.append('|');
		}
		if (markers.length > 1) {
			List<String> querystrings = GoogleMapUtils
					.createLocationQueryStringList(markers);
			buf.append(StringUtils.join(querystrings, '|'));
		} else {
			buf.append(GoogleMapUtils.createLocationQueryString(markers[0]));
		}
		return buf;
	}
	
	public GoogleStaticMapURIBuilder<L> addMarker(GoogleMapMarkerDelegate<L> delegate, L... markers) {
		
		for (int index = 0; index < markers.length; index++) {
			L marker = markers[index];
			MarkerStyle markerStyle = delegate.getMarkerStyle(marker, index);
			StringBuffer buf = new StringBuffer();
			if (markerStyle != null) {
				String style = markerStyle.getStyleString();
				if (StringUtils.isEmpty(style) == false) {
					buf.append(style);
					buf.append('|');
				}
			}
			buf.append(GoogleMapUtils.createLocationQueryString(marker));
			
			builder.addParameter("markers", buf.toString());
		}
		return this;
	}

	public GoogleStaticMapURIBuilder<L> addMarker(MarkerStyle markerStyle,
			L... markers) {

		builder.addParameter("markers",
				createMarkerBuffer(markerStyle, markers).toString());
		
		return this;
	}

	public GoogleStaticMapURIBuilder<L> addMarker(L... markers) {
		return addMarker(defaultMarkerStyle, markers);
	}

	public GoogleStaticMapURIBuilder<L> addPath(PathStyle pathStyle, L... paths) {
		StringBuffer buf = new StringBuffer();
		String style = pathStyle.getStyleString();
		if (StringUtils.isEmpty(style) == false) {
			buf.append(style);
			buf.append('|');
		}

		List<String> pathQueryStrings = GoogleMapUtils
				.createLocationQueryStringList(paths);
		buf.append(StringUtils.join(pathQueryStrings, '|'));

		builder.addParameter("path", buf.toString());
		return this;
	}

	public GoogleStaticMapURIBuilder<L> addPath(L... paths) {
		return addPath(defaultPathStyle, paths);
	}

	public URIBuilder getURIBuilder() {
		return builder;
	}

	@Override
	public String toString() {
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return builder.equals(obj);
	}

	@Override
	public int hashCode() {
		return builder.hashCode();
	}

}
