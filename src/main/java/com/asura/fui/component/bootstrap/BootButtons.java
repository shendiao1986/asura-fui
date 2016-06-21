package com.asura.fui.component.bootstrap;

import java.util.HashMap;
import java.util.Iterator;

import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataButtonGroup;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class BootButtons extends AbstractUIComponent {
	private boolean multi;
	private boolean dialog;
	private boolean dialogIn;
	private String type;

	public boolean isDialog() {
		return this.dialog;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}

	public boolean isDialogIn() {
		return this.dialogIn;
	}

	public void setDialogIn(boolean dialogIn) {
		this.dialogIn = dialogIn;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataButtonGroup dr = (DataButtonGroup) data;

		HashMap<String, String> map = ParamterUtil.convert(dr.getKeyLabels());

		HashMap<String, String> uMap = ParamterUtil.convert(dr.getKeyUrls());

		HashMap<String, String> tMap = ParamterUtil.convert(this.type);
		Iterator localIterator;
		if (!(this.multi)) {
			HtmlDiv div = new HtmlDiv(paras);
			div.setClass("btn-group");

			localIterator = map.keySet().iterator();

			while (true) {
				String key = (String) localIterator.next();
				SimpleHtml input = new SimpleHtml("button", paras);
				if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("submit"))) {
					input.addAttr("type", "submit");
				} else {
					input.addAttr("type", "button");
				}
				input.setClass("btn btn-default");
				input.addAttr("id", key);
				input.addAttr("name", dr.getGroup());
				if (key.equals(dr.getSelected()))
					input.setClass("btn btn-default:active");
				else {
					input.setClass("btn btn-default");
				}

				if (((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("dialog"))) || (this.dialog)) {
					input.addAttr("data-toggle", "modal");
					input.addAttr("data-target", "#" + ((String) uMap.get(key)));
					input.setContent((String) map.get(key));
				} else
					if (((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("dialogIn"))) || (this.dialogIn)) {
					input.addAttr("data-dismiss", "modal");
					input.setContent((String) map.get(key));
				} else if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("button"))) {
					input.addAttr("onclick", "window.open('" + ((String) uMap.get(key)) + "')");
					input.setContent((String) map.get(key));
				} else if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("button-js"))) {
					input.addAttr("onclick", (String) uMap.get(key));
					input.setContent((String) map.get(key));
				} else {
					HtmlA a = new HtmlA(paras);
					a.setUrl((String) uMap.get(key));
					a.setContent((String) map.get(key));
					a.addStyle("color", "#000000");
					input.addChild(a);
				}

				addEvent(input, key);
				addStyle(input, paras);

				div.addChild(input);

				if (!(localIterator.hasNext())) {
					return div;
				}
			}
		}
		HtmlDiv toolbar = new HtmlDiv(paras);
		toolbar.setClass("btn-toolbar");

		for (String key : map.keySet()) {
			HtmlDiv div = new HtmlDiv(paras);
			div.setClass("btn-group");

			SimpleHtml input = new SimpleHtml("button", paras);
			if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("submit")))
				input.addAttr("type", "submit");
			else {
				input.addAttr("type", "button");
			}
			input.setClass("btn btn-default");
			input.addAttr("id", key);
			input.addAttr("name", dr.getGroup());

			input.setClass("btn btn-default");

			if (((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("dialog"))) || (this.dialog)) {
				input.addAttr("data-toggle", "modal");
				input.addAttr("data-target", "#" + ((String) uMap.get(key)));
				input.setContent((String) map.get(key));
			} else if (((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("dialogIn"))) || (this.dialogIn)) {
				input.addAttr("data-dismiss", "modal");
				input.setContent((String) map.get(key));
			} else if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("button"))) {
				input.addAttr("onclick", "window.open('" + ((String) uMap.get(key)) + "')");
				input.setContent((String) map.get(key));
			} else if ((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("button-js"))) {
				input.addAttr("onclick", (String) uMap.get(key));
				input.setContent((String) map.get(key));
			} else {
				HtmlA a = new HtmlA(paras);
				a.setUrl((String) uMap.get(key));
				a.setContent((String) map.get(key));
				a.addStyle("color", "#000000");
				input.addChild(a);
			}

			addEvent(input, key);
			addStyle(input, paras);

			div.addChild(input);

			if (((tMap.containsKey(key)) && (((String) tMap.get(key)).equals("dialogIn"))) || (this.dialogIn)) {
				input.addAttr("data-dismiss", "modal");
				toolbar.addChild(input);
			} else {
				toolbar.addChild(div);
			}
		}

		return toolbar;
	}

	public boolean isMulti() {
		return this.multi;
	}

	public void setMulti(boolean multi) {
		this.multi = multi;
	}

}