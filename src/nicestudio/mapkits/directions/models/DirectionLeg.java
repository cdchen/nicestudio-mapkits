package nicestudio.mapkits.directions.models;

import java.util.ArrayList;
import java.util.List;

import nicestudio.mapkits.directions.LocationCoordinate2D;

public class DirectionLeg extends DirectionLocationBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -347143297327306608L;

	private String startAddress;
	
	private String endAddress;
	
	private List<Integer> viaWaypoints = new ArrayList<Integer>();
	
	private List<DirectionStep> steps = new ArrayList<DirectionStep>();

	private DirectionRoute directionRoute;
	
	public DirectionLeg(DirectionRoute directionRoute) {
		assert directionRoute != null;
		
		this.directionRoute = directionRoute;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public List<Integer> getViaWaypoints() {
		return viaWaypoints;
	}

	public List<DirectionStep> getSteps() {
		return steps;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	public DirectionRoute getDirectionRoute() {
		return directionRoute;
	}
	
	public List<String> getHtmlInstructions() {
		List<String> results = new ArrayList<String>();
		for (DirectionStep step : getSteps()) {
			results.add(step.getHtmlInstruction());
		}
		return results;
	}
	
	public List<LocationCoordinate2D> getLocations() {
		List<LocationCoordinate2D> results = new ArrayList<LocationCoordinate2D>();
		for (DirectionStep step : getSteps()) {
			results.add(step.getStartLocation());
		}
		return results;
	}
}
