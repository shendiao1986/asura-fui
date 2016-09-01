package com.asura.fui.apps.sites;

import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class OneNextArticleProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		FuiUrl url = (FuiUrl) data.getValue("fui-url");
		String host = data.getValueString("dbhost");
		String server = url.getServer();
		String id = data.getValueString("id");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL sql = new SelectSQL("article");
		sql.setLimitCount(1);
		sql.addOrderByField("id", false);
		sql.addWhereCondition(new SQLCondition("id", id, ">", true));
		sql.addWhereCondition("sub", sub);
		IUrlStyle style = (IUrlStyle) data.getValue("url-style");
		List r = new MongoHandler(host).selectList(db, sql);

		if (r.size() > 0) {
			DataRecord next = (DataRecord) r.get(0);

			next.AddField("url", style.getUrl(url, "article", next, 1));

			for (String field : next.getAllFields())
				record.AddField("next_" + field, next.getFieldValue(field));
		} else {
			record.AddField("next_title", "");
		}
	}
}
