import com.asura.fui.script.jquery;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.script.IUIScript;
import com.asura.fui.FrontData;

public class JQueryDatePicker implements IUIScript {
	private String id;
	private String format;

	public String toScript(FrontData paras) {
		if (StringUtil.isNullOrEmpty(this.format)) {
			this.format = "yy-mm-dd";
		}
		return " $(document).ready(function() {\n$(\"#" + this.id + "\").datepicker({ dateFormat: '" + this.format
				+ "' }); \n });";
	}
}
