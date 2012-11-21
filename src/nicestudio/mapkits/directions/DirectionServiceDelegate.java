package nicestudio.mapkits.directions;

import java.util.List;

import nicestudio.mapkits.directions.models.DirectionResult;
import nicestudio.mapkits.directions.models.DirectionTravelMode;

public interface DirectionServiceDelegate<E extends LocationCoordinate2D> {

	DirectionResult getDirectionResult(List<E> locations,
			DirectionTravelMode mode, boolean sensor) throws Exception;

}
