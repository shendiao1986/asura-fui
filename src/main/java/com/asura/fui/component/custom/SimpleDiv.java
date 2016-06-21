package com.asura.fui.component.custom;

import java.util.HashMap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleDiv extends AbstractUIComponent {
	private String styles;
	private String attrs;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		HtmlDiv div = new HtmlDiv();

		if (!(StringUtil.isNullOrEmpty(this.styles))) {
			HashMap<String,String> sMap = ParamterUtil.convert(this.styles);
			for (String key : sMap.keySet()) {
				div.addStyle(key, (String) sMap.get(key));
			}
		}

		if (!(StringUtil.isNullOrEmpty(this.attrs))) {
			HashMap<String,String> sMap = ParamterUtil.convert(this.attrs);
			for (String key : sMap.keySet()) {
				div.addAttr(key, (String) sMap.get(key));
			}
		}

		return div;
	}
}
