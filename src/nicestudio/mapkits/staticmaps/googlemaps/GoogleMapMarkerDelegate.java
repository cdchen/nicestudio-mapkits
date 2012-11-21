package nicestudio.mapkits.staticmaps.googlemaps;

import nicestudio.mapkits.directions.LocationCoordinate2D;

/**
 * 處理 Marker 繪製的代理類別。
 * 
 * @author cdchen
 *
 */
public interface GoogleMapMarkerDelegate<L extends LocationCoordinate2D> {

	MarkerStyle getMarkerStyle(L marker, int index);

}
