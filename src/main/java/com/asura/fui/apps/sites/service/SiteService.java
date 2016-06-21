package com.asura.fui.apps.sites.service;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.service.data.DataSourceProvider;

public class SiteService {
	public static void addTagMap(String domain, String sub, String tag, String id) {
		MongoArticle ma = new MongoArticle();
		ma.setSub(sub);
		ma.setId(id);

		ma = (MongoArticle) BaseService.getSiteBean(domain, sub, ma);
		if (ma != null) {
			MongoTagMap mtm = new MongoTagMap(sub, tag, id, ma.getDate());
			BaseService.add(domain, sub, mtm);
		} else {
			System.out.println(id + " not found");
		}
	}

	public static void addTag(String domain, String sub, String tag, String alias) {
		MongoTag mt = new MongoTag(sub, tag, alias, "", "");

		BaseService.add(domain, sub, mt);
	}

	public static void addCat(String domain, String sub, String cat, String alias) {
		MongoCat mt = new MongoCat(sub, cat, alias, "", "");

		BaseService.add(domain, sub, mt);
	}

	public static void deleteSite(String domain, String sub) {
		MongoSite site = new MongoSite();
		site.setSub(sub);

		BaseService.delete(domain, sub, site);
	}

	public static void updateSite(String domain, String sub, String title, String title1, String keyword, String desc,
			String ip, String urlStyle) {
		if (StringUtil.isNullOrEmpty(urlStyle)) {
			urlStyle = "1";
		}

		MongoSite site = new MongoSite(sub, title, title1, desc, keyword, ip, urlStyle);

		BaseService.update(domain, sub, site);
	}

	public static void addSite(String domain, String sub, String title, String title1, String keyword, String desc,
			String ip, String urlStyle) {
		if ((StringUtil.isNullOrEmpty(sub)) || ("www".equals(sub))) {
			MongoDomain d = new MongoDomain(domain, "", DateUtil.getTodayDate());
			BaseService.add(domain, sub, d);
		}

		if (StringUtil.isNullOrEmpty(urlStyle)) {
			urlStyle = "1";
		}
		MongoSite site = new MongoSite(sub, title, title1, desc, keyword, ip, urlStyle);
		BaseService.add(domain, sub, site);
	}

	public static String[] getSites(String auth) {
		MongoHandler mongo = DataSourceProvider.getMongo("", "sites");
		SelectSQL sql = new SelectSQL("sites");
		if (!(StringUtil.isNullOrEmpty(auth))) {
			sql.addWhereCondition("auth", auth);
		}
		sql.addOrderByField("date", false);

		List<DataRecord> list = mongo.selectList("sites", sql);

		List sites = new ArrayList();
		for (DataRecord dr : list) {
			sites.add(dr.getFieldValue("site"));
		}

		return ((String[]) sites.toArray(new String[0]));
	}
}
