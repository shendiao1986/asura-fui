package com.asura.fui.apps.sites.service;

import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.IEditor;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.StringUtil;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;

public class BaseService {
	public static String getDbName(String server) {
		return "sites_" + FuiUrl.getDomain(server).replace(".", "-");
	}

	public static String getDbNameFromDomain(String domain) {
		return "sites_" + domain.replace(".", "-");
	}

	public static ISiteBean getSiteBean(String domain, String sub, ISiteBean key) {
		MongoHandler mongo = DataSourceProvider.getMongo("", "sites");
		String db = key.getDbName(domain, sub);

		SelectSQL sql = new SelectSQL(key.getTableName());
		DataRecord dr = key.toDataRecord();
		for (String f : dr.getAllFields()) {
			String v = dr.getFieldValue(f);
			if (!(StringUtil.isNullOrEmpty(v))) {
				sql.addWhereCondition(f, v);
			}
		}

		List list = mongo.selectList(db, sql);
		if (list.size() == 1) {
			return key.fromDataRecord((DataRecord) list.get(0));
		}

		return null;
	}

	public static void delete(String domain, String sub, ISiteBean bean) {
		MongoHandler mongo = DataSourceProvider.getMongo("", "sites");
		String db = bean.getDbName(domain, sub);

		IEditor editor = mongo.getEditor(db, bean.getTableName(), bean.getKeys(), bean.getIndexes());

		DataRecord dr = bean.toDataRecord();

		editor.deleteRecord(dr);
	}

	public static void add(String domain, String sub, ISiteBean bean) {
		MongoHandler mongo = DataSourceProvider.getMongo("", "sites");
		String db = bean.getDbName(domain, sub);

		IEditor editor = mongo.getEditor(db, bean.getTableName(), bean.getKeys(), bean.getIndexes());

		editor.addRecord(bean.toDataRecord());
	}

	public static void update(String domain, String sub, ISiteBean bean) {
		MongoHandler mongo = DataSourceProvider.getMongo("", "sites");
		String db = bean.getDbName(domain, sub);

		IEditor editor = mongo.getEditor(db, bean.getTableName(), bean.getKeys(), bean.getIndexes());

		editor.addRecord(bean.toDataRecord(), true);
	}
}
