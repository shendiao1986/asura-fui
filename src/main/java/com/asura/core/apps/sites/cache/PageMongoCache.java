import com.asura.fui.apps.sites.cache;

import java.util.Date;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.core.apps.sites.FuiSitesUtil;
import com.asura.core.service.cache.IPageCache;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;

public class PageMongoCache implements IPageCache {
	private static SimpleCache<String, String> cache = new SimpleCache(10000);

	public void cache(FuiUrl url, String content) {
		PageCacheBatch.addTask(new PageCacheTask(url, content, 3600));

		cache.cache(url.toUrl(), content);
	}

	public String get(FuiUrl url) {
		if (isCache(url)) {
			return ((String) cache.get(url.toUrl()));
		}

		return "";
	}

	public boolean isCache(FuiUrl url) {
		if (!(cache.iscached(url.toUrl()))) {
			SelectSQL sql = new SelectSQL("page_cache");
			sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
			sql.addWhereCondition("key", url.toUrl());

			List list = DataSourceProvider.getMongo(url.getServer(), "")
					.selectList(FuiSitesUtil.getDbName(url.getServer()), sql);

			if (list.size() > 0) {
				String date = ((DataRecord) list.get(0)).getFieldValue("date");
				String second = ((DataRecord) list.get(0)).getFieldValue("second");

				if (DateUtil.getTimeBySecond(DateUtil.getDateFromString(date), new Date()) < Integer.valueOf(second)
						.intValue()) {
					cache.cache(url.toUrl(), ((DataRecord) list.get(0)).getFieldValue("content"));
				}
			}
		}

		return cache.iscached(url.toUrl());
	}
}