package com.asura.fui.service.post;

import java.util.List;

import com.asura.fui.exception.FuiConfigExpcetion;
import com.asura.fui.util.FuiMysqlHandler;
import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.cache.SimpleCache;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServiceHandlerCache {
	private static SimpleCache<String, ServicePostHandler> cache = new SimpleCache(10000);

	public static ServicePostHandler getServicePostHandler(String server) {
		if (!(cache.iscached(server))) {
			SelectSQL sql = new SelectSQL("post_handler");
			sql.addWhereCondition("server", server);
			List list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);
			if (list.size() == 0) {
				sql = new SelectSQL("post_handler");
				sql.setLimitCount(1);
				list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);
			}

			if (list.size() == 1)
				try {
					cache.cache(server, fromXml(((DataRecord) list.get(0)).getFieldValue("handler")));
				} catch (Exception e) {
					throw new FuiConfigExpcetion(e);
				}
			else {
				cache.cache(server, new ServicePostHandler());
			}
		}

		return ((ServicePostHandler) cache.get(server));
	}

	public static ServicePostHandler fromXml(String xml) {
		XStream xs = new XStream(new DomDriver());

		xs.alias("post-handler", ServicePostHandler.class);
		xs.addImplicitCollection(ServicePostHandler.class, "handlers");

		return ((ServicePostHandler) xs.fromXML(xml));
	}
}
