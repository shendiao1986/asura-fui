package com.asura.fui.service.data;

import com.asura.tools.util.StringUtil;

public class ProviderKey {
	private String site;
	private String cat;
	private String key;

	public ProviderKey() {
	}

	public ProviderKey(String cat, String key) {
		this.cat = cat;
		this.key = key;
	}

	public ProviderKey(String site, String cat, String key) {
		this.site = site;
		this.cat = cat;
		this.key = key;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DataProvider getDataProvider() {
		if (StringUtil.isNullOrEmpty(this.site)) {
			return DataProviderCache.getBuilder(this.cat, this.key);
		}
		return DataProviderCache.getBuilder(this.site, this.cat, this.key);
	}
}
