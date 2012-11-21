package nicestudio.mapkits.staticmaps.googlemaps;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MarkerStyle extends StyleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6313326606418556255L;

	public static enum Size {
		tiny, mid, small;
	}

	private Color color;

	private Size size;

	private String label;

	public MarkerStyle() {
	}

	public MarkerStyle(Color color, Size size, String label) {
		this.color = color;
		this.size = size;
		this.label = label;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	synchronized String getStyleString() {
		List<String> results = new ArrayList<String>();
		
		if (getColor() != null) {
			results.add("color:0x" + getColor().getHexString());
		}
		
		if (getSize() != null) {
			results.add("size:" + getSize().toString());
		}
		
		if (StringUtils.isEmpty(getLabel()) == false) {
			try {
				results.add("label:" + URLEncoder.encode(getLabel(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return StringUtils.join(results, '|');
	}

}
