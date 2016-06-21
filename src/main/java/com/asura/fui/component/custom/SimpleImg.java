package com.asura.fui.component.custom;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.basic.DataImg;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlImg;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public class SimpleImg extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataImg img = (DataImg) data;

		HtmlImg hi = new HtmlImg(paras);
		hi.setAlt(img.getAlt());
		hi.setSrc(img.getImg());

		if (img.isLink()) {
			HtmlA a = new HtmlA(paras);
			a.setUrl(img.getUrl());
			a.addChild(hi);

			return a;
		}

		return hi;
	}
}
