package com.asura.fui.apps.sites;

import com.asura.fui.service.dispatch.FuiUrl;

public class FuiSitesUtil {
	public static String getDbName(String server) {
		return "sites_" + FuiUrl.getDomain(server).replace(".", "-");
	}
}
