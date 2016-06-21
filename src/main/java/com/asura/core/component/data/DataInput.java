import com.asura.fui.component.data;

public class DataInput extends AbstractUIData {
	private String label;
	private String value;
	private boolean submit;

	public String getLabel() {
		return this.label;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isSubmit() {
		return this.submit;
	}

	public void setSubmit(boolean submit) {
		this.submit = submit;
	}
}
