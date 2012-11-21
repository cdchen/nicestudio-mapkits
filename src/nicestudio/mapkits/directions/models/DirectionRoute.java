package nicestudio.mapkits.directions.models;

import java.util.ArrayList;
import java.util.List;

public class DirectionRoute extends DirectionObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6183028680189855514L;

	private String copyrights;
	
	private String overviewPolyline;
	
	private List<Integer> waypointOrders = new ArrayList<Integer>();
	
	private List<DirectionLeg> legs = new ArrayList<DirectionLeg>();
	
	private List<String> warnings = new ArrayList<String>();
	
	private String summary;

	private DirectionResult directionResult;
	
	public DirectionRoute(DirectionResult directionResult) {
		assert directionResult != null;
		this.directionResult = directionResult;
	}

	public boolean hasWarnings() {
		return getWarnings().size() > 0;
	}

	public String getCopyrights() {
		return copyrights;
	}

	public String getOverviewPolyline() {
		return overviewPolyline;
	}

	public List<Integer> getWaypointOrders() {
		return waypointOrders;
	}

	public List<DirectionLeg> getLegs() {
		return legs;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public String getSummary() {
		return summary;
	}

	public void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}

	public void setOverviewPolyline(String overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public DirectionResult getDirectionResult() {
		return directionResult;
	}
}
