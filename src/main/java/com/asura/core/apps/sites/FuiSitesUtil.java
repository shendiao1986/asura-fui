import com.asura.fui.apps.sites;

import com.asura.core.service.dispatch.FuiUrl;

public class FuiSitesUtil {
	public static String getDbName(String server) {
		return "sites_" + FuiUrl.getDomain(server).replace(".", "-");
	}
}
