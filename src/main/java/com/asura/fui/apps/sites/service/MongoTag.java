package com.asura.fui.apps.sites.service;


public class MongoTag extends MongoCat {
	public MongoTag() {
	}

	public MongoTag(String sub, String name, String alias, String keyword, String desc) {
		this.sub = sub;
		this.name = name;
		this.alias = alias;
		this.keyword = keyword;
		this.desc = desc;
	}

	public String getTableName() {
		return "tag";
	}
}