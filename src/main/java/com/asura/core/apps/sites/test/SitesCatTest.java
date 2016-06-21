import com.asura.fui.apps.sites.test;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.IEditor;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;

public class SitesCatTest {
	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("58.215.65.52");

		IEditor editor = handler.getEditor("sites_localhost", "category", new String[] { "sub", "name", "alias" },
				new String[] { "sub", "name" });

		DataRecord dr = new DataRecord();
		dr.AddField("sub", "www");
		dr.AddField("name", "管理首页");
		dr.AddField("alias", "manager");

		addCat(editor, "www", "管理首页", "manager", "", "");
		addCat(editor, "www", "交易促成", "deal", "", "");
		addCat(editor, "www", "项目管理", "project", "", "");
	}

	private static void addCat(IEditor editor, String sub, String name, String alias, String keyword, String desc) {
		DataRecord dr = new DataRecord();

		dr.AddField("sub", sub);
		dr.AddField("name", name);
		dr.AddField("alias", alias);
		dr.AddField("keyword", keyword);
		dr.AddField("desc", desc);

		editor.addRecord(dr);
	}
}
