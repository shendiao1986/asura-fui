package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteCatRelatedArticlesProvider implements IDataProvider {
	private List<IOneDataProvider> oneDataProviders;
	private String limit;
	private static final int MAX = 5;

	public List<DataRecord> build(FrontData record) {
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();
		String id = record.getValueString("id");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		List result = new ArrayList();
		SelectSQL sql = new SelectSQL("cat_article");
		sql.setLimitCount(1);
		sql.addWhereCondition(new SQLCondition("a_id", id, "=", true));
		sql.addWhereCondition("sub", sub);

		int l = 1;
		try {
			l = Integer.parseInt(this.limit);
		} catch (Exception localException) {
		}
		List r = new MongoHandler(host).selectList(db, sql);

		if (r.size() <= 0) {
			sql = new SelectSQL("cat_article");
			sql.setLimitCount(1);
			sql.addWhereCondition(new SQLCondition("a_id", id, "=", false));
			sql.addWhereCondition("sub", sub);
			r = new MongoHandler(host).selectList(db, sql);
		}

		if (r.size() == 1) {
			DataRecord d = (DataRecord) r.get(0);

			String cat = d.getFieldValue("cat");

			SelectSQL tempsql = new SelectSQL("cat_article");
			tempsql.addWhereCondition("sub", sub);
			tempsql.addWhereCondition("cat", cat);
			tempsql.setLimitCount(Math.min(5, l));

			List<DataRecord> rlist = new MongoHandler(host).selectList(db, tempsql);

			for (DataRecord dr : rlist) {
				SelectSQL asql = new SelectSQL("article");
				asql.setLimitCount(1);
				asql.addWhereCondition(new SQLCondition("id", dr.getFieldValue("a_id"), "=", true));
				List alist = new MongoHandler(host).selectList(db, asql);
				DataRecord ad = (DataRecord) alist.get(0);
				IUrlStyle style = (IUrlStyle) record.getValue("url-style");

				ad.AddField("url", style.getUrl(url, "article", ad, 1));

				if (this.oneDataProviders != null) {
					for (IOneDataProvider oneDataProvider : this.oneDataProviders) {
						oneDataProvider.build(record, ad);
					}
				}

				result.add(ad);
			}

		}

		return result;
	}

	public List<IOneDataProvider> getOneDataProviders() {
		return this.oneDataProviders;
	}

	public void setOneDataProviders(List<IOneDataProvider> oneDataProviders) {
		this.oneDataProviders = oneDataProviders;
	}
}
