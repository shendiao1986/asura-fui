import com.asura.fui.script;

import com.asura.fui.FrontData;

public class ScriptChangeHtml implements IUIScript {
	private String id;
	private String content;
	private boolean var;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toScript(FrontData paras) {
		if (this.var) {
			return "document.getElementById(\"" + this.id + "\").innerHTML = " + this.content + ";";
		}
		return "document.getElementById(\"" + this.id + "\").innerHTML = \"" + this.content + "\";";
	}
}
