import com.asura.fui.component.data;

import java.util.ArrayList;
import java.util.List;

public class UIDatas {
	private List<IUIData> datas;

	public UIDatas() {
		this.datas = new ArrayList();
	}

	public void addUIData(IUIData data) {
		this.datas.add(data);
	}

	public List<IUIData> getDatas() {
		return this.datas;
	}

	public void setDatas(List<IUIData> datas) {
		this.datas = datas;
	}

	public void replace(IUIData data) {
		IUIData r = null;
		for (IUIData d : this.datas) {
			if (d.getKey().equals(data.getKey())) {
				r = d;
			}
		}

		if (r != null) {
			this.datas.add(data);
			this.datas.remove(r);
		}
	}

	public IUIData getUIData(String key) {
		for (IUIData data : this.datas) {
			if ((data.getKey() != null) && (data.getKey().equals(key))) {
				return data;
			}
		}

		return null;
	}
}
