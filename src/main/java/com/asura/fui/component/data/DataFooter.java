package com.asura.fui.component.data;

public class DataFooter extends AbstractUIData {
	private String keyLabels;
	private String keyUrls;
	private String keyLabelTypes;
	private String keyUrlTypes;
    

	public String getKeyLabelTypes() {
		return keyLabelTypes;
	}

	public void setKeyLabelTypes(String keyLabelTypes) {
		this.keyLabelTypes = keyLabelTypes;
	}

	public String getKeyUrlTypes() {
		return keyUrlTypes;
	}

	public void setKeyUrlTypes(String keyUrlTypes) {
		this.keyUrlTypes = keyUrlTypes;
	}

	public String getKeyLabels() {
		return this.keyLabels;
	}

	public void setKeyLabels(String keyLabels) {
		this.keyLabels = keyLabels;
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

}
