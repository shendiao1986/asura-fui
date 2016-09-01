package com.asura.fui.component.custom;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.data.basic.DataText;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.FrontData;

public class ListViewConditionColumn implements IListViewColumn {
	private String field;
	private String values;
	private String colors;

	public void decorate(HtmlDiv column, FrontData data, int index) {
		String[] vs = StringUtil.split(this.values, "|");
		String[] cs = StringUtil.split(this.colors, "|");
		if (cs.length == vs.length) {
			if (data.getValue(this.field) instanceof DataText) {
				DataText st = (DataText) data.getValue(this.field);
				boolean done = false;
				for (int i = 0; i < cs.length; ++i) {
					if (vs[i].equals(st.getText())) {
						column.addStyle("background-color", cs[i]);
						done = true;
					}
				}
				if (!(done))
					column.addStyle("background-color", "rgb(242,242,242)");
			}
		} else
			new ListViewSimpleColumn().decorate(column, data, index);
	}
}
