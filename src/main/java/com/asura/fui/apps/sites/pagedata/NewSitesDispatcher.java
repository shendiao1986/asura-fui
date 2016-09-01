package com.asura.fui.apps.sites.pagedata;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.asura.tools.data.DataRecord;
import com.asura.fui.apps.sites.Constants;
import com.asura.fui.apps.sites.SiteCaches;
import com.asura.fui.apps.sites.urlstyle.UrlStyleCache;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.IUrlDispatcher;
import com.asura.fui.service.dispatch.urlstyle.IUrlStyle;
import com.asura.fui.service.page.IPageDataBuilder;
import com.asura.fui.service.page.PageData;
import com.asura.fui.DataConverter;
import com.asura.fui.FrontData;

public class NewSitesDispatcher implements IUrlDispatcher, Constants {
	private List<IPageDataBuilder> builders;

	private void initial() {
		if ((this.builders == null) || (this.builders.size() == 0)) {
			this.builders = new ArrayList();

			this.builders.add(new SitesHomePageData());
			this.builders.add(new SitesCatPageData());
			this.builders.add(new SitesTagPageData());
			this.builders.add(new SitesArticlePageData());
			this.builders.add(new SitesErrorPageData());
		}
	}

	public FrontData getFrontData(FuiUrl url, HttpServletRequest request) {
		initial();

		FrontData data = DataConverter.fromParameter(request);

		DataRecord siteInfo = SiteCaches.getSiteInfos(url);
		IUrlStyle urlStyle = UrlStyleCache.getUrlStyle(siteInfo.getFieldValue("urlStyle"));

		String host = DataSourceProvider.getMongoHost(url.getServer(), "sites");
		System.out.println("mongo host:" + host);

		data.AddField("dbhost", host);
		data.AddField("fui-url", url);
		data.AddField("root-path", url.toUrlBase());
		data.AddField("url-style", urlStyle);

		for (IPageDataBuilder builder : this.builders) {
			if (builder.canHandle(url, data)) {
				PageData pd = builder.build(url, data, request);
				FrontData fd = pd.getPageData(url, data);
				fd.merge(data);

				return fd;
			}
		}

		FrontData fd = new SitesErrorPageData().build(url, data, request).getPageData(url, data);
		fd.merge(data);

		return fd;
	}

	public String getPage(FuiUrl url, HttpServletRequest request) {
		FrontData data = DataConverter.fromParameter(request);
		for (IPageDataBuilder builder : this.builders) {
			if (builder.canHandle(url, data)) {
				return builder.getPage(url, data, request);
			}
		}

		return "error";
	}
}