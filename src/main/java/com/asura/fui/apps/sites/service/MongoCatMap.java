package com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public class MongoCatMap extends AbstractSitebean {
	private String sub;
	private String cat;
	private String aId;
	private String date;

	public MongoCatMap() {
	}

	public MongoCatMap(String sub, String cat, String id, String date) {
		this.sub = sub;
		this.cat = cat;
		this.aId = id;
		this.date = date;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getAId() {
		return this.aId;
	}

	public void setAId(String id) {
		this.aId = id;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", this.sub);
		dr.AddField("cat", this.cat);
		dr.AddField("a_id", this.aId);
		dr.AddField("date", this.date);

		return dr;
	}

	public String getTableName() {
		return "cat_article";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "cat", "date" };
	}

	public String[] getKeys() {
		return new String[] { "sub", "cat", "a_id" };
	}
}
