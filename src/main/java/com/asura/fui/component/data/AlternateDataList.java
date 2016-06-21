package com.asura.fui.component.data;

import com.asura.fui.service.data.IDataProvider;

public class AlternateDataList extends AbstractUIData {
	private IDataProvider provider;
	private IUIData data;
	private IUIData alterData;
	private String subKey;

	public IDataProvider getProvider() {
		return this.provider;
	}

	public void setProvider(IDataProvider provider) {
		this.provider = provider;
	}

	public IUIData getData() {
		return this.data;
	}

	public void setData(IUIData data) {
		this.data = data;
	}

	public String getSubKey() {
		return this.subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	public IUIData getAlterData() {
		return this.alterData;
	}

	public void setAlterData(IUIData alterData) {
		this.alterData = alterData;
	}
}