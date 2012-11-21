package nicestudio.mapkits.staticmaps.googlemaps;

import org.apache.commons.lang3.StringUtils;

public final class Color {

	public static final Color BLACK = new Color("000000", "black");
	
	public static final Color RED = new Color("FF0000", "red");
	
	public static final Color YELLOW = new Color("FFFF00", "yellow");
	
	public static final Color BLUE = new Color("0000FF", "blue");
	
	public static final Color BROWN = new Color("A52A2A", "brown");
	
	public static final Color GREEN = new Color("008000", "green");
	
	public static final Color PURPLE = new Color("800080", "purple");
	
	public static final Color GRAY = new Color("808080", "gray");
	
	public static final Color ORANGE = new Color("FFA500", "orange");
	
	public static final Color WHITE = new Color("FFFFFF", "white");
	
	
	private String name;

	private String hexString;

	public Color(String hexString, String name) {
		setHexString(hexString);
		setName(name);
	}

	public Color() {
	}

	public Color(String hexString) {
		setHexString(hexString);
	}

	public String getHexString() {
		return hexString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		assert StringUtils.isEmpty(name) == false;

		this.name = name;
	}

	public void setHexString(String hexString) {
		assert StringUtils.isEmpty(hexString) == false;

		this.hexString = hexString;
	}

	@Override
	public String toString() {
		return StringUtils.isEmpty(name) == false ? name : hexString;
	}
	
}
