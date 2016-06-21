import com.asura.fui.service.dispatch;

import java.util.HashMap;

public class UrlTypeCache {
	private static HashMap<String, String> cache = new HashMap();

	public String getUrlType(String url) {
		return ((String) cache.get(url));
	}
}
