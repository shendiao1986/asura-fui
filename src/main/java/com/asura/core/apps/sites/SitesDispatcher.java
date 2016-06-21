import com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.debug.SpendTimer;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.apps.sites.urlstyle.CatUrl;
import com.asura.core.apps.sites.urlstyle.UrlStyleCache;
import com.asura.core.exception.FuiDispatchExpcetion;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.IUrlDispatcher;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SitesDispatcher implements IUrlDispatcher, Constants {
	public FrontData getFrontData(FuiUrl url, HttpServletRequest request) {
		SpendTimer t = SpendTimer.getInstance("dispatch");
		SpendTimer.enableKey("dispatch");
		t.startTime();

		DataRecord siteInfo = SiteCaches.getSiteInfos(url);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		String style = urlStyle.getUrlType(url);

		t.printSpendTime("get url type");

		FrontData data = new FrontData();

		String host = DataSourceProvider.getMongoHost(url.getServer());

		if (StringUtil.isNullOrEmpty(host)) {
			host = DataSourceProvider.getMongoHost(FuiUrl.getDomain(url.getServer()));
		}

		if (StringUtil.isNullOrEmpty(host)) {
			throw new FuiDispatchExpcetion("site " + url.getServer() + " is not defined");
		}

		System.out.println(url.getServer() + ":" + host);
		data.AddField("dbhost", host);
		data.AddField("fui-url", url);
		data.AddField("root-path", url.toUrlBase());
		data.AddField("url-style", urlStyle);

		String cats = SiteCaches.getCats(url, data);
		data.AddField("cats", cats);

		data.AddField("head", "首页=" + url.toUrlBase());

		t.printSpendTime("get cats");

		setData(data, siteInfo, "title", "htitle");
		setData(data, siteInfo, "title1", "htitle1");

		t.printSpendTime("get siteInfo");

		if ("home".equals(style)) {
			CatUrl cu = new CatUrl(urlStyle.getUrlKey(url));

			data.AddField("cat", "");
			data.AddField("selected", "");
			data.AddField("cpage", cu.getPage());
			data.AddField("pagemax", "100");

			if (StringUtil.isNullOrEmpty(siteInfo.getFieldValue("title1")))
				setData(data, siteInfo, "title", "ctitle");
			else {
				data.AddField("ctitle", siteInfo.getFieldValue("title") + " | " + siteInfo.getFieldValue("title1"));
			}

			List pages = new ArrayList();
			for (int i = Math.max(1, cu.getPage() - 10); i < cu.getPage() + 10; ++i) {
				DataRecord dr = new DataRecord();
				dr.AddField("alias", cu.getName());
				pages.add(i + "=" + urlStyle.getUrl(url, "home", dr, i));
			}

			data.AddField("pages", StringUtil.getStringFromStrings(pages, ","));

			setKeywordDesc(data, siteInfo);
		} else if ("cat".equals(style)) {
			CatUrl cu = new CatUrl(urlStyle.getUrlKey(url));

			data.AddField("cat", cu.getName());
			data.AddField("cpage", cu.getPage());
			data.AddField("pagemax", "100");

			DataRecord catInfo = SiteCaches.getCatInfos(url, cu.getName());

			data.AddField("selected", catInfo.getFieldValue("name"));

			data.AddField("ctitle", catInfo.getFieldValue("name") + " | " + siteInfo.getFieldValue("title"));

			List pages = new ArrayList();
			for (int i = Math.max(1, cu.getPage() - 10); i < cu.getPage() + 10; ++i) {
				DataRecord dr = new DataRecord();
				dr.AddField("alias", cu.getName());
				pages.add(i + "=" + urlStyle.getUrl(url, "cat", dr, i));
			}

			data.AddField("pages", StringUtil.getStringFromStrings(pages, ","));

			setKeywordDesc(data, catInfo);
			setKeywordDesc(data, siteInfo);
		} else if ("article".equals(style)) {
			data.AddField("id", urlStyle.getUrlKey(url));

			DataRecord artcile = SiteCaches.getArticleInfos(url, urlStyle);

			data.AddField("ctitle", artcile.getFieldValue("title") + " | " + siteInfo.getFieldValue("title"));

			setKeywordDesc(data, artcile);
			setKeywordDesc(data, siteInfo);
		} else if ("error".equals(style)) {
			if (StringUtil.isNullOrEmpty(siteInfo.getFieldValue("title1")))
				setData(data, siteInfo, "title", "ctitle");
			else {
				data.AddField("ctitle", siteInfo.getFieldValue("title") + " | " + siteInfo.getFieldValue("title1"));
			}
		}

		return data;
	}

	private void setData(FrontData data, DataRecord dr, String f, String t) {
		if (StringUtil.isNullOrEmpty(data.getValueString(t)))
			data.AddField(t, dr.getFieldValue(f));
	}

	private void setKeywordDesc(FrontData data, DataRecord dr) {
		setData(data, dr, "keyword", "keyword");
		setData(data, dr, "desc", "desc");
	}

	public String getPage(FuiUrl url, HttpServletRequest request) {
		DataRecord siteInfo = SiteCaches.getSiteInfos(url);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		return urlStyle.getUrlType(url);
	}
}
