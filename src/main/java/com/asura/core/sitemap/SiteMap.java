import com.asura.fui.sitemap;

import java.util.ArrayList;
import java.util.List;


public class SiteMap {
	private List<SiteUrl> siteUrls = new ArrayList();

	public List<SiteUrl> getSiteUrls() {
		return this.siteUrls;
	}

	public void setSiteUrls(List<SiteUrl> siteUrls) {
		this.siteUrls = siteUrls;
	}
}
