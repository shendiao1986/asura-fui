import com.asura.fui.component.bootstrap;

import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.DataDialog;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.data.UIDatas;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlDiv;
import com.asura.core.html.IHtmlElement;
import com.asura.core.html.SimpleHtml;
import com.asura.fui.FrontData;

public class BootDialog extends AbstractUIComponent {
	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataDialog dd = (DataDialog) data;

		HtmlDiv div = new HtmlDiv(paras);
		div.setClass("modal fade");
		div.addAttr("id", dd.getId());
		div.addAttr("tabindex", "-1");
		div.addAttr("role", "dialog");
		div.addAttr("aria-hidden", "true");

		HtmlDiv dia = new HtmlDiv(paras);
		dia.setClass("modal-dialog");

		div.addChild(dia);

		HtmlDiv con = new HtmlDiv(paras);
		con.setClass("modal-content");

		dia.addChild(con);

		HtmlDiv head = new HtmlDiv(paras);
		head.setClass("modal-header");

		SimpleHtml bt = new SimpleHtml("button", paras);
		bt.addAttr("type", "button");
		bt.addAttr("class", "close");
		bt.addAttr("data-dismiss", "modal");
		bt.setContent("×");

		head.addChild(bt);

		SimpleHtml h4 = new SimpleHtml("h4", paras);
		h4.setContent(dd.getHeader());

		head.addChild(h4);

		con.addChild(head);

		HtmlDiv body = new HtmlDiv(paras);
		body.setClass("modal-body");
		UIDatas datas = new UIDatas();

		if (dd.getBody() != null) {
			datas.addUIData(dd.getBody());
			layout.toHtml(body, datas, paras, true);

			con.addChild(body);
		}

		HtmlDiv footer = new HtmlDiv(paras);
		footer.setClass("modal-footer");
		if (dd.getFooter() != null) {
			datas = new UIDatas();
			datas.addUIData(dd.getFooter());
			layout.toHtml(footer, datas, paras, true);

			con.addChild(footer);
		}

		return div;
	}
}
