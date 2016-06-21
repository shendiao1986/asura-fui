import com.asura.fui.apps.sites;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;

public class AttachmentCaches {
	private static SimpleCache<String, DataRecord> siteCache = new SimpleCache(10000);

	public static DataRecord getAttachment(String server, String attach, String type) {
		String key = FuiUrl.getDomain(server) + "/" + type + "/" + attach;
		if (!(siteCache.iscached(key))) {
			SelectSQL sql = new SelectSQL("site_attachment");
			sql.addWhereCondition(new SQLCondition("sub", FuiUrl.getSub(server), "=", false));
			sql.addWhereCondition(new SQLCondition("attach", attach, "=", false));
			sql.addWhereCondition(new SQLCondition("type", type, "=", false));

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
