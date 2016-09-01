package com.asura.fui.html;

import com.asura.fui.FrontData;
import com.asura.tools.util.StringUtil;

public class HtmlScript extends AbstractHtmlElement {
	public static final String CSS = "text/css";
	public static final String JS = "text/javascript";

	public HtmlScript() {
	}

	public HtmlScript(String type, String content) {
		addAttr("type", type);
		if (!(StringUtil.isNullOrEmpty(content)))
			setContent(content);
	}

	public HtmlScript(String type, String content, FrontData paras) {
		super(paras);
		addAttr("type", type);
		if (!(StringUtil.isNullOrEmpty(content)))
			setContent(content);
	}

	public String getTag() {
		return "script";
	}
}
