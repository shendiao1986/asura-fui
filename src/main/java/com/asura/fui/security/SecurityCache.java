package com.asura.fui.security;

import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mysql.MysqlHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.cache.SimpleCache;
import com.asura.fui.exception.FuiConfigExpcetion;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SecurityCache {
	private static SimpleCache<String, FuiSecurity> cache = new SimpleCache(10000);

	public static FuiSecurity getSecurity(String server) {
		if (!(cache.iscached(server))) {
			SelectSQL sql = new SelectSQL("security");
			sql.addWhereCondition("server", server);
			List list = new MysqlHandler().selectList(sql);

			if (list.size() == 1)
				try {
					cache.cache(server, fromXml(((DataRecord) list.get(0)).getFieldValue("security")));
				} catch (Exception e) {
					throw new FuiConfigExpcetion(e);
				}
			else {
				cache.cache(server, new FuiSecurity());
			}
		}

		return ((FuiSecurity) cache.get(server));
	}

	public static FuiSecurity fromXml(String xml) {
		XStream xs = new XStream(new DomDriver());

		xs.alias("fui-security", FuiSecurity.class);

		xs.alias("url-author", UrlAuthor.class);

		xs.aliasAttribute(UrlAuthor.class, "url", "url");
		xs.aliasAttribute(UrlAuthor.class, "role", "role");

		return ((FuiSecurity) xs.fromXML(xml));
	}
}
