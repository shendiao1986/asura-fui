package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.math.NumberUtil;
import com.asura.tools.util.math.RandomUtil;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteRecentArticleProvider implements IDataProvider {
	public List<DataRecord> build(FrontData record) {
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		SelectSQL sql = new SelectSQL("article");
		sql.addField("id");
		sql.addOrderByField("id", true);
		sql.setLimitCount(1);

		List<DataRecord> list = new MongoHandler(host).selectList(db, sql);

		String id = "1000001";
		if (list.size() == 1) {
			id = ((DataRecord) list.get(0)).getFieldValue("id");
		}

		int max = NumberUtil.getInt(id) - 1000000;

		sql = new SelectSQL("article");
		sql.setLimitCount(10);
		sql.setLimitStart(RandomUtil.random(0, Math.max(0, max - 10)));

		list = new MongoHandler(host).selectList(db, sql);

		IUrlStyle style = (IUrlStyle) record.getValue("url-style");
		String pageType = style.getUrlType(url);

		List result = new ArrayList();
		for (DataRecord dr : list) {
			dr.AddField("url", style.getUrl(url, pageType, dr, 1));
			result.add(dr);
		}

		return result;
	}
}
