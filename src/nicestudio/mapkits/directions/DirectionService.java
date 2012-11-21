package nicestudio.mapkits.directions;

import java.util.List;

import nicestudio.mapkits.directions.models.DirectionResult;
import nicestudio.mapkits.directions.models.DirectionTravelMode;

public class DirectionService<E extends LocationCoordinate2D> {

	private LocationDelegate<E> locationDelegate;

	private DirectionServiceDelegate<E> serviceDelegate;

	public DirectionService(LocationDelegate<E> locationDelegate,
			DirectionServiceDelegate<E> serviceDelegate) {

		assert locationDelegate != null;
		assert serviceDelegate != null;

		this.locationDelegate = locationDelegate;
		this.serviceDelegate = serviceDelegate;
	}

	/**
	 * 透過 DirectionServiceDelegate 取得路徑規劃的物件。
	 * 
	 * @param locations
	 *            沿途經過的所有位置。
	 * @param mode
	 *            路徑規劃的旅行模式。
	 * @param sensor
	 *            是否要開啟感測器的功能。
	 * @return 路徑規劃的物件。
	 * @throws Exception
	 */
	public DirectionResult getDirectionResult(List<E> locations,
			DirectionTravelMode mode, boolean sensor) throws Exception {

		assert locations != null && locations.size() >= 2;

		locations = new DirectionUtils<E>().createLocations(
				getLocationDelegate().getCurrentLocation(), locations);

		return getServiceDelegate().getDirectionResult(locations, mode, sensor);
	}

	public LocationDelegate<E> getLocationDelegate() {
		return locationDelegate;
	}

	public DirectionServiceDelegate<E> getServiceDelegate() {
		return serviceDelegate;
	}

}
