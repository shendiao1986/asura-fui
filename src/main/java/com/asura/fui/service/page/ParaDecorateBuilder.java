package com.asura.fui.service.page;

import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class ParaDecorateBuilder extends AbstractParaBuilder {
	private IParaBuilder buidler;
	private FrontData data;

	public IParaBuilder getBuidler() {
		return this.buidler;
	}

	public void setBuidler(IParaBuilder buidler) {
		this.buidler = buidler;
	}

	public FrontData getData(FuiUrl url, FrontData data) {
		if (this.data == null) {
			this.data = this.buidler.build(url, data);
		}
		return this.data;
	}

	public FrontData build(FuiUrl url, FrontData data) {
		if (this.data == null) {
			this.data = this.buidler.build(url, data);
		}
		return this.data;
	}
}