package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteHomeProvider implements IDataProvider {
	private List<IOneDataProvider> oneDataProviders;

	public List<DataRecord> build(FrontData record) {
		List result = new ArrayList();
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL sql = new SelectSQL("site");
		sql.addWhereCondition("sub", sub);
		sql.setLimitCount(1);
		List temp = new MongoHandler(host).selectList(db, sql);

		if (temp.size() > 0) {
			DataRecord d = (DataRecord) temp.get(0);
			IUrlStyle style = (IUrlStyle) record.getValue("url-style");

			d.AddField("homeurl", style.getUrl(url, "home", d, 1));

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
