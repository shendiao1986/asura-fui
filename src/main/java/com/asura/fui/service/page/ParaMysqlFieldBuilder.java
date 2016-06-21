package com.asura.fui.service.page;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ParaMysqlFieldBuilder extends AbstractParaBuilder {
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
		List list = DataSourceProvider.getMysql(url.getServer(), this.datasource).selectList(sql);

		if (list.size() > 0) {
			d.AddField(this.key, ((DataRecord) list.get(0)).getFieldValue(this.field));
		}

		return d;
	}
}