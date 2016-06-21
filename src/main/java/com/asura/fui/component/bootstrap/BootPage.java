package com.asura.fui.component.bootstrap;

import java.util.HashMap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.cpkf.yyjd.tools.util.math.NumberUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataPage;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlLi;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class BootPage extends AbstractUIComponent {
	private int count;
	private String max;

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		int imax = NumberUtil.getInt(getValue(this.max, paras));
		if (imax == 0) {
			imax = 100;
		}
		DataPage p = (DataPage) data;

		HashMap m = ParamterUtil.convert(ParamterUtil.getValue(p.getUrl(), paras));

		int s = NumberUtil.getInt(ParamterUtil.getValue(p.getSelected(), paras));

		int start = (s - 1) / this.count * this.count + 1;

		HtmlUL ul = new HtmlUL(paras);
		ul.setClass("pagination");

		HtmlLi pre = new HtmlLi(paras);
		HtmlA pa = new HtmlA(paras);
		pa.setContent("上一页");
		pre.addChild(pa);

		if (StringUtil.isNullOrEmpty((String) m.get(s - 1))) {
			pre.setClass("disabled");
			pa.setUrl("");
		} else {
			pa.setUrl((String) m.get(s - 1));
		}

		ul.addChild(pre);

		for (int i = start; i < Math.min(start + this.count, imax + 1); ++i) {
			String v = (String) m.get(i);
			HtmlLi li = new HtmlLi(paras);
			if (i == s) {
				li.setClass("active");
			}
			HtmlA a = new HtmlA(paras);
			a.setUrl(v);
			a.setContent(""+i);
			li.addChild(a);
			ul.addChild(li);
		}

		HtmlLi next = new HtmlLi(paras);
		pa = new HtmlA(paras);
		pa.setContent("下一页");
		next.addChild(pa);

		if (StringUtil.isNullOrEmpty((String) m.get(s + 1))) {
			next.setClass("disabled");
			pa.setUrl("");
		} else {
			pa.setUrl((String) m.get(s + 1));
			if (s + 1 > imax) {
				next.setClass("disabled");
				pa.setUrl("");
			}
		}

		ul.addChild(next);

		return ul;
	}
}
