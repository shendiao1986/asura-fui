package com.asura.fui.html;

import com.asura.fui.FrontData;

public class HtmlImg extends AbstractHtmlElement {
	public HtmlImg() {
	}

	public HtmlImg(FrontData paras) {
		super(paras);
	}

	public String getTag() {
		return "img";
	}

	public void setSrc(String src) {
		addAttr("src", src);
	}

	public void setAlt(String alt) {
		addAttr("alt", alt);
	}
}
