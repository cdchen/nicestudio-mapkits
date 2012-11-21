package nicestudio.mapkits.staticmaps.googlemaps;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class PathStyle extends StyleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621938339389079855L;

	private Color color;
	
	private Integer weight;
	
	public PathStyle() {
	}

	public PathStyle(Color color, Integer weight) {
		this.color = color;
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	synchronized String getStyleString() {
		List<String> results = new ArrayList<String>();
		
		if (getColor() != null) {
			results.add("color:0x" + getColor().getHexString());
		}

		if (getWeight() != null) {
			results.add("weight:" + getWeight());
		}

		return StringUtils.join(results, '|');
	}
	
}
