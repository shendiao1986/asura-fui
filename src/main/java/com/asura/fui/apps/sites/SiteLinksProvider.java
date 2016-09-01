package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.util.StringUtil;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteLinksProvider implements IDataProvider, Constants {
	private String linkType;

	public List<DataRecord> build(FrontData record) {
		List result = new ArrayList();

		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		IUrlStyle style = (IUrlStyle) record.getValue("url-style");
		String pageType = style.getUrlType(url);

		boolean flink = true;

		if ("rlink".equals(this.linkType)) {
			flink = false;
		}

		String links = SiteCaches.getLink(url, pageType, style.getUrlKey(url), flink).getFieldValue("links");

		if (StringUtil.isNullOrEmpty(links)) {
			links = SiteCaches.getLink(url, "home", "", flink).getFieldValue("links");
		}

		if (StringUtil.isNullOrEmpty(links)) {
			links = "";
		}

		for (String s : StringUtil.split(links, ",")) {
			String[] vs = StringUtil.split(s, "=");
			if (vs.length == 2) {
				DataRecord dr = new DataRecord();
				dr.AddField("fl-name", vs[0].trim());
				dr.AddField("fl-url", vs[1].trim());

				result.add(dr);
			}
		}

		return result;
	}
}
