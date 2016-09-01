package com.asura.fui.service.data;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.FrontData;
import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mysql.MysqlHandler;
import com.asura.tools.sql.SelectSQL;

public class MysqlDataProvider implements IDataProvider {
	public static final String RE = "$";
	private String sql;
	private boolean single;

	public List<DataRecord> build(FrontData record) {
		String s = replace(record);

		SelectSQL sql = new SelectSQL();
		sql.setSql(s);

		List list = new MysqlHandler().selectList(sql);

		if (list.size() > 0) {
			if (this.single) {
				List result = new ArrayList();
				result.add((DataRecord) list.get(0));
				return result;
			}
			return list;
		}

		return new ArrayList();
	}

	private String replace(FrontData record) {
		String s = this.sql;
		for (String r : record.getAllFields()) {
			s = s.replace("$" + r, record.getValueString(r));
		}

		return s;
	}
}
