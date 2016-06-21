package com.asura.fui.component.bootstrap;

import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataPanel;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlNavi;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.FrontData;

public class BootPanel implements IUIComponent {
	private boolean head;
	private boolean tail;
	private String style;

	public boolean isHead() {
		return this.head;
	}

	public void setHead(boolean head) {
		this.head = head;
	}

	public boolean isTail() {
		return this.tail;
	}

	public void setTail(boolean tail) {
		this.tail = tail;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataPanel p = (DataPanel) data;

		HtmlNavi panel = new HtmlNavi(paras);

		String s = this.style;
		try {
			s = PanelType.valueOf(s).name().toLowerCase();
		} catch (Exception e) {
			s = "default";
		}
		panel.addAttr("class", "panel panel-" + s);

		if (this.head) {
			HtmlDiv ph = new HtmlDiv(paras);

			ph.addAttr("class", "panel-heading");

			SimpleHtml h = new SimpleHtml("h3", paras);
			h.setContent(p.getHead());
			h.setClass("panel-title");

			ph.addChild(h);

			panel.addChild(ph);
		}

		HtmlDiv body = new HtmlDiv(paras);
		body.addAttr("class", "panel-body");

		if (p.getChildren() != null) {
			UIDatas datas = new UIDatas();
			for (IUIData el : p.getChildren()) {
				datas.addUIData(el);
			}
			layout.toHtml(body, datas, paras, true);
		}

		panel.addChild(body);

		if (this.tail) {
			HtmlDiv ph = new HtmlDiv(paras);

			ph.addAttr("class", "panel-footer");

			SimpleHtml h = new SimpleHtml("h3", paras);
			h.setContent(p.getTail());
			h.setClass("panel-title");

			ph.addChild(h);

			panel.addChild(ph);
		}

		return panel;
	}

	public static enum PanelType {
		primary, success, info, warning, danger, Default;
	}
}
