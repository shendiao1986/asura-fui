import com.asura.fui.component.bootstrap;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.DataProgress;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;


public class BootProgress extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataProgress dp = (DataProgress) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.setClass("progress");

		HtmlDiv pro = new HtmlDiv(paras);
		pro.setClass("progress-bar");
		pro.addStyle("width", dp.getPercent() + "%");

		if (StringUtil.isNullOrEmpty(dp.getCount()))
			pro.setContent(dp.getPercent() + "%");
		else {
			pro.setContent(dp.getCount());
		}

		addStyle(pro, paras);

		div.addChild(pro);

		return div;
	}
}
