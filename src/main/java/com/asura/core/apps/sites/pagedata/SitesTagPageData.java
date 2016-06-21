import com.asura.fui.apps.sites.pagedata;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.apps.sites.Constants;
import com.asura.core.apps.sites.FuiSitesUtil;
import com.asura.core.apps.sites.SiteCaches;
import com.asura.core.apps.sites.urlstyle.CatUrl;
import com.asura.core.apps.sites.urlstyle.UrlStyleCache;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.core.service.page.PageData;
import com.asura.fui.FrontData;

public class SitesTagPageData extends AbstractSitesPageData implements Constants {
	public PageData build(FuiUrl url, FrontData data, HttpServletRequest request) {
		DataRecord siteInfo = SiteCaches.getSiteInfos(url);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		PageData pd = getBasePageData(url, data, request);

		CatUrl cu = new CatUrl(urlStyle.getUrlKey(url));
		DataRecord catInfo = SiteCaches.getTagInfos(url, cu.getName());

		pd.addConstant("cat", cu.getName());
		pd.addConstant("selected", catInfo.getFieldValue("name"));
		pd.addConstant("cpage", cu.getPage());
		pd.addConstant("pagemax", getCount(url, cu.getName()) / 10L + 1L);
		pd.addConstant("ctitle", catInfo.getFieldValue("name") + " | " + siteInfo.getFieldValue("title"));

		List pages = new ArrayList();
		for (int i = Math.max(1, cu.getPage() - 10); i < cu.getPage() + 10; ++i) {
			DataRecord dr = new DataRecord();
			dr.AddField("alias", cu.getName());
			pages.add(i + "=" + urlStyle.getUrl(url, "cat", dr, i));
		}

		data.AddField("pages", StringUtil.getStringFromStrings(pages, ","));

		return pd;
	}

	private long getCount(FuiUrl url, String tag) {
		SelectSQL sql = new SelectSQL("tag_article");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition("tag", tag);

		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");

		return handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql);
	}

	public String getPageType() {
		return "tag";
	}

	public String getPage(FuiUrl url, FrontData data, HttpServletRequest request) {
		return "cat";
	}
}
