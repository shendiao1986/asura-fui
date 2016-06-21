package com.asura.fui.script;

import com.asura.fui.FrontData;

public class ScriptParseJson implements IUIScript {
	private String in;
	private String out;

	public ScriptParseJson() {
	}

	public ScriptParseJson(String in, String out) {
		this.in = in;
		this.out = out;
	}

	public String getIn() {
		return this.in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getOut() {
		return this.out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String toScript(FrontData paras) {
		return "var " + this.out + " = $.parseJSON(" + this.in + ");";
	}
}
