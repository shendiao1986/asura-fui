import com.asura.fui.apps.sites.pagedata;

import javax.servlet.http.HttpServletRequest;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.core.apps.sites.Constants;
import com.asura.core.apps.sites.SiteCaches;
import com.asura.core.apps.sites.urlstyle.UrlStyleCache;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.core.service.page.PageData;
import com.asura.fui.FrontData;

public class SitesArticlePageData extends AbstractSitesPageData implements Constants {
	public PageData build(FuiUrl url, FrontData data, HttpServletRequest request) {
		DataRecord siteInfo = SiteCaches.getSiteInfos(url);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		PageData pd = getBasePageData(url, data, request);

		pd.addConstant("id", urlStyle.getUrlKey(url));

		DataRecord artcile = SiteCaches.getArticleInfos(url, urlStyle);

		pd.addConstant("ctitle", artcile.getFieldValue("title") + " | " + siteInfo.getFieldValue("title"));

		return pd;
	}

	public String getPageType() {
		return "article";
	}

	public String getPage(FuiUrl url, FrontData data, HttpServletRequest request) {
		return "article";
	}
}
