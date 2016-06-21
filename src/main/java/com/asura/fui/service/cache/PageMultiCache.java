package com.asura.fui.service.cache;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.service.dispatch.FuiUrl;

public class PageMultiCache implements IPageCache {
	private List<IPageCache> caches;

	public PageMultiCache() {
		this.caches = new ArrayList();
	}

	public void addCache(IPageCache cache) {
		this.caches.add(cache);
	}

	public void cache(FuiUrl url, String content) {
		for (IPageCache cache : this.caches)
			cache.cache(url, content);
	}

	public String get(FuiUrl url) {
		for (IPageCache cache : this.caches) {
			if (cache.isCache(url)) {
				return cache.get(url);
			}
		}

		return "";
	}

	public boolean isCache(FuiUrl url) {
		long s = System.currentTimeMillis();
		for (IPageCache cache : this.caches) {
			if (cache.isCache(url)) {
				System.out.println("multi is cache:" + (System.currentTimeMillis() - s));
				return true;
			}
		}

		return false;
	}
}
