import com.asura.fui.apps.sites;

import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.asura.core.service.data.IOneDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class OneTagProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		FuiUrl url = (FuiUrl) data.getValue("fui-url");
		String server = url.getServer();
		String host = data.getValueString("dbhost");
		String sub = FuiUrl.getSub(server);
		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		SelectSQL catSql = new SelectSQL("tag_article");
		catSql.addField("tag");
		catSql.addWhereCondition("sub", sub);

		catSql.addWhereCondition(new SQLCondition("a_id", record.getFieldValue("id"), "=", true));
		catSql.setLimitCount(1);
		List temp = new MongoHandler(host).selectList(db, catSql);

		System.out.println("OneTagProvider:" + catSql);
		if (temp.size() > 0) {
			SelectSQL tempSql = new SelectSQL("tag");
			tempSql.addField("name");
			tempSql.addWhereCondition("sub", sub);
			tempSql.addWhereCondition("alias", ((DataRecord) temp.get(0)).getFieldValue("tag"));
			tempSql.setLimitCount(1);
			List cats = new MongoHandler(host).selectList(db, tempSql);

			System.out.println("getTag:" + tempSql);
			if (cats.size() > 0) {
				((DataRecord) cats.get(0)).AddField("alias", ((DataRecord) temp.get(0)).getFieldValue("tag"));

				record.AddField("tag", ((DataRecord) cats.get(0)).getFieldValue("name"));

				IUrlStyle style = (IUrlStyle) data.getValue("url-style");
				record.AddField("tagurl", style.getUrl(url, "tag", (DataRecord) cats.get(0), 1));
			} else {
				record.AddField("tag", "");
				record.AddField("tagurl", "");
			}
		} else {
			record.AddField("tag", "");
			record.AddField("tagurl", "");
		}
	}
}
