package com.asura.fui.sitemap;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.data.mongo.MongoHandler;
import com.cpkf.yyjd.tools.data.newmysql.ConnectionInformation;
import com.cpkf.yyjd.tools.sql.SelectSQL;
import com.asura.fui.util.XStreamUtil;

public class SiteMapMaker {
	public static String make(String server) {
		String monghost = ConnectionInformation.fromConfigFile().getHost();
		System.out.println("make begin monghost = " + monghost);
		MongoHandler handler = new MongoHandler(monghost);
		String[] strs = server.split("\\.");
		String sub = strs[0];
		String host = strs[1];

		String dbname = "sites_" + host + "-com";
		String style = "";

		SelectSQL sql = new SelectSQL("site");
		sql.addField("urlStyle");
		sql.addWhereCondition("sub", sub);
		List<DataRecord> r = handler.selectList(dbname, sql);
		System.out.println(sql);
		if (r.size() > 0) {
			style = ((DataRecord) r.get(0)).getFieldValue("urlStyle");
		}
		SiteMap siteMap = new SiteMap();
		List list = new ArrayList();

		if ("www".equals(sub)) {
			sql = new SelectSQL("site");
			sql.addField("sub");
			r = handler.selectList(dbname, sql);
			System.out.println(sql + " find " + r.size());
			for (DataRecord dr : r) {
				SiteUrl url = new SiteUrl();
				url.setLoc("http://" + dr.getFieldValue("sub") + "." + host + ".com");
				list.add(url);
			}
		} else {
			SiteUrl url = new SiteUrl();
			url.setLoc("http://www." + host + ".com");
			list.add(url);
		}

		sql = new SelectSQL("category");
		sql.addField("alias");
		sql.addWhereCondition("sub", sub);
		r = handler.selectList(dbname, sql);
		System.out.println("style=" + style + " " + sql + " find " + r.size());
		for (DataRecord dr : r) {
			SiteUrl url = new SiteUrl();
			url.setLoc("http://" + server + "/" + dr.getFieldValue("alias") + "/1");
			list.add(url);
		}

		sql = new SelectSQL("article");
		sql.addField("id");
		sql.addWhereCondition("sub", sub);
		r = handler.selectList(dbname, sql);
		System.out.println("style=" + style + " " + sql + " find " + r.size());

		for (DataRecord dr : r) {
			SiteUrl url = new SiteUrl();
			url.setLoc("http://" + server + getBlogUrl(style, dr));
			list.add(url);
		}

		siteMap.setSiteUrls(list);
		return XStreamUtil.toXMLAsString(siteMap);
	}

	private static String getBlogUrl(String style, DataRecord dr) {
		String blog_id = dr.getFieldValue("id");
		System.out.println("blog_id=" + blog_id);
		String postDateTime = dr.getFieldValue("date");
		String url = "";
		if ("1".equals(style))
			url = "/" + blog_id;
		else if ("2".equals(style))
			url = "/" + blog_id + ".html";
		else if ("3".equals(style))
			url = "/" + getDateUrl(postDateTime) + "/" + blog_id + ".html";
		else if ("4".equals(style))
			url = "/" + blog_id;
		else {
			url = "/" + blog_id;
		}

		return url.replaceAll("//", "/");
	}

	private static String getDateUrl(String standardDateStr) {
		String date = standardDateStr.split(" ")[0];

		return date.replace("-", "/");
	}
}
