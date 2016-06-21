package com.asura.fui.component.data;

import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public interface IUIData {
	public String getKey();

	public void setKey(String paramString);

	public IHtmlElement toElement(IUILayout paramIUILayout, FrontData paramFrontData);
}
