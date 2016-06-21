import com.asura.fui.service.page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.cpkf.yyjd.tools.util.math.NumberUtil;
import com.asura.core.component.custom.ListViewUtil;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ParaMongoListViewBuilder extends AbstractParaBuilder {
	private String field;
	private String db;
	private SelectSQL sql;
	private String datasource;

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public SelectSQL getSql() {
		return this.sql;
	}

	public void setSql(SelectSQL sql) {
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

		List<DataRecord> list = DataSourceProvider.getMongo("", this.datasource).selectList(this.db, this.sql);

		List result = new ArrayList();
		if (list.size() > 0) {
			HashMap<String,String> m = ParamterUtil.convert(this.field);

			for (DataRecord dr : list) {
				FrontData subD = new FrontData();
				for (String key : m.keySet()) {
					subD.AddField(key, ListViewUtil.getText(getLenghed(dr.getFieldValue((String) m.get(key)))));
				}
				result.add(subD);
			}
		}

		d.AddField(this.key, result);

		return d;
	}

	private String getLenghed(String v) {
		if ((!(StringUtil.isNullOrEmpty(v))) && (StringUtil.isNumberString(v))) {
			if (NumberUtil.getDouble(v) > 1.0D) {
				return NumberUtil.getLenedDouble(NumberUtil.getDouble(v), 2);
			}
			return NumberUtil.getLenedDouble(NumberUtil.getDouble(v), 4);
		}

		try {
			double d = new BigDecimal(v).doubleValue();
			return NumberUtil.getLenedDouble(d, 4);
		} catch (Exception localException) {
		}
		return v;
	}
}
