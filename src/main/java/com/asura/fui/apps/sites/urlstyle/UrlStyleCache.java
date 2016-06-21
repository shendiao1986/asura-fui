package com.asura.fui.apps.sites.urlstyle;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.newmysql.MysqlHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UrlStyleCache {
	private static SimpleCache<String, IUrlStyle> cache = new SimpleCache(10000);

	public static IUrlStyle getUrlStyle(String key) {
		if (!(cache.iscached(key))) {
			SelectSQL sql = new SelectSQL("url_style");
			sql.addWhereCondition("key", key);

			List list = new MysqlHandler().selectList(sql);
			if (list.size() == 1)
				cache.cache(key, (IUrlStyle) new XStream(new DomDriver())
						.fromXML(((DataRecord) list.get(0)).getFieldValue("style")));
			else {
				cache.cache(key, new DefaultUrlStyle());
			}
		}

		return ((IUrlStyle) cache.get(key));
	}
}
