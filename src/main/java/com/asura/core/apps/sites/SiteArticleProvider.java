import com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.core.service.data.IDataProvider;
import com.asura.core.service.data.IOneDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.core.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteArticleProvider implements IDataProvider {
	private List<IOneDataProvider> oneDataProviders;

	public List<DataRecord> build(FrontData record) {
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();
		String id = record.getValueString("id");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");
		IUrlStyle style = (IUrlStyle) record.getValue("url-style");
		String sub = FuiUrl.getSub(server);

		List result = new ArrayList();

		DataRecord d = ArticleCaches.getArticleInfos(server, id);
		if (d != null) {
			d.AddField("homeurl", style.getUrl(url, "home", d, 1));
			d.AddField("url", style.getUrl(url, "article", d, 1));

			if (this.oneDataProviders != null) {
				for (IOneDataProvider oneDataProvider : this.oneDataProviders) {
					oneDataProvider.build(record, d);
				}
			}

			result.add(d);
		}

		return result;
	}

	public List<IOneDataProvider> getOneDataProviders() {
		return this.oneDataProviders;
	}

	public void setOneDataProviders(List<IOneDataProvider> oneDataProviders) {
		this.oneDataProviders = oneDataProviders;
	}
}
