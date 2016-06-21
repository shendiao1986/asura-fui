package com.asura.fui.component.bootstrap;

import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataChart;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public class BootChart implements IUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataChart p = (DataChart) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.addAttr("id", p.getKey());

		return div;
	}
}
