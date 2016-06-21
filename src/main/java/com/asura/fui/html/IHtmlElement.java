package com.asura.fui.html;

public interface IHtmlElement {
	public abstract String getTag();

	public abstract String toHtml();

	public abstract void addStyle(String name, String value);

	public abstract void addChild(IHtmlElement el);

	public abstract void setContent(String content);

	public abstract void addAttr(String name, String value);

	public abstract String getAttr(String name);
}
