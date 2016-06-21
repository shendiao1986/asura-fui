import com.asura.fui.component.data;

import com.asura.core.service.data.IDataProvider;

public class DataList extends AbstractUIData {
	private IDataProvider provider;
	private IUIData data;
	private String subKey;

	public IDataProvider getProvider() {
		return this.provider;
	}

	public void setProvider(IDataProvider provider) {
		this.provider = provider;
	}

	public IUIData getData() {
		return this.data;
	}

	public void setData(IUIData data) {
		this.data = data;
	}

	public String getSubKey() {
		return this.subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}
}
