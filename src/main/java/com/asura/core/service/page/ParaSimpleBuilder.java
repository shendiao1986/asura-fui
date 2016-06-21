import com.asura.fui.service.page;

import com.asura.core.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class ParaSimpleBuilder extends AbstractParaBuilder {
	private String field;

	public FrontData build(FuiUrl url, FrontData data) {
		FrontData d = new FrontData();

		d.AddField(this.key, data.getValue(this.field));

		return d;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
