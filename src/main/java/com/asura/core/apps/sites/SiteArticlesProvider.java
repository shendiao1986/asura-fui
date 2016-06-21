import com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.debug.SpendTimer;
import com.cpkf.yyjd.tools.sql.LimitSQL;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.apps.sites.urlstyle.UrlStyleCache;
import com.asura.core.service.data.IDataProvider;
import com.asura.core.service.data.IOneDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.core.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SiteArticlesProvider implements IDataProvider, Constants {
	private String page;
	private String num;
	private String size;
	private List<IOneDataProvider> oneDataProviders;

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
		IUrlStyle style = (IUrlStyle) record.getValue("url-style");

		int keepsize = 0;
		try {
			keepsize = Integer.parseInt(ParamterUtil.getValue(this.size, record));
		} catch (Exception e) {
			keepsize = -1;
		}
		int p = 1;
		int n = 10;
		try {
			p = Integer.parseInt(ParamterUtil.getValue(this.page, record));
		} catch (Exception localException1) {
		}
		try {
			n = Integer.parseInt(ParamterUtil.getValue(this.num, record));
		} catch (Exception localException2) {
		}
		SelectSQL sql = new SelectSQL("cat_article");
		sql.addField("a_id");
		sql.addWhereCondition("sub", sub);
		sql.addOrderByField("date", true);
		DataRecord siteInfo = SiteCaches.getSiteInfos(url);
		System.out.println("find article list: p=" + p + " n=" + n);

		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		List result = new ArrayList();
		IOneDataProvider oneDataProvider;
		if (!(StringUtil.isNullOrEmpty(cat))) {
			sql.addWhereCondition("cat", cat);

			if ("tag".equals(urlStyle.getUrlType(url))) {
				sql = new SelectSQL("tag_article");
				sql.addField("a_id");
				sql.addWhereCondition("sub", sub);
				if (!(StringUtil.isNullOrEmpty(cat))) {
					sql.addWhereCondition("tag", cat);
				}
				sql.addOrderByField("date", true);
			}

			sql.setLimit(new LimitSQL((p - 1) * n, n));

			st.printSpendTime("pre ids");

			System.out.println("SiteArticlesProvider:" + sql);
			List<DataRecord> list = new MongoHandler(host).selectList(db, sql);

			st.printSpendTime(sql.getSQLString());

			System.out.println(sql + " find " + list.size() + " articles");

			for (DataRecord dr : list) {
				DataRecord d = ArticleCaches.getArticleInfos(server, dr.getFieldValue("a_id"));

				if (d != null) {
					d.AddField("url", style.getUrl(url, "article", d, 1));

					if (this.oneDataProviders != null) {
						for (Iterator localIterator2 = this.oneDataProviders.iterator(); localIterator2.hasNext();) {
							oneDataProvider = (IOneDataProvider) localIterator2.next();
							oneDataProvider.build(record, d);
						}
					}
					if (keepsize > 0) {
						d.AddField("content", d.getFieldValue("content").substring(0,
								Math.min(keepsize, d.getFieldValue("content").length())));
					}

					result.add(d);
				}
			}
			st.printSpendTime("select each id");
		} else {
			sql = new SelectSQL("article");
			sql.addWhereCondition("sub", sub);
			sql.addOrderByField("date", true);
			sql.setLimit(new LimitSQL((p - 1) * n, n));
			st.printSpendTime("select all article");
			List<DataRecord> list = new MongoHandler(host).selectList(db, sql);
			st.printSpendTime(sql.getSQLString());

			System.out.println(sql + " find " + list.size() + " articles");
			for (DataRecord dr : list) {
				dr.AddField("url", style.getUrl(url, "article", dr, 1));

				if (this.oneDataProviders != null) {
					for (IOneDataProvider provider : this.oneDataProviders) {
						provider.build(record, dr);
					}
				}

				if (keepsize > 0) {
					dr.AddField("content", dr.getFieldValue("content").substring(0,
							Math.min(keepsize, dr.getFieldValue("content").length())));
				}
				result.add(dr);
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

	public static void main(String[] args) {
		int p = 2;
		int n = 10;
		SelectSQL sql = new SelectSQL("article");
		sql.addOrderByField("date", true);
		sql.setLimit(new LimitSQL((p - 1) * n, n));
		System.out.println(sql);
	}
}