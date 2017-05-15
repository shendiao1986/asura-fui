package com.asura.fui.apps.sites.urlstyle;

import java.util.List;

import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.util.FuiMysqlHandler;
import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.cache.SimpleCache;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class UrlStyleCache {
	private static SimpleCache<String, IUrlStyle> cache = new SimpleCache(10000);

	public static IUrlStyle getUrlStyle(String key) {
		if (!(cache.iscached(key))) {
			SelectSQL sql = new SelectSQL("url_style");
			sql.addWhereCondition("key", key);

			List list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);
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
