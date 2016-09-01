package com.asura.fui.apps.sites.test;

import java.util.Date;

import com.asura.tools.data.DataIterator;
import com.asura.tools.data.DataRecord;
import com.asura.tools.data.IEditor;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.DateUtil;
import com.asura.tools.util.math.RandomUtil;

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
