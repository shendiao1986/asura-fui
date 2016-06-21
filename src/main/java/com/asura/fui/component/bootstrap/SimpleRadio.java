package com.asura.fui.component.bootstrap;

import java.util.HashMap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataButtonGroup;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlInput;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleRadio extends AbstractUIComponent {
	private String click;
	private int ml;

	public String getClick() {
		return this.click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		HtmlDiv div = new HtmlDiv();
		div.setClass("btn-group");

		DataButtonGroup dr = (DataButtonGroup) data;

		HashMap<String,String> map = ParamterUtil.convert(ParamterUtil.getValue(dr.getKeyLabels(), paras));

		HashMap<String,String> uMap = ParamterUtil.convert(ParamterUtil.getValue(dr.getKeyUrls(), paras));

		HashMap<String,String> dMap = ParamterUtil.convert(ParamterUtil.getValue(dr.getKeyDescs(), paras));

		int i = 0;
		for (String key : map.keySet()) {
			HtmlDiv out = new HtmlDiv();
			HtmlInput input = new HtmlInput();
			input.setType("radio");
			input.addAttr("name", dr.getGroup());
			input.setContent((String) map.get(key));
			input.addAttr("alt", (String) map.get(key));
			if (!(StringUtil.isNullOrEmpty((String) uMap.get(key)))) {
				input.addAttr("onclick", (String) uMap.get(key));
			}
			if (!(StringUtil.isNullOrEmpty((String) dMap.get(key)))) {
				input.addAttr("title", (String) dMap.get(key));
			}
			input.addAttr("id", key);

			if (key.equals(dr.getSelected())) {
				input.addAttr("checked", "true");
			}

			addEvent(input, key);

			if ((this.ml > 0) && (i > 0)) {
				out.addStyle("margin-left", this.ml + "px");
			}
			if (!(StringUtil.isNullOrEmpty(this.click))) {
				input.addAttr("onclick", this.click);
			}

			out.addStyle("float", "left");
			out.addChild(input);

			div.addChild(out);
			++i;
		}

		return div;
	}
}
