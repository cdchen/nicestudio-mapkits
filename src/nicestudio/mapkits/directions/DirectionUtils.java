package nicestudio.mapkits.directions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectionUtils<E extends LocationCoordinate2D> {

	public List<E> createLocations(E current,
			Collection<E> locations) {
		List<E> result = new ArrayList<E>();
		result.add(current);
		result.addAll(locations);
		return result;
	}
}
