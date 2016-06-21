package com.asura.fui.component.custom;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public class SimpleHr extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		HtmlDiv div = new HtmlDiv();

		div.addAttr("style", "border-top: 1px solid;color:#ccc; height: 1px; width:100%;float:left;");

		addStyle(div, paras);

		return div;
	}
}
