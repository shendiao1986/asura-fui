package com.asura.fui.component.bootstrap;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataBlock1;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlImg;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;


public class BootBlock1 extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataBlock1 bl1 = (DataBlock1) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.setClass("thumbnail");
		div.addAttr("style", "float: left; width: 250px;");

		HtmlA a = new HtmlA(paras);
		a.setUrl(bl1.getUrl());
		HtmlImg img = new HtmlImg(paras);
		img.setSrc(bl1.getImg());
		img.setAlt(bl1.getTitle());
		a.addChild(img);

		div.addChild(a);

		HtmlDiv d = new HtmlDiv(paras);
		d.setClass("caption");

		div.addChild(d);

		HtmlDiv name = new HtmlDiv();
		a = new HtmlA(paras);
		a.setUrl(bl1.getUrl());
		a.setContent(bl1.getTitle());
		name.addAttr("style", "word-wrap:break-word; height:3em;overflow:hidden;");
		name.addChild(a);
		d.addChild(name);

		if (!(StringUtil.isNullOrEmpty(bl1.getDesc()))) {
			SimpleHtml desc = new SimpleHtml("p", bl1.getDesc(), paras);
			desc.addAttr("style", "overflow:hidden;");
			d.addChild(desc);
		}

		if ((!(StringUtil.isNullOrEmpty(bl1.getButtons()))) && (getButtons(bl1).length > 0)) {
			SimpleHtml p = new SimpleHtml("p", paras);
			for (String btn : getButtons(bl1)) {
				a = new HtmlA(paras);
				a.setUrl(getButtonUrl(bl1, btn));
				a.setClass("btn btn-default");
				a.addAttr("role", "button");
				a.setContent(btn);

				p.addChild(a);
			}
			d.addChild(p);
		}

		return div;
	}

	public String[] getButtons(DataBlock1 bl1) {
		return ((String[]) ParamterUtil.convert(bl1.getButtons()).keySet().toArray(new String[0]));
	}

	public String getButtonUrl(DataBlock1 bl1, String btn) {
		String s = (String) ParamterUtil.convert(bl1.getButtons()).get(btn);
		if (StringUtil.isNullOrEmpty(s)) {
			s = bl1.getUrl();
		}

		return s;
	}
}
