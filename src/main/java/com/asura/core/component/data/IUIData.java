import com.asura.fui.component.data;

import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;

public interface IUIData {
	public String getKey();

	public void setKey(String paramString);

	public IHtmlElement toElement(IUILayout paramIUILayout, FrontData paramFrontData);
}
