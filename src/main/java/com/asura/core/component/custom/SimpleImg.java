import com.asura.fui.component.custom;

import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.data.basic.DataImg;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlA;
import com.asura.core.html.HtmlImg;
import com.asura.core.html.IHtmlElement;
import com.asura.fui.FrontData;

public class SimpleImg extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataImg img = (DataImg) data;

		HtmlImg hi = new HtmlImg(paras);
		hi.setAlt(img.getAlt());
		hi.setSrc(img.getImg());

		if (img.isLink()) {
			HtmlA a = new HtmlA(paras);
			a.setUrl(img.getUrl());
			a.addChild(hi);

			return a;
		}

		return hi;
	}
}
