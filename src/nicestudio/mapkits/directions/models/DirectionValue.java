package nicestudio.mapkits.directions.models;


public class DirectionValue extends DirectionObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -941511310920252086L;

	private String text;
	
	private Double value;

	public String getText() {
		return text;
	}

	public Double getValue() {
		return value;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
