package com.asura.fui.component.custom;

import java.util.HashMap;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataFilter;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlInput;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;


public class SimpleFilter extends AbstractUIComponent {
	private boolean check;
	private int column;
	private int row;

	public boolean isCheck() {
		return this.check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataFilter lv = (DataFilter) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.addStyle("border", "1px solid rgb(200,200,200)");
		div.addStyle("color", "rgb(107,107,107)");

		HashMap<String,String> wMap = ParamterUtil.convert(lv.getName());

		HashMap list = (HashMap) paras.getValue(lv.getFilter().replace("$", ""));

		if (list != null) {
			for (String s : wMap.keySet()) {
				HtmlDiv dl = new HtmlDiv(paras);

				div.addChild(dl);

				dl.addStyle("border-bottom", "1px dotted rgb(200,200,200)");
				dl.addStyle("background", "rgb(245,245,245)");
				dl.addStyle("float", "left");
				dl.addStyle("width", "100%");

				String label = (String) wMap.get(s);

				HtmlDiv dt = new HtmlDiv(paras);
				dt.setContent(label);
				dt.addStyle("text-align", "center");
				dt.addStyle("color", "rgb(107,107,107)");
				dt.addStyle("line-height", "100%");
				dt.addStyle("float", "left");
				dt.addStyle("width", "15%");
				dt.addStyle("margin-top", "10px");

				dl.addChild(dt);

				HtmlDiv dd = new HtmlDiv(paras);

				dd.addStyle("background", "rgb(255,255,255)");
				dd.addStyle("float", "left");
				dd.addStyle("width", "85%");
				dd.addStyle("padding-left", "5%");

				dl.addChild(dd);

				FrontData fd = (FrontData) list.get(s);
				if (fd == null)
					continue;
				int i = 0;
				for (String f : fd.getAllFields()) {
					String url = fd.getValueString(f);
					HtmlSpan span = new HtmlSpan(paras);
					span.addStyle("width", (100 / this.column) + "%");
					span.addStyle("overflow", "hidden");
					span.addStyle("float", "left");
					span.addStyle("margin-bottom", "10px");

					if (i < this.column) {
						span.addStyle("margin-top", "10px");
					}

					if (this.check) {
						HtmlInput input = new HtmlInput(paras);
						input.addAttr("type", "checkbox");
						input.addAttr("onclick", url);

						span.addChild(input);

						span.addChild(new HtmlSpan(f, paras));
					} else {
						HtmlA a = new HtmlA(paras);
						a.setUrl(url);
						a.addChild(new HtmlSpan(f, paras));

						span.addChild(a);
					}

					dd.addChild(span);

					++i;
				}
			}

		}

		return div;
	}
}
