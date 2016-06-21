import com.asura.fui.service.data;

import java.util.HashMap;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.newmysql.MysqlHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.asura.core.config.FuiConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DataProviderCache {
	private static final String SPLITER = "어";
	private static HashMap<String, DataProvider> map;
	private static HashMap<String, DataProvider> lowMap;

	static {
		reload();
	}

	private static void reload() {
		map = new HashMap();
		lowMap = new HashMap();

		SelectSQL sql = new SelectSQL("data_provider");
		List<DataRecord> list = new MysqlHandler().selectList(sql);
		for (DataRecord dr : list) {
			DataProvider dp = fromXml(dr.getFieldValue("provider"));
			map.put(dr.getFieldValue("site") + "어" + dr.getFieldValue("category") + "어" + dr.getFieldValue("key"), dp);
			lowMap.put(dr.getFieldValue("category") + "어" + dr.getFieldValue("key"), dp);
		}
	}

	public static DataProvider getBuilder(String site, String category, String key) {
		return ((DataProvider) map.get(site + "어" + category + "어" + key));
	}

	public static DataProvider getBuilder(String category, String key) {
		return ((DataProvider) lowMap.get(category + "어" + key));
	}

	private static DataProvider fromXml(String xml) {
		XStream xs = new XStream(new DomDriver());
		FuiConverter.setDataProvider(xs);

		return ((DataProvider) xs.fromXML(xml));
	}
}
