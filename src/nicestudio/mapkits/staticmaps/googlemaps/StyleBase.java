package nicestudio.mapkits.staticmaps.googlemaps;

import java.io.Serializable;

abstract class StyleBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 697864534131433350L;

	abstract String getStyleString();
}
