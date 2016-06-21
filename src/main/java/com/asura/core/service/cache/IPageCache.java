import com.asura.fui.service.cache;

import com.asura.core.service.dispatch.FuiUrl;

public interface IPageCache {

	public boolean isCache(FuiUrl paramFuiUrl);

	public void cache(FuiUrl paramFuiUrl, String paramString);

	public String get(FuiUrl paramFuiUrl);
}
