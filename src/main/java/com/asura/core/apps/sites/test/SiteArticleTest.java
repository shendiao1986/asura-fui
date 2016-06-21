import com.asura.fui.apps.sites.test;

import java.util.Date;

import com.cpkf.yyjd.tools.data.DataIterator;
import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.IEditor;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.cpkf.yyjd.tools.util.math.RandomUtil;

public class SiteArticleTest {
	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("58.215.65.52");

		IEditor editor = handler.getEditor("sites_localhost", "article", new String[] { "sub", "title", "date" },
				new String[] { "sub", "title", "date", "id" });

		SelectSQL sql = new SelectSQL("rose_article");
		sql.addFields(new String[] { "content", "title" });
		sql.setLimitCount(1000);

		DataIterator it = handler.select("rose", sql);
		int id = 1000000;
		while (it.hasNext()) {
			DataRecord dr = (DataRecord) it.next();
			dr.AddField("sub", "www");
			dr.AddField("excerpt", dr.getFieldValue("content"));
			dr.AddField("date", DateUtil.getDateAndTimeString(new Date()));
			dr.AddField("auth", "user" + RandomUtil.random(1, 100));
			dr.AddField("id", id++);
			dr.AddField("keyword", "");
			dr.AddField("desc", dr.getFieldValue("title"));

			editor.addRecord(dr);
			System.out.println(id);
		}
	}
}
