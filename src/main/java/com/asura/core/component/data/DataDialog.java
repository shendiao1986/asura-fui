import com.asura.fui.component.data;

public class DataDialog extends AbstractUIData {
	private String id;
	private String header;
	private IUIData body;
	private IUIData footer;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public IUIData getBody() {
		return this.body;
	}

	public void setBody(IUIData body) {
		this.body = body;
	}

	public IUIData getFooter() {
		return this.footer;
	}

	public void setFooter(IUIData footer) {
		this.footer = footer;
	}
}