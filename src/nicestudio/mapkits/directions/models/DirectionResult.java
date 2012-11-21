package nicestudio.mapkits.directions.models;

import java.util.ArrayList;
import java.util.List;

import nicestudio.mapkits.directions.LocationCoordinate2D;

public class DirectionResult extends DirectionObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027215902351161795L;

	private String status;

	private List<DirectionRoute> routes = new ArrayList<DirectionRoute>();

	public String getStatus() {
		return status;
	}

	public List<DirectionRoute> getRoutes() {
		return routes;
	}

	/*
	 * Utils methods.
	 */
	public List<DirectionLeg> findLeg(LocationCoordinate2D start,
			LocationCoordinate2D end) {
		List<DirectionLeg> results = new ArrayList<DirectionLeg>();

		for (DirectionRoute route : getRoutes()) {

			for (DirectionLeg leg : route.getLegs()) {
				if (start.equals(leg.getStartLocation())
						&& end.equals(leg.getEndLocation())) {
					results.add(leg);
				}
			}

		}
		return results;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
