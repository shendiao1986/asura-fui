package com.asura.fui.service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.fui.util.ServiceUtil;
import com.asura.fui.DataConverter;
import com.asura.fui.FrontData;

public class DispatchService extends HttpServlet {

	private static final long serialVersionUID = -5164872685520450152L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");

		FrontData data = DataConverter.fromParameter(request);
		System.out.println(data);

		String path = request.getPathInfo();

		System.out.println("path=" + path);
		
		if (path != null) {
			if ((path.startsWith("/js/")) || (path.startsWith("/css/")) || (path.startsWith("/image/"))
					|| (path.startsWith("/images/")) || (path.endsWith(".test")) || (path.endsWith(".ico"))
					|| (path.endsWith(".jpg")) || (path.endsWith(".png")) || (path.endsWith(".gif"))
					|| (path.endsWith(".pdf")) || (path.endsWith(".doc")) || (path.endsWith(".docx"))
					|| (path.endsWith("help.htm"))) {
				String p = request.getSession().getServletContext().getRealPath(path);
				try {
					ServiceUtil.output(new FileInputStream(p), response);
				} catch (Exception e) {
					response.setStatus(404);
					ServiceUtil.output("File Not Found", response);
				}
				return;
			}
			if (path.equals("/sitemap.xml")) {
				String serverName = request.getServerName();
				System.out.println("serverName=" + serverName);
				String mapStr="undo";
				//String mapStr = SiteMapMaker.make(serverName);
				//TODO will parse
				try {
					ServiceUtil.output(mapStr, response);
				} catch (Exception e) {
					response.setStatus(404);
					ServiceUtil.output("File Not Found", response);
				}
				return;
			}
		}

		request.getSession().setAttribute("url-suffix", path);

		request.getSession().getServletContext().getRequestDispatcher("/page").forward(request, response);
	}

	
	
	

}
