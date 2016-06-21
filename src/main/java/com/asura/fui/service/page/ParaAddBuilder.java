package com.asura.fui.service.page;

import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class ParaAddBuilder extends AbstractParaBuilder {
	private String value;

	public FrontData build(FuiUrl url, FrontData data) {
		FrontData d = new FrontData();

		d.AddField(this.key, this.value);

		return d;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}