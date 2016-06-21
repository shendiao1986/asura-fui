package com.asura.fui.component.custom;

import java.util.HashMap;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleNavi extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataNavi navi = (DataNavi) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.addStyle("background", "rgb(204,204,204)");

		String url = getValue(navi.getUrl(), paras);

		HashMap<String,String> m = ParamterUtil.convert(url);

		for (String key : m.keySet()) {
			HtmlA a = new HtmlA(paras);
			a.addStyle("width", "100%");
			a.addStyle("float", "left");
			HtmlSpan span = new HtmlSpan(paras);
			span.setContent(key);

			a.addChild(span);

			div.addChild(a);
		}

		return div;
	}
}
