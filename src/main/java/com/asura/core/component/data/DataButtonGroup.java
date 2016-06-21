import com.asura.fui.component.data;


public class DataButtonGroup extends AbstractUIData {
	private String keyLabels;
	private String keyUrls;
	private String keyDescs;
	private String group;
	private String selected;

	public String getKeyDescs() {
		return this.keyDescs;
	}

	public void setKeyDescs(String keyDescs) {
		this.keyDescs = keyDescs;
	}

	public String getKeyLabels() {
		return this.keyLabels;
	}

	public void setKeyLabels(String keyLabels) {
		this.keyLabels = keyLabels;
	}

	public String getSelected() {
		return this.selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getGroup() {
		return this.group;
	}

	public String getKeyUrls() {
		if (this.keyUrls == null) {
			this.keyUrls = "";
		}
		return this.keyUrls;
	}

	public void setKeyUrls(String keyUrls) {
		this.keyUrls = keyUrls;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
