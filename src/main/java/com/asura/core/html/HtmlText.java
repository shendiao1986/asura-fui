import com.asura.fui.html;

import com.asura.fui.FrontData;

public class HtmlText extends AbstractHtmlElement {
	
	private String text;

	public HtmlText(String text) {
		this.text = text;
	}

	public HtmlText(String text, FrontData paras) {
		super(paras);
		this.text = text;
	}

	@Override
	public String getTag() {
		return "div";
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	protected String toHtml(String prefix) {
		return prefix + this.text;
	}

}
