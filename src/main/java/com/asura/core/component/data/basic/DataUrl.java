import com.asura.fui.component.data.basic;

import com.asura.core.component.data.AbstractUIData;

public class DataUrl extends AbstractUIData {
	private String url;
	private String text;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
