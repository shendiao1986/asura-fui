package com.asura.fui.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.asura.fui.FrontData;
import com.asura.fui.FuiCss;
import com.cpkf.yyjd.tools.util.StringUtil;

public abstract class AbstractHtmlElement implements IHtmlElement {
	
	protected HashMap<String, String> attr;
	protected List<IHtmlElement> children;
	protected FrontData paras;
	
	public AbstractHtmlElement() {
		this.attr = new LinkedHashMap();
		this.children = new ArrayList();
	}

	public AbstractHtmlElement(FrontData paras) {
		this.attr = new LinkedHashMap();
		this.children = new ArrayList();
		this.paras = paras;
	}
	
	public void setClass(String value) {
		this.attr.put("class", value);
	}

	@Override
	public String toHtml() {
		return toHtml("");
	}

	@Override
	public void addStyle(String name, String value) {
		FuiCss fc = new FuiCss();
		for (String key : this.attr.keySet()) {
			if ("style".equals(key)) {
				if ("".equals(this.attr.get(key))) {
					this.attr.remove(key);
					return;
				}
				fc.add((String) this.attr.get(key));
			}
		}
		fc.add(name, value);
		this.attr.put("style", fc.toStyle(new FrontData()));

	}

	@Override
	public void addChild(IHtmlElement el) {
		this.children.add(el);
	}

	@Override
	public void setContent(String content) {
		if (this.paras == null) {
			HtmlText text = new HtmlText(content);
			this.children.add(text);
		} else {
			HtmlText text = new HtmlText(getValue(content, this.paras));
			this.children.add(text);
		}
	}

	@Override
	public void addAttr(String name, String value) {
		if (value != null) {
			if (this.paras == null){
				this.attr.put(name, value);
			}else{
				this.attr.put(name, getValue(value, this.paras));
			}
		} else{
			this.attr.remove(name);
		}
	}

	@Override
	public String getAttr(String name) {
		return ((String) this.attr.get(name));
	}
	
	public void removeChild(String tag, String[] keys, String[] values) {
		List<IHtmlElement> list = new ArrayList<IHtmlElement>();

		for (IHtmlElement el : this.children) {
			AbstractHtmlElement ah = (AbstractHtmlElement) el;
			if (ah.getTag().endsWith(tag)) {
				boolean not = false;
				for (int i = 0; i < keys.length; ++i) {
					if (!(ah.attr.containsKey(keys[i]))) {
						not = true;
						break;
					}
					if (!(((String) ah.attr.get(keys[i])).equals(values[i]))) {
						not = true;
						break;
					}
				}
				if (!(not)) {
					list.add(ah);
				}
			}
		}

		for (IHtmlElement el : list)
			this.children.remove(el);
	}
	
	protected String toHtml(String prefix) {
		List list = new ArrayList();

		List as = new ArrayList();
		for (String key : this.attr.keySet()) {
			as.add(key + "=\"" + ((String) this.attr.get(key)) + "\"");
		}

		if ((contentIsNull()) && (this.children.size() == 0)) {
			if (getTag().equals("link"))
				list.add(prefix + "<" + getTag() + " " + StringUtil.getStringFromStrings(as, " ") + " >");
			else if (getTag().equals("br"))
				list.add(prefix + "<" + getTag() + " " + StringUtil.getStringFromStrings(as, " ") + " />");
			else
				list.add(prefix + "<" + getTag() + " " + StringUtil.getStringFromStrings(as, " ") + ">" + "</"
						+ getTag() + ">");
		} else {
			list.add(prefix + "<" + getTag() + " " + StringUtil.getStringFromStrings(as, " ") + ">");
			for (IHtmlElement child : this.children) {
				list.add(((AbstractHtmlElement) child).toHtml(prefix + "  "));
			}
			list.add(prefix + "</" + getTag() + ">");
		}

		return StringUtil.getStringFromStrings(list, "\n");
	}
	
	private boolean contentIsNull() {
		for (IHtmlElement el : this.children) {
			if ((el instanceof HtmlText) && (!(StringUtil.isNullOrEmpty(((HtmlText) el).getText())))) {
				return false;
			}
		}
		return true;
	}

	private String getValue(String value, FrontData paras) {
		if (StringUtil.isNullOrEmpty(value)) {
			return "";
		}
		String t = value;
		for (String p : paras.getAllFields()) {
			t = t.replace("$" + p + "$", paras.getValueString(p));
		}
		return t;
	}
	
}
