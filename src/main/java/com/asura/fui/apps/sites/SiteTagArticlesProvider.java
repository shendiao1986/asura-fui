package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.debug.SpendTimer;
import com.asura.tools.sql.LimitSQL;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SiteTagArticlesProvider implements IDataProvider, Constants {
	private String page;
	private String num;

	public List<DataRecord> build(FrontData record) {
		SpendTimer st = SpendTimer.getInstance("as provider");
		SpendTimer.enableKey("as provider");

		st.startTime();

		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();
		String tag = record.getValueString("tag");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL sql = new SelectSQL("article");
		sql.addWhereCondition("sub", sub);
		sql.addWhereCondition(new SQLCondition("keyword", tag, "like"));
		sql.addOrderByField("date", true);

		int p = 1;
		int n = 10;
		try {
			p = Integer.parseInt(ParamterUtil.getValue(this.page, record));
		} catch (Exception localException) {
		}
		try {
			n = Integer.parseInt(ParamterUtil.getValue(this.num, record));
		} catch (Exception localException1) {
		}
		sql.setLimit(new LimitSQL((p - 1) * n, n));

		st.printSpendTime("pre ids");

		List<DataRecord> list = new MongoHandler(host).selectList(db, sql);

		st.printSpendTime(sql.getSQLString());

		IUrlStyle style = (IUrlStyle) record.getValue("url-style");

		List result = new ArrayList();
		for (DataRecord dr : list) {
			dr.AddField("url", style.getUrl(url, "article", dr, 1));
			result.add(dr);
		}

		st.printSpendTime("select each id");

		return result;
	}

}
