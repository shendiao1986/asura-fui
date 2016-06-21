package com.asura.fui.component.bootstrap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataInput;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.FrontData;

public class BootButton extends AbstractUIComponent {
	private String type;
	private String style;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataInput p = (DataInput) data;

		SimpleHtml bt = new SimpleHtml("button", paras);
		if (p.isSubmit())
			bt.addAttr("type", "submit");
		else {
			bt.addAttr("type", "button");
		}
		bt.setContent(p.getLabel());
		if (StringUtil.isNullOrEmpty(this.type)) {
			this.type = "default";
		}
		bt.setClass("btn btn-" + this.type);

		addEvent(bt, p.getKey());

		addStyle(bt, this.style, paras);

		addStyle(bt, paras);

		addAttr(bt, paras);

		return bt;
	}
}
