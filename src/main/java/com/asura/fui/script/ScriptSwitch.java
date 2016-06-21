package com.asura.fui.script;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.FrontData;
import com.cpkf.yyjd.tools.util.StringUtil;

public class ScriptSwitch implements IUIScript {
	private List<ScriptCondition> conditions;

	public String toScript(FrontData paras) {
		List list = new ArrayList();

		int i = 0;
		for (ScriptCondition con : this.conditions) {
			if (i == 0)
				list.add("if(" + con.getCon() + "){\n" + con.toScript(paras) + "\n}");
			else {
				list.add("(" + con.getCon() + "){\n" + con.toScript(paras) + "\n}");
			}
			++i;
		}

		return StringUtil.getStringFromStrings(list, " else if ");
	}
}
