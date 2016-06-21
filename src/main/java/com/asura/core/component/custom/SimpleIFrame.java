import com.asura.fui.component.custom;

import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.DataIFrame;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.IHtmlElement;
import com.asura.core.html.SimpleHtml;
import com.asura.fui.FrontData;

public class SimpleIFrame extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataIFrame dif = (DataIFrame) data;

		SimpleHtml sh = new SimpleHtml("iframe", paras);
		sh.addAttr("src", dif.getSrc());

		addStyle(sh, paras);

		return sh;
	}
}