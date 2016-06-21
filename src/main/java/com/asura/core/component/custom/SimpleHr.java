import com.asura.fui.component.custom;

import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;

public class SimpleHr extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		HtmlDiv div = new HtmlDiv();

		div.addAttr("style", "border-top: 1px solid;color:#ccc; height: 1px; width:100%;float:left;");

		addStyle(div, paras);

		return div;
	}
}
