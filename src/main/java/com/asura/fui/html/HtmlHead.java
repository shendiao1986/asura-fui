package com.asura.fui.html;

import com.asura.fui.FrontData;

public class HtmlHead extends AbstractHtmlElement {
	public HtmlHead() {
		HtmlMeta meta = new HtmlMeta();
		meta.addAttr("http-equiv", "Content-Type");
		meta.addAttr("content", "text/html; charset=UTF-8");
		addChild(meta);
	}

	public HtmlHead(FrontData paras) {
		super(paras);
		HtmlMeta meta = new HtmlMeta();
		meta.addAttr("http-equiv", "Content-Type");
		meta.addAttr("content", "text/html; charset=UTF-8");
		addChild(meta);
	}

	public String getTag() {
		return "head";
	}

	public void setCharset(String charset) {
		removeChild("meta", new String[] { "http-equiv" }, new String[] { "Content-Type" });
		HtmlMeta meta = new HtmlMeta();
		meta.addAttr("http-equiv", "Content-Type");
		meta.addAttr("content", "text/html; charset=" + charset);
		addChild(meta);
	}

	public void setTitle(String title) {
		removeChild("tag", new String[0], new String[0]);
		addChild(new SimpleHtml("title", title));
	}

	public void setKeyword(String keyword) {
		removeChild("meta", new String[] { "name" }, new String[] { "keyword" });

		HtmlMeta meta = new HtmlMeta();
		meta.addAttr("name", "keyword");
		meta.addAttr("content", keyword);
		addChild(meta);
	}

	public void setDesc(String desc) {
		removeChild("meta", new String[] { "name" }, new String[] { "description" });

		HtmlMeta meta = new HtmlMeta();
		meta.addAttr("name", "description");
		meta.addAttr("content", desc);
		addChild(meta);
	}

	public void addJavaScript(String js) {
		addChild(new HtmlScript("text/javascript", js));
	}

	public void addCss(String css) {
		addChild(new HtmlScript("text/css", css));
	}

	public void addJSRef(String ref) {
		HtmlScript hs = new HtmlScript("text/javascript", "");
		hs.addAttr("src", ref);

		addChild(hs);
	}

	public void addCssRef(String ref) {
		SimpleHtml hs = new SimpleHtml("link");
		hs.addAttr("type", "text/css");
		hs.addAttr("rel", "stylesheet");
		hs.addAttr("href", ref);

		addChild(hs);
	}

}
