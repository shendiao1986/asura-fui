package com.asura.fui.html;

public class FuiHtml extends AbstractHtmlElement {

	public void setHead(HtmlHead head) {
		this.children.add(0, head);
	}

	public HtmlHead getHead() {
		for (IHtmlElement html : this.children) {
			if (html.getTag().equals("head")) {
				return ((HtmlHead) html);
			}
		}

		setHead(new HtmlHead());

		return getHead();
	}

	public void setBody(HtmlBody body) {
		this.children.add(body);
	}

	public HtmlBody getBody() {
		for (IHtmlElement html : this.children) {
			if (html.getTag().equals("body")) {
				return ((HtmlBody) html);
			}
		}

		HtmlBody bo = new HtmlBody();
		bo.addStyle("height", "100%");
		setBody(bo);

		return getBody();
	}
	
	@Override
	public String getTag() {
		return "html";
	}

}
