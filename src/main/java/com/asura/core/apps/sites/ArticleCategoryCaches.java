import com.asura.fui.apps.sites;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;

public class ArticleCategoryCaches {
	private static SimpleCache<String, DataRecord> siteCache = new SimpleCache(100000);

	public static DataRecord getCategoryInfos(String server, String id) {
		String key = FuiUrl.getDomain(server) + "/" + id;
		if (!(siteCache.iscached(key))) {
			SelectSQL catSql = new SelectSQL("cat_article");
			catSql.addField("cat");
			catSql.addWhereCondition("sub", FuiUrl.getSub(server));

			catSql.addWhereCondition(new SQLCondition("a_id", id, "=", true));
			catSql.setLimitCount(1);

			System.out.println("getCategoryInfos:" + catSql);

			List list = DataSourceProvider.getMongo(server, "sites").selectList(FuiSitesUtil.getDbName(server), catSql);

			if (list.size() > 0) {
				SelectSQL tempSql = new SelectSQL("category");
				tempSql.addWhereCondition("sub", FuiUrl.getSub(server));
				tempSql.addWhereCondition("alias", ((DataRecord) list.get(0)).getFieldValue("cat"));
				tempSql.setLimitCount(1);
				List cats = DataSourceProvider.getMongo(server, "sites").selectList(FuiSitesUtil.getDbName(server),
						tempSql);
				System.out.println("getCategory:" + tempSql);
				if (cats.size() > 0)
					siteCache.cache(key, (DataRecord) cats.get(0), 3600);
			} else {
				siteCache.cache(key, new DataRecord(), 3600);
			}
		}

		return ((DataRecord) siteCache.get(key));
	}
}
