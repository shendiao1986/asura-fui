import com.asura.fui.component;

import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;

public interface IUIComponent {
	public IHtmlElement toHtml(IUIData paramIUIData, IUILayout paramIUILayout, FrontData paramFrontData);
}
