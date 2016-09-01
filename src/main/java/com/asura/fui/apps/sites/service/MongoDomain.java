package com.asura.fui.apps.sites.service;

import com.asura.tools.data.DataRecord;

public class MongoDomain extends AbstractSitebean {
	private String site;
	private String auth;
	private String date;

	public MongoDomain() {
	}

	public MongoDomain(String site, String auth, String date) {
		this.site = site;
		this.auth = auth;
		this.date = date;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("site", this.site);
		dr.AddField("auth", this.auth);
		dr.AddField("date", this.date);

		return dr;
	}

	public String getDbName(String domain, String sub) {
		return "sites";
	}

	public String getTableName() {
		return "sites";
	}

	public String[] getIndexes() {
		return new String[] { "site", "auto", "date" };
	}

	public String[] getKeys() {
		return new String[] { "site" };
	}

}
