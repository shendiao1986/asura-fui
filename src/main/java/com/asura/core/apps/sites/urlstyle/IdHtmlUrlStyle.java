import com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;

public class IdHtmlUrlStyle extends AbstractSitesUrlStyle {
	public String getArticleUrl(DataRecord article) {
		return article.getFieldValue("id") + ".html";
	}

	public String getPageName() {
		return "page";
	}

	public String getArticleKey(String suffix) {
		return suffix.replace(".html", "");
	}

	public String getArticleField() {
		return "id";
	}
}
