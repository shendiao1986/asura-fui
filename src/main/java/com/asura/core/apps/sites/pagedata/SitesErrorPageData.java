import com.asura.fui.apps.sites.pagedata;

import javax.servlet.http.HttpServletRequest;

import com.asura.core.apps.sites.Constants;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.page.PageData;
import com.asura.fui.FrontData;


public class SitesErrorPageData extends AbstractSitesPageData implements Constants {
	public PageData build(FuiUrl url, FrontData data, HttpServletRequest request) {
		PageData pd = getBasePageData(url, data, request);

		return pd;
	}

	public String getPageType() {
		return "error";
	}

	public String getPage(FuiUrl url, FrontData data, HttpServletRequest request) {
		return "error";
	}
}