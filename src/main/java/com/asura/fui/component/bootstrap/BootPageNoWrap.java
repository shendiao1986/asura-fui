package com.asura.fui.component.bootstrap;

import java.util.HashMap;

import com.asura.tools.util.StringUtil;
import com.asura.tools.util.math.NumberUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataPage;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;


public class BootPageNoWrap extends AbstractUIComponent {
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

		HtmlDiv pre = new HtmlDiv(paras);
		HtmlA pa = new HtmlA(paras);
		pa.setContent("上一页");
		pre.addChild(pa);

		if (StringUtil.isNullOrEmpty((String) m.get(s - 1))) {
			pre.setClass("disabled");
			pa.setUrl("");
		} else {
			pa.setUrl((String) m.get(s - 1));
		}

		for (int i = start; i < Math.min(start + this.count, imax + 1); ++i) {
			String v = (String) m.get(i);

			HtmlA a = new HtmlA(paras);
			a.setUrl(v);
			a.setContent(""+i);
			pre.addChild(a);
		}

		pa = new HtmlA(paras);
		pa.setContent("下一页");
		pre.addChild(pa);

		if (StringUtil.isNullOrEmpty((String) m.get(s + 1))) {
			pa.setUrl("");
		} else {
			pa.setUrl((String) m.get(s + 1));
			if (s + 1 > imax) {
				pa.setUrl("");
			}
		}

		return pre;
	}
}