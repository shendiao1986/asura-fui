package com.asura.fui.service.dispatch;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.newmysql.MysqlHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.config.FuiConverter;
import com.asura.fui.script.IUIScript;
import com.asura.fui.FuiPage;

public class PageCache {
	private static SimpleCache<String, Object> map = new SimpleCache(10000);

	public static FuiPage getPage(String server, String name) {
		String key = server + name;
		System.out.println("page key:" + key);
		if (!(map.iscached(key))) {
			String config = getConfig(server, name);
			if (!(StringUtil.isNullOrEmpty(config))) {
				map.cache(key, FuiConverter.getPage(config), 1);
			}

		}

		return ((FuiPage) map.get(key));
	}

	public static IUIData getDataRef(String server, String ref) {
		String key = server + ref;
		if (!(map.iscached(key))) {
			String config = getConfig(server, ref);
			if (!(StringUtil.isNullOrEmpty(config))) {
				map.cache(key, FuiConverter.getData(config), 1);
			}

		}

		return ((IUIData) map.get(key));
	}

	public static IUIScript getScriptRef(String server, String ref) {
		String key = server + ref;
		if (!(map.iscached(key))) {
			String config = getConfig(server, ref);
			if (!(StringUtil.isNullOrEmpty(config))) {
				map.cache(key, FuiConverter.getScript(config), 1);
			}

		}

		return ((IUIScript) map.get(key));
	}

	private static String getConfig(String server, String name) {
		SelectSQL sql = new SelectSQL("page");
		if (!(StringUtil.isNullOrEmpty(server))) {
			sql.addWhereCondition("site", server);
		}
		sql.addWhereCondition("name", name);
		List list = new MysqlHandler().selectList(sql);
		if (list.size() == 0) {
			sql = new SelectSQL("page");
			sql.addWhereCondition("name", name);
			list = new MysqlHandler().selectList(sql);
		}
		if (list.size() > 0) {
			return ((DataRecord) list.get(0)).getFieldValue("page");
		}

		return "";
	}
}
