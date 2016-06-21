package com.asura.fui.component;

import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.FrontData;

public interface IUIComponent {
	public IHtmlElement toHtml(IUIData paramIUIData, IUILayout paramIUILayout, FrontData paramFrontData);
}
