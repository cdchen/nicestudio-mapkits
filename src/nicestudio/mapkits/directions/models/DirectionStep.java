package nicestudio.mapkits.directions.models;

public class DirectionStep extends DirectionLocationBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972151397751571185L;

	private String htmlInstruction;
	
	private String polyline;
	
	private DirectionTravelMode travelMode;

	private DirectionLeg directionLeg;
	
	
	public DirectionStep(DirectionLeg directionLeg) {
		assert directionLeg != null;
		
		this.directionLeg = directionLeg;
	}

	public String getHtmlInstruction() {
		return htmlInstruction;
	}

	public String getPolyline() {
		return polyline;
	}

	public DirectionTravelMode getTravelMode() {
		return travelMode;
	}

	public void setHtmlInstruction(String htmlInstruction) {
		this.htmlInstruction = htmlInstruction;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public void setTravelMode(DirectionTravelMode travelMode) {
		this.travelMode = travelMode;
	}

	public DirectionLeg getDirectionLeg() {
		return directionLeg;
	}
	
}
