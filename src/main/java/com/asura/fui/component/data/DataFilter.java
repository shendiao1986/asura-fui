package com.asura.fui.component.data;

public class DataFilter extends AbstractUIData {
	private String name;
	private String filter;
	private String filterKey;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilterKey() {
		return this.filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}
}
