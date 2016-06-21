package com.asura.fui.service.page;

import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class ParaConstBuilder extends AbstractParaBuilder {
	private Object value;

	public FrontData build(FuiUrl url, FrontData data) {
		FrontData d = new FrontData();

		d.AddField(this.key, this.value);

		return d;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
