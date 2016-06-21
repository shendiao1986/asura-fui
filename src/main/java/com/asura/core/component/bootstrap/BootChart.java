import com.asura.fui.component.bootstrap;

import com.asura.core.component.IUIComponent;
import com.asura.core.component.data.DataChart;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;

public class BootChart implements IUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataChart p = (DataChart) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.addAttr("id", p.getKey());

		return div;
	}
}
