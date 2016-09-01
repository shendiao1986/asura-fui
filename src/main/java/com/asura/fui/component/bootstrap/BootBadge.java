package com.asura.fui.component.bootstrap;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataBadge;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;


public class BootBadge implements IUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataBadge p = (DataBadge) data;

		HtmlDiv div = new HtmlDiv(paras);
		HtmlA a = new HtmlA(paras);

		if (!(StringUtil.isNullOrEmpty(p.getUrl()))) {
			a.addAttr("href", p.getUrl());
		}

		a.setContent(p.getName());

		HtmlSpan span = new HtmlSpan(paras);
		span.setClass("badge");
		span.setContent(p.getCount());

		a.addChild(span);

		div.addChild(a);

		return div;
	}
}
