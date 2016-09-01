package com.asura.fui.apps.sites.test;

import com.asura.tools.data.DataIterator;
import com.asura.tools.data.DataRecord;
import com.asura.tools.data.IEditor;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;

public class SitesCatMapTest {
	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("58.215.65.52");

		IEditor editor = handler.getEditor("sites_localhost", "cat_article", new String[] { "sub", "cat", "a_id" },
				new String[] { "sub", "cat", "date" });

		SelectSQL sql = new SelectSQL("article");
		sql.addFields(new String[] { "id", "date" });

		DataIterator it = handler.select("sites_localhost", sql);

		int i = 0;
		while (it.hasNext()) {
			DataRecord dr = (DataRecord) it.next();
			DataRecord n = new DataRecord();
			n.AddField("sub", "www");
			n.AddField("cat", new String[] { "manager", "deal", "project" }[com.asura.tools.util.math.RandomUtil
					.random(0, 2)]);
			n.AddField("a_id", dr.getFieldValue("id"));
			n.AddField("date", dr.getFieldValue("date"));

			editor.addRecord(n);
			System.out.println(i++);
		}
	}
}
