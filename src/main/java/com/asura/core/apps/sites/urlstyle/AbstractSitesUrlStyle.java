import com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SQLCondition;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.core.apps.sites.Constants;
import com.asura.core.apps.sites.FuiSitesUtil;
import com.asura.core.service.data.DataSourceProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;

public abstract class AbstractSitesUrlStyle implements IUrlStyle, Constants {
	private static SimpleCache<String, String> cache = new SimpleCache(10000000);

	public String getUrl(FuiUrl url, String type, DataRecord dr, int page) {
		if ("home".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + getPageName() + "/" + page;
		if ("cat".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + dr.getFieldValue("alias") + "/" + page;
		if ("article".equals(type))
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + getArticleUrl(dr);
		if ("tag".equals(type)) {
			return "http://" + url.toUrlBaseWithoutHttp() + "/" + dr.getFieldValue("alias") + "/" + page;
		}

		return "http://" + url.toUrlBaseWithoutHttp();
	}

	public String getUrlKey(FuiUrl url) {
		if ((isHome(url)) || (isCat(url)) || (isTag(url))) {
			return url.getSuffix();
		}
		return getArticleKey(url.getSuffix());
	}

	public String getUrlType(FuiUrl url) {
		if (!(cache.iscached(url.toUrl()))) {
			String type = "error";

			if (isHome(url))
				type = "home";
			else if (url.getSuffix().equals("sitemap.xml"))
				type = "sitemap";
			else if (isCat(url))
				type = "cat";
			else if (isTag(url))
				type = "tag";
			else if (isArticle(url)) {
				type = "article";
			}

			cache.cache(url.toUrl(), type, 86400);
		}

		return ((String) cache.get(url.toUrl()));
	}

	private boolean isHome(FuiUrl url) {
		return ((StringUtil.isNullOrEmpty(url.getSuffix())) || (url.getSuffix().startsWith(getPageName() + "/")));
	}

	private boolean isCat(FuiUrl url) {
		String s = url.getSuffix();
		String[] vs = StringUtil.split(s, "/");
		String c = "";
		if (vs.length > 0) {
			c = vs[0].trim();
		}
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");
		SelectSQL sql = new SelectSQL("category");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition("alias", c);

		return (handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql) > 0L);
	}

	private boolean isTag(FuiUrl url) {
		String s = url.getSuffix();
		String[] vs = StringUtil.split(s, "/");
		String c = "";
		if (vs.length > 0) {
			c = vs[0].trim();
		}
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");
		SelectSQL sql = new SelectSQL("tag");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));
		sql.addWhereCondition("alias", c);

		return (handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql) > 0L);
	}

	private boolean isArticle(FuiUrl url) {
		System.out.println("url.getServer  " + url.getServer());
		MongoHandler handler = DataSourceProvider.getMongo(url.getServer(), "sites");

		SelectSQL sql = new SelectSQL("article");
		sql.addWhereCondition("sub", FuiUrl.getSub(url.getServer()));

		sql.addWhereCondition(new SQLCondition(getArticleField(), getArticleKey(url.getSuffix()), "=", true));
		System.out.println("isArticle " + sql);
		long count = handler.getCount(FuiSitesUtil.getDbName(url.getServer()), sql);

		return (count > 0L);
	}

	public abstract String getArticleUrl(DataRecord paramDataRecord);

	public abstract String getArticleKey(String paramString);

	public abstract String getArticleField();

	public abstract String getPageName();

	public static void main(String[] args) {
		MongoHandler handler = new MongoHandler("23.83.198.2");
		SelectSQL sql = new SelectSQL("article");
		sql.addWhereCondition("sub", "176cqsf");

		sql.addWhereCondition(new SQLCondition("id", "000074031", "=", false));

		System.out.println(handler.getCount(FuiSitesUtil.getDbName("176cqsf.185zscqsf.com"), sql));

		System.out.println(sql);
	}
}
