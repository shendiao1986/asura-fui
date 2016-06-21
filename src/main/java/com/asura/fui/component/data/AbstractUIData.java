package com.asura.fui.component.data;

import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public abstract class AbstractUIData implements IUIData {
	protected IUIComponent component;
	protected String key;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IHtmlElement toElement(IUILayout layout, FrontData paras) {
		return this.component.toHtml(this, layout, paras);
	}

	public IUIComponent getComponent() {
		return this.component;
	}

	public void setComponent(IUIComponent component) {
		this.component = component;
	}
}
