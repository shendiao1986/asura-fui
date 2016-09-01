package com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SQLCondition;
import com.asura.tools.sql.SelectSQL;
import com.asura.fui.service.data.IDataProvider;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.FrontData;

public class SiteArticleTagsProvider implements IDataProvider {
	private List<IOneDataProvider> oneDataProviders;

	public List<DataRecord> build(FrontData record) {
		List result = new ArrayList();
		FuiUrl url = (FuiUrl) record.getValue("fui-url");
		String host = record.getValueString("dbhost");
		String server = url.getServer();
		String id = record.getValueString("id");

		String domain = FuiUrl.getDomain(server);
		String db = "sites_" + domain.replace(".", "-");

		String sub = FuiUrl.getSub(server);

		SelectSQL catSql = new SelectSQL("tag_article");
		catSql.addField("tag");
		catSql.addWhereCondition("sub", sub);

		catSql.addWhereCondition(new SQLCondition("a_id", id, "=", true));
		List<DataRecord> temp = new MongoHandler(host).selectList(db, catSql);

		List<String> tags = new ArrayList();

		for (DataRecord ddr : temp) {
			if (!(tags.contains(ddr.getFieldValue("tag")))) {
				tags.add(ddr.getFieldValue("tag"));
			}
		}

		for (String tag : tags) {
			SelectSQL sql = new SelectSQL("tag");
			sql.addWhereCondition("alias", tag);
			sql.addWhereCondition("sub", sub);

			List r = new MongoHandler(host).selectList(db, sql);

			if (r.size() == 1) {
				DataRecord d = (DataRecord) r.get(0);

				IUrlStyle style = (IUrlStyle) record.getValue("url-style");

				d.AddField("onetag", d.getFieldValue("name"));
				d.AddField("onetagurl", style.getUrl(url, "tag", d, 1));

				if (this.oneDataProviders != null) {
					for (IOneDataProvider oneDataProvider : this.oneDataProviders) {
						oneDataProvider.build(record, d);
					}
				}

				result.add(d);
			}
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
