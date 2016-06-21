import com.asura.fui.service.dispatch;

import com.cpkf.yyjd.tools.util.RegularExpressionUtil;

public class UrlMapping {
	private String pattern;
	private String page;

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public boolean meet(String url) {
		try {
			return RegularExpressionUtil.matches(url, this.pattern);
		} catch (Exception e) {
		}
		return false;
	}
}
