import com.asura.fui.apps.sites;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.core.service.data.IOneDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteOneCatProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		FuiUrl url = (FuiUrl) data.getValue("fui-url");
		String server = url.getServer();
		String host = data.getValueString("dbhost");
		String sub = FuiUrl.getSub(server);
		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		DataRecord dr = ArticleCategoryCaches.getCategoryInfos(server, record.getFieldValue("id"));

		if (dr != null) {
			record.AddField("cat", dr.getFieldValue("name"));
			IUrlStyle style = (IUrlStyle) data.getValue("url-style");
			record.AddField("caturl", style.getUrl(url, "cat", dr, 1));
		}
	}
}
