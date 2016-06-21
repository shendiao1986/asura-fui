package com.asura.fui.script;

import java.util.ArrayList;
import java.util.List;

public class UIScripts {
	private List<IUIScript> scripts;

	public UIScripts() {
		this.scripts = new ArrayList();
	}

	public void addScript(IUIScript script) {
		this.scripts.add(script);
	}

	public List<IUIScript> getScripts() {
		return this.scripts;
	}

	public void setScripts(List<IUIScript> scripts) {
		this.scripts = scripts;
	}
}
