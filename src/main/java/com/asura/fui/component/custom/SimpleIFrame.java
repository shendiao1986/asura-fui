package com.asura.fui.component.custom;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataIFrame;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.FrontData;

public class SimpleIFrame extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataIFrame dif = (DataIFrame) data;

		SimpleHtml sh = new SimpleHtml("iframe", paras);
		sh.addAttr("src", dif.getSrc());

		addStyle(sh, paras);

		return sh;
	}
}