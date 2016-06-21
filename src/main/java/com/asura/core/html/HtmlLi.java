import com.asura.fui.html;

import com.asura.fui.FrontData;

public class HtmlLi extends AbstractHtmlElement {
	public HtmlLi() {
	}

	public HtmlLi(FrontData paras) {
		super(paras);
	}

	public String getTag() {
		return "li";
	}
}