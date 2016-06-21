package com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.fui.apps.sites.Constants;
import com.asura.fui.apps.sites.FuiSitesUtil;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;

public class DefaultUrlStyle implements IUrlStyle, Constants {
	private static SimpleCache<String, String> cache = new SimpleCache(10000000);

	public String getUrlKey(FuiUrl url) {
		return url.getSuffix();
	}

	public String getUrlType(FuiUrl url) {
		if (!(cache.iscached(url.toUrl()))) {
			String type = "error";

			if (isHome(url))
				type = "home";
			else if (url.getSuffix().equals("sitemap.xml"))
				type = "sitemap";
			else if (isCat(url))
				type = "cat";
			else if (isTag(url))
				type = "tag";
			else if (isArticle(url)) {
				type = "article";
			}

			cache.cache(url.toUrl(), type, 86400);
		}

		return ((String) cache.get(url.toUrl()));
	}

	private boolean isHome(FuiUrl url) {
		return ((StringUtil.isNullOrEmpty(url.getSuffix())) || (url.getSuffix().startsWith("page/")));
	}

	private boolean isCat(FuiUrl url) {
		String s = url.getSuffix();
		String[] vs = StringUtil.split(s, "/");
		String c = "";
		if (vs.length > 0) {
			c = vs[0].trim();
		}
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");
		SelectSQL sql = new SelectSQL("category");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition("alias", c);
		System.out.println("isCat:" + sql);
		return (handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql) > 0L);
	}

	private boolean isTag(FuiUrl url) {
		String s = url.getSuffix();
		String[] vs = StringUtil.split(s, "/");
		String c = "";
		if (vs.length > 0) {
			c = vs[0].trim();
		}
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");
		SelectSQL sql = new SelectSQL("tag");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition("alias", c);
		System.out.println("isTag:" + sql);
		return (handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql) > 0L);
	}

	private boolean isArticle(FuiUrl url) {
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");
		SelectSQL sql = new SelectSQL("article");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition(new SQLCondition("id", url.getSuffix(), "=", true));

		System.out.println("isArticle:" + sql);
		return (handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql) > 0L);
	}

	public String getUrl(FuiUrl url, String type, DataRecord dr, int page) {
		if ("home".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + "page/" + page;
		if ("cat".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + dr.getFieldValue("alias") + "/" + page;
		if ("tag".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + dr.getFieldValue("alias") + "/" + page;
		if ("article".equals(type)) {
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + dr.getFieldValue("id");
		}

		return "http://" + url.toUrlBaseWithoutHttp();
	}

	public String getArticleField() {
		return "id";
	}
}
