package com.asura.fui.component.bootstrap;

import java.util.HashMap;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlLi;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;


public class BootTab extends AbstractUIComponent {
	private boolean pill;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataNavi navi = (DataNavi) data;

		HtmlUL ul = new HtmlUL(paras);

		if (this.pill)
			ul.addAttr("class", "nav nav-pills");
		else {
			ul.addAttr("class", "nav nav-tabs");
		}

		String url = getValue(navi.getUrl(), paras);

		HashMap<String,String> m = ParamterUtil.convert(url);

		for (String n : m.keySet()) {
			String u = (String) m.get(n);
			HtmlLi li = new HtmlLi(paras);
			if (n.equals(getValue(navi.getSelected(), paras))) {
				li.addAttr("class", "active");
			}
			HtmlA a = new HtmlA(paras);
			a.addAttr("href", u);
			a.setContent(n);
			li.addChild(a);
			ul.addChild(li);
		}

		return ul;
	}
}
