package com.asura.fui.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.tools.debug.SpendTimer;
import com.asura.tools.util.ExceptionUtil;
import com.asura.fui.service.cache.IPageCache;
import com.asura.fui.service.cache.PageCacheService;
import com.asura.fui.service.dispatch.DispatcherCache;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.service.dispatch.IUrlDispatcher;
import com.asura.fui.service.dispatch.PageCache;
import com.asura.fui.util.ServiceUtil;
import com.asura.fui.DataConverter;
import com.asura.fui.FrontData;
import com.asura.fui.FuiPage;

public class PageService extends HttpServlet {
	
	private static final long serialVersionUID = -5164872685520450152L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		SpendTimer.enableKey("page");
		SpendTimer t = SpendTimer.getInstance("page");
		t.startTime();
		
		String path = (String) request.getSession().getAttribute("url-suffix");
		if (path == null) {
			path = "";
		}
		
		path = new String(path.getBytes("ISO-8859-1"), "UTF8");
		FuiUrl url = new FuiUrl(request.getServerName(), request.getContextPath(), request.getServerPort(), path);

		System.out.println("reqeust url:" + request.getRequestURI());

		System.out.println("page url:" + url.toUrl());
		
		IPageCache cache = PageCacheService.getPageCache(url);
		
		if(!cache.isCache(url)){
			response.setCharacterEncoding("utf8");
			System.out.println(url.getServer());
			
			IUrlDispatcher dispatcher = DispatcherCache.getDispather(url.getServer(), request.getServerPort());

			if (dispatcher == null) {
				dispatcher = DispatcherCache.getDispather(url.getServer());
			}
			
			System.out.println("dispatch:" + dispatcher.getClass().getSimpleName());

			long s = System.currentTimeMillis();

			System.out.println("is cached:" + (System.currentTimeMillis() - s));
			t.printSpendTime("prepare");

			System.out.println("url:" + url.toUrl());
			System.out.println("urlbase:" + url.toUrlBase());

			FrontData paras = DataConverter.fromParameter(request);

			paras.AddField("fui-url", url);

			String content = "";
			try {
				paras.merge(dispatcher.getFrontData(url, request));

				t.printSpendTime("get paras");

				String pageName = dispatcher.getPage(url, request);
				System.out.println("pageName:" + pageName);
				paras.AddField("pageName", pageName);

				FuiPage page = PageCache.getPage(url.getServer(), pageName);

				t.printSpendTime("get page");

				if (page != null)
					content = page.toHtml(paras);
			} catch (Exception e) {
				content = ExceptionUtil.getExceptionContent(e);
			}

			cache.cache(url, content);
			ServiceUtil.output(content, response);
			
			
		}else{
			ServiceUtil.output(cache.get(url), response);
		}
		
		t.printSpendTime(url.toUrl() + " output page");

	}

}
