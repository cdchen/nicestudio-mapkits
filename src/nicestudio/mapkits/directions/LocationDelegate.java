package nicestudio.mapkits.directions;


public interface LocationDelegate<E extends LocationCoordinate2D> {

	E getCurrentLocation();
	
}
