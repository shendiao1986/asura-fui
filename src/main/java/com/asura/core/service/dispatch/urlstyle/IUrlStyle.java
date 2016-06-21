import com.asura.fui.service.dispatch.urlstyle;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.core.service.dispatch.FuiUrl;

public interface IUrlStyle {
	public String getUrlType(FuiUrl paramFuiUrl);

	public String getUrlKey(FuiUrl paramFuiUrl);

	public String getUrl(FuiUrl paramFuiUrl, String paramString, DataRecord paramDataRecord, int paramInt);

	public String getArticleField();
}
