package com.asura.fui.component.custom;

import java.util.HashMap;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleNode extends AbstractUIComponent {
	private String node;
	private String content;
	private String wrapNode;
	private String wrapNodeAttrs;
	private String wrapNodeContent;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		IHtmlElement parent = null;
		if (!(StringUtil.isNullOrEmpty(this.wrapNode))) {
			if (!(StringUtil.isNullOrEmpty(this.wrapNodeContent)))
				parent = new SimpleHtml(this.wrapNode, this.wrapNodeContent, paras);
			else {
				parent = new SimpleHtml(this.wrapNode, paras);
			}

			if (!(StringUtil.isNullOrEmpty(this.wrapNodeAttrs))) {
				HashMap<String,String> sMap = ParamterUtil.convert(this.wrapNodeAttrs);
				for (String key : sMap.keySet()) {
					if ("".equals(sMap.get(key)))
						parent.addAttr(key, null);
					else {
						parent.addAttr(key, (String) sMap.get(key));
					}

				}

			}

		}

		SimpleHtml simpleHtml = new SimpleHtml(this.node, this.content, paras);
		if (!(StringUtil.isNullOrEmpty(this.styles))) {
			HashMap<String,String> sMap = ParamterUtil.convert(this.styles);
			for (String key : sMap.keySet()) {
				simpleHtml.addStyle(key, (String) sMap.get(key));
			}
		}

		if (!(StringUtil.isNullOrEmpty(this.attrs))) {
			HashMap<String,String> sMap = ParamterUtil.convert(this.attrs);
			for (String key : sMap.keySet()) {
				if ("".equals(sMap.get(key)))
					simpleHtml.addAttr(key, null);
				else {
					simpleHtml.addAttr(key, (String) sMap.get(key));
				}
			}

		}

		if (parent != null) {
			parent.addChild(simpleHtml);
			return parent;
		}

		return simpleHtml;
	}

	public String getNode() {
		return this.node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWrapNode() {
		return this.wrapNode;
	}

	public void setWrapNode(String wrapNode) {
		this.wrapNode = wrapNode;
	}

	public String getWrapNodeAttrs() {
		return this.wrapNodeAttrs;
	}

	public void setWrapNodeAttrs(String wrapNodeAttrs) {
		this.wrapNodeAttrs = wrapNodeAttrs;
	}

	public String getWrapNodeContent() {
		return this.wrapNodeContent;
	}

	public void setWrapNodeContent(String wrapNodeContent) {
		this.wrapNodeContent = wrapNodeContent;
	}
}
