import com.asura.fui.service.dispatch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.asura.fui.FrontData;


public class SimpleDispatcher implements IUrlDispatcher {
	private List<UrlMapping> mappings;

	public List<UrlMapping> getMappings() {
		return this.mappings;
	}

	public void setMappings(List<UrlMapping> mappings) {
		this.mappings = mappings;
	}

	public String getPage(FuiUrl url, HttpServletRequest request) {
		for (UrlMapping um : this.mappings) {
			if (um.meet(url.getSuffix())) {
				return um.getPage();
			}
		}

		return "page";
	}

	public FrontData getFrontData(FuiUrl url, HttpServletRequest request) {
		return null;
	}
	
}
