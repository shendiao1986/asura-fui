import com.asura.fui.service.data;

import com.asura.fui.FrontData;

public class SimpleDataMapping implements IDataMapping {
	public ProviderKey getProviderKey(FrontData data) {
		ProviderKey pk = new ProviderKey(data.getValueString("site"), data.getValueString("site"),
				data.getValueString("site"));
		if (pk.getDataProvider() == null) {
			return pk;
		}
		return pk;
	}
}