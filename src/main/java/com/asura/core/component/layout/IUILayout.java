import com.asura.fui.component.layout;

import java.util.List;

import com.asura.core.component.data.UIDatas;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;


public interface IUILayout {
	public void merge(IUILayout paramIUILayout);

	public IHtmlElement toHtml(UIDatas paramUIDatas, FrontData paramFrontData);

	public void toHtml(IHtmlElement paramIHtmlElement, UIDatas paramUIDatas, FrontData paramFrontData,
			boolean paramBoolean);

	public void handleList(IHtmlElement paramIHtmlElement, String paramString1, List<IHtmlElement> paramList,
			String paramString2, int paramInt1, int paramInt2, FrontData paramFrontData, String paramString3);
}
