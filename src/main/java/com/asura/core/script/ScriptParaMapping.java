import com.asura.fui.script;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.FrontData;
import com.cpkf.yyjd.tools.util.StringUtil;

public class ScriptParaMapping implements IUIScript {
	private List<ParaMapping> mappings;

	public ScriptParaMapping() {
		this.mappings = new ArrayList();
	}

	public void addDataMapping(ParaMapping mapping) {
		this.mappings.add(mapping);
	}

	public String toScript(FrontData paras) {
		String s = "";
		List initials = new ArrayList();
		String ini;
		for (ParaMapping pm : this.mappings) {
			for (String toKey : pm.getToMap().keySet()) {
				ini = "var " + toKey + "='';";
				if (!(initials.contains(ini))) {
					initials.add(ini);
				}
			}
		}

		s = StringUtil.getStringFromStrings(initials, "\n") + "\n";

		for (ParaMapping pm : this.mappings) {
			s = s + "if(";
			List con = new ArrayList();
			for (String fromKey : pm.getFromMap().keySet()) {
				con.add(fromKey + " == '" + ((String) pm.getFromMap().get(fromKey)) + "'");
			}
			s = s + StringUtil.getStringFromStrings(con, " && ") + "){\n";

			Object set = new ArrayList();
			for (String toKey : pm.getToMap().keySet()) {
				((List) set).add(toKey + "= '" + ((String) pm.getToMap().get(toKey)) + "';");
			}
			s = s + StringUtil.getStringFromStrings((List) set, "\n");
			s = s + "\n}\n";
		}

		return ((String) s);
	}
}
