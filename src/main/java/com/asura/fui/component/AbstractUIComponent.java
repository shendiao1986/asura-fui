package com.asura.fui.component;

import java.util.HashMap;
import java.util.List;

import com.asura.tools.util.StringUtil;
import com.asura.fui.component.event.UIEvent;
import com.asura.fui.component.event.UIEvents;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;


public abstract class AbstractUIComponent implements IUIComponent {
	protected UIEvents events;
	protected String styles;
	protected String attrs;
	protected String className;
	protected boolean hidden;

	public UIEvents getEvents() {
		return this.events;
	}

	public void setEvents(UIEvents events) {
		this.events = events;
	}

	protected void addEvent(IHtmlElement element, String key) {
		if (this.events != null) {
			List<UIEvent> list = this.events.getEvent(key);
			for (UIEvent ev : list)
				element.addAttr(ev.getName(), ev.getEvent());
		}
	}

	protected void setClass(IHtmlElement el, FrontData paras) {
		if (!(StringUtil.isNullOrEmpty(this.className)))
			el.addAttr("class", ParamterUtil.getValue(this.className, paras));
	}

	protected void addAttr(IHtmlElement el, FrontData paras) {
		addAttr(el, this.attrs, paras);
	}

	protected void addStyle(IHtmlElement el, FrontData paras) {
		addStyle(el, this.styles, paras);
	}

	protected void addAttr(IHtmlElement el, String attr, FrontData paras) {
		if (!(StringUtil.isNullOrEmpty(attr))) {
			HashMap<String,String> map = ParamterUtil.convert(attr);
			for (String key : map.keySet())
				el.addAttr(key, getValue((String) map.get(key), paras));
		}
	}

	protected void addStyle(IHtmlElement el, String style, FrontData paras) {
		if (!(StringUtil.isNullOrEmpty(style))) {
			HashMap<String,String> map = ParamterUtil.convert(style);
			for (String key : map.keySet())
				el.addStyle(key, getValue((String) map.get(key), paras));
		}
	}

	protected String getValue(String value, FrontData paras) {
		if (value == null) {
			value = "";
		}
		String t = value;
		for (String p : paras.getAllFields()) {
			t = t.replace("$" + p + "$", paras.getValueString(p));
		}

		if ((t.trim().startsWith("$")) && (t.trim().endsWith("$")) && (t.length() < 20)) {
			t = "";
		}

		return t;
	}

	public String getStyles() {
		return this.styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getAttrs() {
		return this.attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

}
