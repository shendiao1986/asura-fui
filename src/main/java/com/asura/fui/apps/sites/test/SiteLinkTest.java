package com.asura.fui.apps.sites.test;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.IEditor;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;

public class SiteLinkTest {
	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("58.215.65.52");

		IEditor editor = handler.getEditor("sites_localhost", "f_link", new String[] { "sub", "type", "key" },
				new String[] { "sub", "type", "key" });

		DataRecord dr = new DataRecord();

		dr.AddField("sub", "www");
		dr.AddField("type", "home");
		dr.AddField("key", "");
		dr.AddField("links", "百度=http://www.baidu.com,网站地图=http://www.s800f.com");

		editor.addRecord(dr);
	}
}
