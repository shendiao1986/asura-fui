package com.asura.fui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.asura.fui.util.ParamterUtil;
import com.asura.tools.util.StringUtil;
import com.asura.tools.util.math.NumberUtil;

public class FuiCss {
	private HashMap<String, String> map;

	public FuiCss() {
		this.map = new LinkedHashMap<>();
	}

	public void add(String key, String value) {
		this.map.put(key, value);
	}

	public void addIgnore(String key, String value) {
		if (!(this.map.containsKey(key)))
			this.map.put(key, value);
	}

	public String[] getFields() {
		return ((String[]) this.map.keySet().toArray(new String[0]));
	}

	public String getValue(String field) {
		return ((String) this.map.get(field));
	}

	public void add(String style) {
		String[] ss = StringUtil.split(style, ";");
		for (String s : ss)
			if (!(StringUtil.isNullOrEmpty(s))) {
				String[] vs = StringUtil.split(s, ":");
				if (vs.length == 2)
					add(vs[0].trim(), vs[1].trim());
			}
	}

	public int getWidth() {
		String w = getValue("width");

		return NumberUtil.getInt(w);
	}

	public String toStyle(FrontData data) {
		List list = new ArrayList();
		for (String key : this.map.keySet()) {
			list.add(key + ": " + ParamterUtil.getValue((String) this.map.get(key), data) + ";");
		}

		return StringUtil.getStringFromStrings(list, " ");
	}
}
