import com.asura.fui.service.page;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class PageData {
	private String page;
	private List<IParaBuilder> builders;

	public PageData() {
		this.builders = new ArrayList();
	}

	public void addPageData(IParaBuilder builder) {
		this.builders.add(builder);
	}

	public void addConstant(String field, Object value) {
		ParaConstBuilder c = new ParaConstBuilder();
		c.setKey(field);
		if ((value instanceof String) && (StringUtil.isNullOrEmpty((String) value))) {
			value = "";
		}

		c.setValue(value);
		addPageData(c);
	}

	public void addParaField(String key, String field) {
		ParaSimpleBuilder builder = new ParaSimpleBuilder();
		builder.setKey(key);
		builder.setField(field);
		addPageData(builder);
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public List<IParaBuilder> getBuilders() {
		return this.builders;
	}

	public void setBuilders(List<IParaBuilder> builders) {
		this.builders = builders;
	}

	public FrontData getPageData(FuiUrl url, FrontData data) {
		FrontData d = new FrontData();
		for (IParaBuilder builder : this.builders) {
			d.merge(builder.build(url, data));
		}

		return d;
	}
}
