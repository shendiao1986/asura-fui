import com.asura.fui.service.data;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.FrontData;
import com.cpkf.yyjd.tools.data.DataRecord;

public class MysqlExecuteDataProvider implements IDataProvider {
	public static final String RE = "$";
	private String sql;
	private String datasource;

	public List<DataRecord> build(FrontData record) {
		String s = replace(record);

		DataSourceProvider.getMysql("", this.datasource).execute(s);

		return new ArrayList();
	}

	private String replace(FrontData record) {
		String s = this.sql;
		for (String r : record.getAllFields()) {
			s = s.replace("$" + r + "$", record.getValueString(r));
		}

		return s;
	}
}
