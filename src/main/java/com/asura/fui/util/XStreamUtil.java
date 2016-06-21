package com.asura.fui.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.asura.fui.sitemap.SiteMap;
import com.asura.fui.sitemap.SiteUrl;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamUtil {
	private static XStream xstream;

	private static XStream getXStream() {
		if (xstream == null) {
			xstream = new XStream(new DomDriver());
			xstream.alias("content", String.class);
			xstream.addImplicitCollection(SiteMap.class, "siteUrls");
			xstream.alias("urlset", SiteMap.class);
			xstream.alias("url", SiteUrl.class);
		}
		return xstream;
	}

	public static String toXMLAsString(Object object) {
		XStream xstream = getXStream();
		return xstream.toXML(object);
	}

	public static Object newObject(InputStream inputStream) {
		XStream xstream = getXStream();

		return xstream.fromXML(inputStream);
	}

	public static Object newObject(String xmlString) {
		XStream xstream = getXStream();

		return xstream.fromXML(xmlString);
	}

	public static void main(String[] args) {
		SiteMap siteMap = new SiteMap();
		List list = new ArrayList();

		SiteUrl url = new SiteUrl();
		url.setLoc("123");
		list.add(url);
		siteMap.setSiteUrls(list);
		String xmlString = toXMLAsString(siteMap);
		System.out.println(xmlString);
	}
}