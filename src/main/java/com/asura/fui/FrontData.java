package com.asura.fui;

import java.util.HashMap;

public class FrontData {

	private HashMap<String, Object> dataMap;

	public FrontData() {
		this.dataMap = new HashMap();
	}
	
	public String[] getAllFields() {
		return ((String[]) this.dataMap.keySet().toArray(new String[0]));
	}

	public Object getValue(String field) {
		Object obj = this.dataMap.get(field);
		if (obj == null) {
			return null;
		}

		return obj;
	}
	
	public void merge(FrontData data) {
		for (String key : data.getAllFields())
			AddField(key, data.getValue(key));
	}
	
	public String getValueString(String field) {
		if ((this.dataMap.containsKey(field)) && (this.dataMap.get(field) != null)) {
			return this.dataMap.get(field).toString();
		}
		return "";
	}
	
	public void deleteField(String field) {
		this.dataMap.remove(field);
	}

	public boolean containsField(String field) {
		return this.dataMap.containsKey(field);
	}

	public void AddField(String field, Object value) {
		this.dataMap.put(field, value);
	}

	public String toString() {
		return this.dataMap.toString();
	}
	
	public FrontData clone() {
		FrontData dr = new FrontData();
		dr.dataMap = ((HashMap) this.dataMap.clone());

		return dr;
	}
	
}
