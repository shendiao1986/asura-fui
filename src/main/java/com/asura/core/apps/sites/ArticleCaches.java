import com.asura.fui.apps.sites;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;

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
