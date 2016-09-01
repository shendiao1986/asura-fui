package com.asura.fui.component.data;

import java.util.List;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;


public class DataForm extends AbstractUIData {
	private String id;
	private String action;
	private String method;
	private List<IUIData> set;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public IHtmlElement toElement(IUILayout layout, FrontData paras) {
		SimpleHtml div = new SimpleHtml("form", paras);

		if (StringUtil.isNullOrEmpty(this.id)) {
			this.id = getKey();
		}

		div.addAttr("id", this.id);

		FuiUrl url = (FuiUrl) paras.getValue("fui-url");

		if (StringUtil.isNullOrEmpty(this.action)) {
			this.action = url.toUrlBase() + "/post";
		}

		div.addAttr("action", this.action);

		if (StringUtil.isNullOrEmpty(this.method)) {
			this.method = "post";
		}
		div.addAttr("method", this.method);

		UIDatas datas = new UIDatas();
		for (IUIData d : this.set) {
			datas.addUIData(d);
		}

		layout.toHtml(div, datas, paras, false);

		SimpleHtml input = new SimpleHtml("input", paras);
		input.addAttr("type", "hidden");
		input.addAttr("name", "post_id");
		input.addAttr("value", this.id);

		div.addChild(input);

		return div;
	}
}
