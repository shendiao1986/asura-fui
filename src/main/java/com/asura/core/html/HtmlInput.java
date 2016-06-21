import com.asura.fui.html;

import com.asura.fui.FrontData;

public class HtmlInput extends AbstractHtmlElement {
	public HtmlInput() {
	}

	public HtmlInput(FrontData paras) {
		super(paras);
	}

	public String getTag() {
		return "input";
	}

	public void setType(String type) {
		addAttr("type", type);
	}
}
