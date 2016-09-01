package com.asura.fui.script;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.FrontData;
import com.asura.tools.util.StringUtil;

public class ScriptFunction implements IUIScript {
	private String name;
	private String vars;
	private List<IUIScript> lines;
	private boolean callback;

	public ScriptFunction() {
	}

	public ScriptFunction(String name, String vars, boolean callback) {
		this.name = name;
		this.vars = vars;
		this.callback = callback;
		this.lines = new ArrayList();
	}

	public void addLine(IUIScript line) {
		this.lines.add(line);
	}

	public boolean isCallback() {
		return this.callback;
	}

	public void setCallback(boolean callback) {
		this.callback = callback;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVars() {
		return this.vars;
	}

	public void setVars(String vars) {
		this.vars = vars;
	}

	public List<IUIScript> getLines() {
		return this.lines;
	}

	public void setLines(List<IUIScript> lines) {
		this.lines = lines;
	}

	public String toScript(FrontData paras) {
		List list = new ArrayList();
		for (IUIScript s : this.lines) {
			list.add(s.toScript(paras));
		}
		if (this.callback) {
			return "function(" + this.vars + "){\n" + StringUtil.getStringFromStrings(list, "\n") + "\n}\n";
		}
		return "function " + this.name + "(" + this.vars + "){\n" + StringUtil.getStringFromStrings(list, "\n")
				+ "\n}\n";
	}
}