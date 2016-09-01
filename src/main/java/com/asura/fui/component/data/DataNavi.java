package com.asura.fui.component.data;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.asura.tools.util.StringUtil;

public class DataNavi extends AbstractUIData {
	private LinkedHashMap<String, String> map;
	private HashMap<String, String> parentMap;
	private String url;
	private String parent;
	private String selected;
	private String head;
	private String right;

	public DataNavi() {
		this.map = new LinkedHashMap();
		this.parentMap = new LinkedHashMap();
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public void addNavi(String name, String url) {
		initial();
		this.map.put(name, url);
	}

	public void addRelation(String child, String parent) {
		initial();
		this.parentMap.put(child, parent);
	}

	public void addNavi(String[] names, String[] urls) {
		initial();
		if (names.length == urls.length)
			for (int i = 0; i < names.length; ++i)
				this.map.put(names[i], urls[i]);
	}

	public String getRight() {
		return this.right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getUrl() {
		return this.url;
	}

	public String getSelected() {
		return this.selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	private synchronized void initial() {
		if (this.map == null) {
			this.map = new LinkedHashMap();
			this.parentMap = new LinkedHashMap();

			if (!(StringUtil.isNullOrEmpty(this.url))) {
				String[] urls = StringUtil.split(this.url, ",");

				for (String url : urls) {
					String[] nu = StringUtil.split(url.trim(), "=");
					if (nu.length == 2) {
						addNavi(nu[0].trim(), nu[1].trim());
					}
				}
			}

			if (!(StringUtil.isNullOrEmpty(this.parent))) {
				String[] ps = StringUtil.split(this.parent, ",");

				for (String url : ps) {
					String[] nu = StringUtil.split(url.trim(), "=");
					if (nu.length == 2)
						addRelation(nu[0].trim(), nu[1].trim());
				}
			}
		}
	}

}
