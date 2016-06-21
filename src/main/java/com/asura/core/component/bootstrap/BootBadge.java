import com.asura.fui.component.bootstrap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.component.IUIComponent;
import com.asura.core.component.data.DataBadge;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlA;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.HtmlSpan;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;


public class BootBadge implements IUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataBadge p = (DataBadge) data;

		HtmlDiv div = new HtmlDiv(paras);
		HtmlA a = new HtmlA(paras);

		if (!(StringUtil.isNullOrEmpty(p.getUrl()))) {
			a.addAttr("href", p.getUrl());
		}

		a.setContent(p.getName());

		HtmlSpan span = new HtmlSpan(paras);
		span.setClass("badge");
		span.setContent(p.getCount());

		a.addChild(span);

		div.addChild(a);

		return div;
	}
}
