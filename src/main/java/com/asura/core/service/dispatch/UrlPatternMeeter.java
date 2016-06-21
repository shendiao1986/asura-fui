import com.asura.fui.service.dispatch;

import com.cpkf.yyjd.tools.util.RegularExpressionUtil;

public class UrlPatternMeeter implements IUrlMeeter {
	private String pattern;

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean meet(String url) {
		try {
			return RegularExpressionUtil.matches(url, this.pattern);
		} catch (Exception e) {
		}
		return false;
	}
}