package com.asura.fui.apps.sites.test;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.IEditor;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;

public class SitesSiteTest {
	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("58.215.65.52");

		IEditor editor = handler.getEditor("sites_localhost", "site", new String[] { "sub" },
				new String[] { "sub", "title" });

		DataRecord dr = new DataRecord();

		dr.AddField("sub", "www");
		dr.AddField("title", "800传奇私服网");
		dr.AddField("title1", "传奇私服，最新CQ私服，CQ新开发布网");
		dr.AddField("keyword", "网通传奇sf,变态传奇私服网,新开CQ私服网,最新开网通私服,传奇sf发布网");
		dr.AddField("desc", "网通最新最全传奇私服发布网");
		dr.AddField("ip", "localhost");
		dr.AddField("urlStyle", "2");

		editor.addRecord(dr, true);
	}
}
