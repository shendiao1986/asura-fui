package com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public class MongoArticle extends AbstractSitebean {
	private String sub;
	private String id;
	private String postId;
	private String title;
	private String keyword;
	private String desc;
	private String excerpt;
	private String content;
	private String auth;
	private String date;

	public MongoArticle() {
	}

	public MongoArticle(String sub, String id, String postId, String title, String keyword, String desc, String excerpt,
			String content, String auth, String date) {
		this.sub = sub;
		this.id = id;
		this.postId = postId;
		this.title = title;
		this.keyword = keyword;
		this.desc = desc;
		this.excerpt = excerpt;
		this.content = content;
		this.auth = auth;
		this.date = date;
	}

	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExcerpt() {
		return this.excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", this.sub);
		dr.AddField("id", this.id);
		dr.AddField("title", this.title);
		dr.AddField("keyword", this.keyword);
		dr.AddField("desc", this.desc);
		dr.AddField("postId", this.postId);
		dr.AddField("excerpt", this.excerpt);
		dr.AddField("content", this.content);
		dr.AddField("auth", this.auth);
		dr.AddField("date", this.date);

		return dr;
	}

	public String getTableName() {
		return "article";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "title", "date", "id" };
	}

	public String[] getKeys() {
		return new String[] { "id" };
	}
}
