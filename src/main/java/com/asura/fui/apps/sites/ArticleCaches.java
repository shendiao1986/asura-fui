package com.asura.fui.apps.sites;

import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.cache.SimpleCache;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;

public class ArticleCaches {
	private static SimpleCache<String, DataRecord> siteCache = new SimpleCache(100000);

	public static DataRecord getArticleInfos(String server, String id) {
		String key = FuiUrl.getDomain(server) + "/" + id;
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("article");
			sql.addWhereCondition(new SQLCondition("id", id, "=", true));

			List list = DataSourceProvider.getMongo(server, "sites").selectList(FuiSitesUtil.getDbName(server), sql);
			if (list.size() > 0)
				siteCache.cache(key, (DataRecord) list.get(0), 3600);
			else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}
}
