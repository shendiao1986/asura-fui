package com.asura.fui.apps.sites.service;

import com.asura.tools.data.DataRecord;

public class MongoTagMap extends AbstractSitebean {
	private String sub;
	private String tag;
	private String aId;
	private String date;

	public MongoTagMap() {
	}

	public MongoTagMap(String sub, String tag, String id, String date) {
		this.sub = sub;
		this.tag = tag;
		this.aId = id;
		this.date = date;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
		dr.AddField("tag", this.tag);
		dr.AddField("a_id", this.aId);
		dr.AddField("date", this.date);

		return dr;
	}

	public String getTableName() {
		return "tag_article";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "tag", "date" };
	}

	public String[] getKeys() {
		return new String[] { "sub", "tag", "a_id" };
	}

}
