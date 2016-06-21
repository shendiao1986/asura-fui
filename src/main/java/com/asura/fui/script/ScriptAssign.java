package com.asura.fui.script;

import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ScriptAssign implements IUIScript {
	private String name;
	private String value;
	private boolean first;
	private boolean quote;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isFirst() {
		return this.first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public String toScript(FrontData paras) {
		String v = ParamterUtil.getValue(this.value, paras);
		if (this.quote) {
			v = "'" + v + "'";
		}
		if (this.first) {
			return "var " + this.name + " = " + v + ";";
		}
		return this.name + " = " + v + ";";
	}
}