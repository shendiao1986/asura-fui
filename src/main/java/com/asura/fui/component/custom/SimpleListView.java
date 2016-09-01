package com.asura.fui.component.custom;

import java.util.HashMap;
import java.util.List;

import com.asura.tools.util.StringUtil;
import com.asura.tools.util.math.NumberUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataListView;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleListView extends AbstractUIComponent {
	private String width;
	private String headerStyle;
	private String columnStyle;
	private IListViewColumn column;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataListView lv = (DataListView) data;

		HtmlDiv div = new HtmlDiv(paras);

		if (!(StringUtil.isNullOrEmpty(lv.getTitle()))) {
			div.addChild(new SimpleHtml("h4", getValue(lv.getTitle(), paras)));
		}

		HashMap<String,String> hMap = ParamterUtil.convert(ParamterUtil.getValue(lv.getHeader(), paras));
		HashMap<String,String> wMap = ParamterUtil.convert(ParamterUtil.getValue(this.width, paras));

		handleHeader(div, paras, hMap, wMap);

		List<FrontData> list = (List<FrontData>) paras.getValue(lv.getColumns().replace("$", ""));

		if (list != null) {
			int i = 0;
			for (FrontData fd : list) {
				HtmlDiv column = new HtmlDiv(paras);
				column.addStyle("margin-top", "10px");
				column.addStyle("float", "left");
				column.addStyle("width", "100%");
				column.addStyle("height", "20px");

				for (String key : ParamterUtil.convert(this.columnStyle).keySet()) {
					column.addStyle(key, (String) ParamterUtil.convert(this.columnStyle).get(key));
				}

				for (String key : hMap.keySet()) {
					HtmlDiv item = new HtmlDiv(paras);
					item.addStyle("width", ((String) wMap.get(key)) + "%");
					item.addStyle("float", "left");
					item.addStyle("height", getHeight() + "px");
					item.addStyle("line-height", getHeight() + "px");
					item.addStyle("white-space", "nowrap");
					item.addStyle("overflow", "hidden");

					IUIData ud = (IUIData) fd.getValue(key);
					if (ud != null) {
						IHtmlElement el = ud.toElement(layout, paras);
						el.addStyle("line-height", getHeight() + "px");
						el.addStyle("height", getHeight() + "px");
						item.addChild(el);
					}

					column.addChild(item);
				}

				if (this.column == null) {
					this.column = new ListViewSimpleColumn();
				}
				this.column.decorate(column, fd, i);

				div.addChild(column);
			}
		}

		return div;
	}

	private int getHeight() {
		if (!(StringUtil.isNullOrEmpty(this.columnStyle))) {
			HashMap m = ParamterUtil.convert(this.columnStyle);
			if (m.containsKey("height")) {
				return NumberUtil.getInt((String) m.get("height"));
			}
		}

		return 30;
	}

	private void handleHeader(HtmlDiv div, FrontData paras, HashMap<String, String> hMap,
			HashMap<String, String> wMap) {
		HtmlDiv header = new HtmlDiv(paras);
		header.addStyle("width", "100%");
		header.addStyle("float", "left");
		header.addStyle("background-color", "#888888");
		header.addStyle("font-size", "18");

		for (String key : ParamterUtil.convert(this.headerStyle).keySet()) {
			header.addStyle(key, (String) ParamterUtil.convert(this.headerStyle).get(key));
		}

		for (String key : hMap.keySet()) {
			HtmlDiv ele = new HtmlDiv();
			ele.addStyle("width", ((String) wMap.get(key)) + "%");
			ele.addStyle("float", "left");

			HtmlSpan span = new HtmlSpan(paras);
			span.setContent((String) hMap.get(key));

			ele.addChild(span);

			header.addChild(ele);
		}

		div.addChild(header);
	}
}
