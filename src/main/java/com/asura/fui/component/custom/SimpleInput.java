package com.asura.fui.component.custom;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataInput;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleInput extends AbstractUIComponent {
	private String type;
	private String value;
	private boolean twoline;
	private boolean bold;
	private boolean readonly;
	private int percent;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		if (this.percent == 0) {
			this.percent = 20;
		}
		DataInput input = (DataInput) data;

		HtmlDiv div = new HtmlDiv(paras);

		HtmlSpan span = new HtmlSpan(paras);
		span.setContent(input.getLabel() + ":");
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

		String tag = "input";
		if ("multi".equals(this.type)) {
			tag = "textarea";
		}
		SimpleHtml html = new SimpleHtml(tag, paras);
		html.addAttr("name", input.getKey());
		html.addAttr("id", input.getKey());

		html.addStyle("float", "left");
		if (this.twoline)
			html.addStyle("width", "100%");
		else {
			html.addStyle("width", (100 - this.percent) + "%");
		}
		if (!(StringUtil.isNullOrEmpty(input.getValue()))) {
			html.addAttr("value", input.getValue());
		}
		if (this.readonly) {
			html.addAttr("readonly", "true");
		}

		if (StringUtil.isNullOrEmpty(this.type)) {
			this.type = "text";
		}
		html.addAttr("type", this.type);

		if (!(StringUtil.isNullOrEmpty(this.value))) {
			html.addAttr("value", ParamterUtil.getValue(this.value, paras));
		}

		addStyle(html, paras);
		addAttr(html, paras);

		div.addChild(html);

		return div;
	}
}
