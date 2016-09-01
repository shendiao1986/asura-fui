package com.asura.fui.service.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ParaMysqlRecordsBuilder extends AbstractParaBuilder {
	private String field;
	private String sql;
	private String datasource;

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getDatasource() {
		return this.datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public FrontData build(FuiUrl url, FrontData data) {
		FrontData d = new FrontData();

		this.sql = ParamterUtil.getValue(this.sql, data);

		SelectSQL sql = new SelectSQL();
		sql.setSql(this.sql);
		List<DataRecord> list = DataSourceProvider.getMysql(url.getServer(), this.datasource).selectList(sql);

		List result = new ArrayList();
		if (list.size() > 0) {
			HashMap<String,String> m = ParamterUtil.convert(this.field);

			for (DataRecord dr : list) {
				FrontData subD = new FrontData();
				for (String key : m.keySet()) {
					subD.AddField(key, dr.getFieldValue((String) m.get(key)));
				}
				result.add(subD);
			}
		}

		d.AddField(this.key, result);

		return d;
	}
}