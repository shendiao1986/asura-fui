package com.asura.fui.service.cache;

import com.cpkf.yyjd.tools.util.cache.SimpleCache;
import com.asura.fui.service.dispatch.FuiUrl;

public class PageMemCache implements IPageCache {
	
	private static SimpleCache<String, String> cache = new SimpleCache(1000000);
	private int second;
	

	public PageMemCache() {
		this.second = 3600;
	}

	public PageMemCache(int second) {
		this.second = second;
	}

	public void cache(FuiUrl url, String content) {
		cache.cache(url.toUrl(), content, this.second);
	}

	public String get(FuiUrl url) {
		return ((String) cache.get(url.toUrl()));
	}

	public boolean isCache(FuiUrl url) {
		long s = System.currentTimeMillis();

		boolean c = cache.iscached(url.toUrl());
		System.out.println("mem is cache:" + (System.currentTimeMillis() - s));
		return c;
	}

}
