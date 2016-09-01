package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.StringUtil;
import com.asura.tools.util.cache.SimpleCache;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteCaches {
	private static SimpleCache<String, DataRecord> siteCache = new SimpleCache(100000);

	public static String getHomeFLink(FuiUrl url) {
		DataRecord dr = getLink(url, "home", "", true);
		return dr.getFieldValue("links");
	}

	public static String getHomeRLink(FuiUrl url) {
		DataRecord dr = getLink(url, "home", "", false);
		return dr.getFieldValue("links");
	}

	public static String getCatFLink(FuiUrl url, String cat) {
		DataRecord dr = getLink(url, "cat", cat, true);
		return dr.getFieldValue("links");
	}

	public static String getCatRLink(FuiUrl url, String cat) {
		DataRecord dr = getLink(url, "cat", cat, false);
		return dr.getFieldValue("links");
	}

	public static String getArticleFLink(FuiUrl url, String id) {
		DataRecord dr = getLink(url, "article", id, true);
		return dr.getFieldValue("links");
	}

	public static String getArticleRLink(FuiUrl url, String id) {
		DataRecord dr = getLink(url, "article", id, false);
		return dr.getFieldValue("links");
	}

	public static DataRecord getLink(FuiUrl url, String type, String key1, boolean flink) {
		String key = url.getServer() + type + key1 + "flink";
		if (!(flink)) {
			key = url.getServer() + type + key1 + "rlink";
		}
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("f_link");
			if (!(flink)) {
				sql = new SelectSQL("r_link");
			}
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
			sql.addWhereCondition("type", type);
			if (!(StringUtil.isNullOrEmpty(key1))) {
				sql.addWhereCondition("key", key1);
			}

			List list = DataSourceProvider.getMongo(url.getServer(), "sites")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0)
				siteCache.cache(key, (DataRecord) list.get(0), 3600);
			else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}

	public static String getCats(FuiUrl url, FrontData data) {
		String key = url.getServer() + "allcats";
		if (!(siteCache.iscached(key))) {
			List<DataRecord> list = new SiteCatProvider().build(data);
			List l = new ArrayList();
			for (DataRecord dr : list) {
				l.add(dr.getFieldValue("name") + "=" + dr.getFieldValue("url"));
			}

			DataRecord dr = new DataRecord();
			dr.AddField("cats", StringUtil.getStringFromStrings(l, ","));
			siteCache.cache(key, dr);
		}

		return ((DataRecord) siteCache.get(key)).getFieldValue("cats");
	}

	public static DataRecord getSiteInfos(FuiUrl url) {
		if (!(siteCache.iscached(url.getServer()))) {
			SelectSQL sql = new SelectSQL("site");
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));

			List list = DataSourceProvider.getMongo(url.getServer(), "sites")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0) {
				siteCache.cache(url.getServer(), (DataRecord) list.get(0), 3600);
			} else {
				siteCache.cache(url.getServer(), new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(url.getServer()));
	}

	public static DataRecord getCatInfos(FuiUrl url, String catAlias) {
		String key = url.getServer() + "/" + catAlias;
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("category");
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
			sql.addWhereCondition("alias", catAlias);

			List list = DataSourceProvider.getMongo(url.getServer(), "sites")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0)
				siteCache.cache(key, (DataRecord) list.get(0), 3600);
			else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}

	public static DataRecord getTagInfos(FuiUrl url, String tagAlias) {
		String key = url.getServer() + "/" + tagAlias;
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("tag");
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
			sql.addWhereCondition("alias", tagAlias);

			List list = DataSourceProvider.getMongo(url.getServer(), "sites")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0)
				siteCache.cache(key, (DataRecord) list.get(0), 3600);
			else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}

	public static DataRecord getArticleInfos(FuiUrl url, IUrlStyle urlStyle) {
		String key = url.getServer() + "/" + urlStyle.getUrlKey(url);
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("article");
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));

			sql.addWhereCondition(new SQLCondition(urlStyle.getArticleField(), urlStyle.getUrlKey(url), "=", true));

			List list = DataSourceProvider.getMongo(url.getServer(), "sites")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0)
				siteCache.cache(key, (DataRecord) list.get(0), 3600);
			else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}
}
