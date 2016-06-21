package com.asura.fui.script;

import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ScriptSimple implements IUIScript {
	private String script;

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String toScript(FrontData paras) {
		if (!(this.script.trim().endsWith(";"))) {
			this.script += ";";
		}

		return ParamterUtil.getValue(this.script, paras);
	}
}
