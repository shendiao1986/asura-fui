package com.asura.fui.apps.sites.cache;

import java.util.Date;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.asura.fui.apps.sites.FuiSitesUtil;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.service.dispatch.FuiUrl;

public class PageCacheTask {
	private FuiUrl url;
	private String content;
	private int second;

	public PageCacheTask() {
	}

	public PageCacheTask(FuiUrl url, String content, int second) {
		this.url = url;
		this.content = content;
		this.second = second;
	}

	public FuiUrl getUrl() {
		return this.url;
	}

	public void setUrl(FuiUrl url) {
		this.url = url;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSecond() {
		return this.second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void cache() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", FuiUrl.getSub(this.url.getServer()));
		dr.AddField("key", this.url.toUrl());
		dr.AddField("content", this.content);
		dr.AddField("date", DateUtil.getDateAndTimeString(new Date()));
		dr.AddField("second", this.second);

		DataSourceProvider.getMongo(this.url.getServer(), "").getEditor(FuiSitesUtil.getDbName(this.url.getServer()),
				"page_cache", new String[] { "sub", "key" }, new String[] { "sub", "key" }).addRecord(dr, true);
	}
}
