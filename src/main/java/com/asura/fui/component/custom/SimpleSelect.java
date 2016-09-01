package com.asura.fui.component.custom;

import java.util.HashMap;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleSelect extends AbstractUIComponent {
	private boolean twoline;
	private boolean bold;
	private int percent;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		if (this.percent == 0) {
			this.percent = 20;
		}

		SimpleHtml select = new SimpleHtml("select", paras);

		DataNavi navi = (DataNavi) data;

		HtmlDiv div = new HtmlDiv(paras);

		HtmlSpan span = new HtmlSpan(paras);
		span.setContent(navi.getHead() + ":");
		span.addStyle("float", "left");
		span.addStyle("overflow", "hidden");
		if (this.twoline)
			span.addStyle("width", "100%");
		else {
			span.addStyle("width", this.percent + "%");
		}
		if (this.bold) {
			span.addStyle("font-weight", "bold");
		}
		div.addChild(span);

		select.addAttr("id", navi.getKey());
		select.addAttr("name", navi.getKey());

		String selected = "";
		if (!(StringUtil.isNullOrEmpty(navi.getSelected()))) {
			selected = ParamterUtil.getValue(navi.getSelected(), paras);
		}

		HashMap<String,String> m = ParamterUtil.convert(getValue(navi.getUrl(), paras));
		for (String key : m.keySet()) {
			SimpleHtml op = new SimpleHtml("option", paras);
			op.addAttr("value", key);
			op.setContent(key);
			if ((!(StringUtil.isNullOrEmpty(selected))) && (selected.equals(key))) {
				op.addAttr("selected", "true");
			}

			select.addChild(op);
		}

		select.addStyle("float", "left");
		select.addStyle("height", "26px");
		if (this.twoline)
			select.addStyle("width", "100%");
		else {
			select.addStyle("width", (100 - this.percent) + "%");
		}

		div.addChild(select);

		addAttr(select, paras);
		addStyle(select, paras);

		return div;
	}
}
