import com.asura.fui.component.custom;

import java.util.HashMap;

import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.DataNavi;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlA;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.HtmlSpan;
import com.asura.core.html.IHtmlElement;
import com.asura.core.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleNavi extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataNavi navi = (DataNavi) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.addStyle("background", "rgb(204,204,204)");

		String url = getValue(navi.getUrl(), paras);

		HashMap<String,String> m = ParamterUtil.convert(url);

		for (String key : m.keySet()) {
			HtmlA a = new HtmlA(paras);
			a.addStyle("width", "100%");
			a.addStyle("float", "left");
			HtmlSpan span = new HtmlSpan(paras);
			span.setContent(key);

			a.addChild(span);

			div.addChild(a);
		}

		return div;
	}
}
