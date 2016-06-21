package com.asura.fui.service.cache;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.newmysql.MysqlHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.fui.service.dispatch.FuiUrl;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PageCacheService {
	
	private static SimpleCache<String, IPageCache> cache = new SimpleCache(10000);
	
	public static IPageCache getPageCache(FuiUrl url) {
		if (!(cache.iscached(url.getServer()))) {
			SelectSQL sql = new SelectSQL("page_cache");
			sql.addWhereCondition("server", url.getServer());
			List list = new MysqlHandler().selectList(sql);

			if (list.size() == 1)
				try {
					cache.cache(url.getServer(), fromXml(((DataRecord) list.get(0)).getFieldValue("cache")));
				} catch (Exception localException) {
				}
			else {
				cache.cache(url.getServer(), new PageMemCache(1));
			}
		}

		return ((IPageCache) cache.get(url.getServer()));
	}
	
	public static IPageCache fromXml(String xml) {
		XStream xs = new XStream(new DomDriver());

		xs.alias("multi-cache", PageMultiCache.class);
		xs.addImplicitCollection(PageMultiCache.class, "caches");

		xs.alias("mem-cache", PageMemCache.class);
		xs.aliasAttribute(PageMemCache.class, "second", "second");

		return ((IPageCache) xs.fromXML(xml));
	}

}
