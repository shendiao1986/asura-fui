package com.asura.fui.service.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IServicePostHandler {
	public boolean canHandle(String paramString);

	public String redirect(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse);
}
