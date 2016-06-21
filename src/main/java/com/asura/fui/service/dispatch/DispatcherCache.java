package com.asura.fui.service.dispatch;

import java.util.HashMap;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.newmysql.MysqlHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DispatcherCache {
	private static final String SPLITER = "어";
	private static HashMap<String, IUrlDispatcher> map;
	private static HashMap<String, IUrlDispatcher> lowMap;

	static {
		reload();
	}

	private static void reload() {
		map = new HashMap();
		lowMap = new HashMap();

		SelectSQL sql = new SelectSQL("url_dispatcher");
		List<DataRecord> list = new MysqlHandler().selectList(sql);
		for (DataRecord dr : list) {
			IUrlDispatcher dp = fromXml(dr.getFieldValue("dispatcher"));
			map.put(dr.getFieldValue("server") + "어" + dr.getFieldValue("port"), dp);
			lowMap.put(dr.getFieldValue("server"), dp);
		}
	}

	public static IUrlDispatcher getDispather(String server, int port) {
		IUrlDispatcher dispatcher = (IUrlDispatcher) map.get(server + "어" + port);
		if (dispatcher == null) {
			return ((IUrlDispatcher) map.get("localhost어" + port));
		}

		return dispatcher;
	}

	public static IUrlDispatcher getDispather(String server) {
		IUrlDispatcher dispatcher = (IUrlDispatcher) lowMap.get(server);
		if (dispatcher == null) {
			return ((IUrlDispatcher) lowMap.get("localhost"));
		}

		return dispatcher;
	}

	
	private static IUrlDispatcher fromXml(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("simple-dispatcher", SimpleDispatcher.class);
		xs.addImplicitCollection(SimpleDispatcher.class, "mappings");

		xs.alias("url-mapping", UrlMapping.class);
		xs.aliasAttribute(UrlMapping.class, "pattern", "pattern");
		xs.aliasAttribute(UrlMapping.class, "page", "page");

		return ((IUrlDispatcher) xs.fromXML(xml));
	}

}
