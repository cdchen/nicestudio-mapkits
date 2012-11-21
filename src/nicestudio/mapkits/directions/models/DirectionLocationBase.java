package nicestudio.mapkits.directions.models;

import nicestudio.mapkits.directions.LocationCoordinate2D;


public abstract class DirectionLocationBase extends DirectionObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2655661245361131194L;

	private DirectionValue distance;
	
	private DirectionValue duration;
	
	private LocationCoordinate2D startLocation;
	
	private LocationCoordinate2D endLocation;
	
	public DirectionValue getDistance() {
		return distance;
	}

	public DirectionValue getDuration() {
		return duration;
	}

	public LocationCoordinate2D getStartLocation() {
		return startLocation;
	}

	public LocationCoordinate2D getEndLocation() {
		return endLocation;
	}

	public void setDistance(DirectionValue distance) {
		this.distance = distance;
	}

	public void setDuration(DirectionValue duration) {
		this.duration = duration;
	}

	public void setStartLocation(LocationCoordinate2D startLocation) {
		this.startLocation = startLocation;
	}

	public void setEndLocation(LocationCoordinate2D endLocation) {
		this.endLocation = endLocation;
	}

}
