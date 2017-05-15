package com.asura.fui.service.data;

import java.util.List;

import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.FuiMysqlHandler;
import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.data.mysql.ConnectionInformation;
import com.asura.tools.data.mysql.MysqlHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.StringUtil;
import com.asura.tools.util.cache.SimpleCache;

public class DataSourceProvider {
	private static SimpleCache<String, String> cache = new SimpleCache(1000000);

	public static MysqlHandler getMysql(String server, String key) {
		String config = getConfig(server, key, "mysql");
		if (StringUtil.isNullOrEmpty(config)) {
			config = getConfig(FuiUrl.getDomain(server), key, "mysql");
		}

		if (!(StringUtil.isNullOrEmpty(config))) {
			return new MysqlHandler(ConnectionInformation.fromXml(config));
		}

		return null;
	}

	public static String getMysqlXml(String server, String key) {
		String config = getConfig(server, key, "mysql");
		if (StringUtil.isNullOrEmpty(config)) {
			config = getConfig(FuiUrl.getDomain(server), key, "mysql");
		}

		if (!(StringUtil.isNullOrEmpty(config))) {
			return config;
		}

		return null;
	}

	private static String getConfig(String server, String key, String type) {
		String s = server + key + type;
		if (!(cache.iscached(s))) {
			SelectSQL sql = new SelectSQL("datasource");
			if (!(StringUtil.isNullOrEmpty(key))) {
				sql.addWhereCondition("key", key);
			}
			sql.addWhereCondition("type", type);
			if (!(StringUtil.isNullOrEmpty(server))) {
				sql.addWhereCondition("server", server);
			}
			List list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);

			if (list.size() > 0)
				cache.cache(s, ((DataRecord) list.get(0)).getFieldValue("config"), 3600);
			else {
				cache.cache(s, "");
			}
		}

		return ((String) cache.get(s));
	}

	public static String getMongoHost(String server) {
		return getMongoHost(server, "");
	}

	public static String getMongoHost(String server, String key) {
		String config = getConfig(server, key, "mongo");
		if (StringUtil.isNullOrEmpty(config)) {
			config = getConfig(FuiUrl.getDomain(server), key, "mongo");
		}

		if (!(StringUtil.isNullOrEmpty(config))) {
			return config;
		}
		config = getConfig("localhost", key, "mongo");

		return config;
	}

	public static MongoHandler getMongo(String server) {
		return getMongo(server, "");
	}

	public static MongoHandler getMongo(String server, String key) {
		String config = getConfig(server, key, "mongo");
		if (StringUtil.isNullOrEmpty(config)) {
			config = getConfig(FuiUrl.getDomain(server), key, "mongo");
		}

		if (!(StringUtil.isNullOrEmpty(config))) {
			return new MongoHandler(config);
		}
		config = getConfig("localhost", key, "mongo");

		if (!(StringUtil.isNullOrEmpty(config))) {
			return new MongoHandler(config);
		}

		return null;
	}
}
