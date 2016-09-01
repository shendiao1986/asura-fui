package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.debug.SpendTimer;
import com.asura.tools.sql.LimitSQL;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.StringUtil;
import com.asura.fui.apps.sites.urlstyle.UrlStyleCache;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SiteArticlesRandomProvider implements IDataProvider, Constants {
	private String page;
	private String num;
	private String keep;
	private List<IOneDataProvider> oneDataProviders;
	private static final Random RANDOM = new Random();

	public List<DataRecord> build(FrontData record) {
		SpendTimer st = SpendTimer.getInstance("as provider");
		SpendTimer.enableKey("as provider");

		st.startTime();

		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();
		String cat = record.getValueString("cat");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL sql = new SelectSQL("cat_article");
		sql.addField("a_id");
		sql.addWhereCondition("sub", sub);
		if (!(StringUtil.isNullOrEmpty(cat))) {
			sql.addWhereCondition("cat", cat);
		}
		sql.addOrderByField("date", true);

		DataRecord siteInfo = SiteCaches.getSiteInfos(url);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));
		if ("tag".equals(urlStyle.getUrlType(url))) {
			sql = new SelectSQL("tag_article");
			sql.addField("a_id");
			sql.addWhereCondition("sub", sub);
			if (!(StringUtil.isNullOrEmpty(cat))) {
				sql.addWhereCondition("tag", cat);
			}
			sql.addOrderByField("date", true);
		}

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
		if (n > 0) {
			sql.setLimit(new LimitSQL((p - 1) * n, n));
		}

		st.printSpendTime("pre ids");

		List list = new MongoHandler(host).selectList(db, sql);

		st.printSpendTime(sql.getSQLString());

		IUrlStyle style = (IUrlStyle) record.getValue("url-style");

		List result = new ArrayList();

		List<DataRecord> rlist = randomList(list);
		for (DataRecord dr : rlist) {
			DataRecord d = ArticleCaches.getArticleInfos(server, dr.getFieldValue("a_id"));

			if (d != null) {
				d.AddField("url", style.getUrl(url, "article", d, 1));

				if (this.oneDataProviders != null) {
					for (IOneDataProvider oneDataProvider : this.oneDataProviders) {
						oneDataProvider.build(record, d);
					}
				}

				result.add(d);
			}
		}

		st.printSpendTime("select each id");

		return result;
	}

	public List<DataRecord> randomList(List<DataRecord> list) {
		List ret = new ArrayList();
		int n = Integer.parseInt(this.keep);

		int size = list.size();

		if (size <= n)
			return list;
		do {
			DataRecord dr = (DataRecord) list.remove(RANDOM.nextInt(list.size()));
			ret.add(dr);
		} while (ret.size() < n);

		return ret;
	}

	public List<IOneDataProvider> getOneDataProviders() {
		return this.oneDataProviders;
	}

	public void setOneDataProviders(List<IOneDataProvider> oneDataProviders) {
		this.oneDataProviders = oneDataProviders;
	}
}