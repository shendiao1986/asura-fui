package com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.cpkf.yyjd.tools.util.StringUtil;

public class DateIdUrlStyle extends AbstractSitesUrlStyle {
	public String getArticleField() {
		return "id";
	}

	public String getArticleKey(String suffix) {
		String[] ss = StringUtil.split(suffix, "/");

		return ss[(ss.length - 1)].replace(".html", "");
	}

	public String getArticleUrl(DataRecord article) {
		return DateUtil.getDateAndTimeString(DateUtil.getDateFromString(article.getFieldValue("date")), "yyyy/MM/dd")
				+ "/" + article.getFieldValue("id") + ".html";
	}

	public String getPageName() {
		return "page";
	}
}
