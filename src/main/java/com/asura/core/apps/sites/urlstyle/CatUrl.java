import com.asura.fui.apps.sites.urlstyle;

import com.cpkf.yyjd.tools.util.StringUtil;

public class CatUrl {
	private String name;
	private int page;

	public CatUrl(String suffix) {
		String[] vs = StringUtil.split(suffix, "/");
		if (vs.length == 1) {
			this.name = vs[0].trim();
			this.page = 1;
		} else {
			this.name = vs[0].trim();
			try {
				this.page = Integer.parseInt(vs[1].trim());
			} catch (Exception e) {
				this.page = 1;
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
