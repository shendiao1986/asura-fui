package com.asura.fui.service.page;

import javax.servlet.http.HttpServletRequest;

import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public interface IPageDataBuilder {
	public boolean canHandle(FuiUrl paramFuiUrl, FrontData paramFrontData);

	public String getPage(FuiUrl paramFuiUrl, FrontData paramFrontData,
			HttpServletRequest paramHttpServletRequest);

	public PageData build(FuiUrl paramFuiUrl, FrontData paramFrontData,
			HttpServletRequest paramHttpServletRequest);
}
