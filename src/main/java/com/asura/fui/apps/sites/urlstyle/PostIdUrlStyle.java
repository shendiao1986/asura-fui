package com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;

public class PostIdUrlStyle extends AbstractSitesUrlStyle {
	public String getArticleField() {
		return "id";
	}

	public String getArticleKey(String suffix) {
		return suffix;
	}

	public String getArticleUrl(DataRecord article) {
		return article.getFieldValue("id");
	}

	public String getPageName() {
		return "page";
	}
}