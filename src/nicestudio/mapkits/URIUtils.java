package nicestudio.mapkits;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URIUtils {

	public static String decode(String encoded) {
		try {
			return URLDecoder.decode(encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		return null;
	}
}
