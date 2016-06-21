package com.asura.fui.service.dispatch;

import javax.servlet.http.HttpServletRequest;

import com.asura.fui.FrontData;

public interface IUrlDispatcher {

	public String getPage(FuiUrl paramFuiUrl, HttpServletRequest paramHttpServletRequest);

	public FrontData getFrontData(FuiUrl paramFuiUrl, HttpServletRequest paramHttpServletRequest);
}
