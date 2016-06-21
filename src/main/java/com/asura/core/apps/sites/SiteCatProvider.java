import com.asura.fui.apps.sites;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.asura.core.service.data.IDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteCatProvider implements IDataProvider {
	public List<DataRecord> build(FrontData record) {
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();

		System.out.println("cat host:" + host);

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL sql = new SelectSQL("category");
		sql.addWhereCondition("sub", sub);

		IUrlStyle style = (IUrlStyle) record.getValue("url-style");

		List<DataRecord> list = new MongoHandler(host).selectList(db, sql);

		for (DataRecord dr : list) {
			dr.AddField("url", style.getUrl(url, "cat", dr, 1));
		}

		return list;
	}
}
