package com.asura.fui.component.data;

import java.util.ArrayList;
import java.util.List;

public class DataPanel extends AbstractUIData {
	private String head;
	private String tail;
	private List<IUIData> children;

	public DataPanel() {
		this.children = new ArrayList();
	}

	public void addChild(IUIData child) {
		this.children.add(child);
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getTail() {
		return this.tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public List<IUIData> getChildren() {
		return this.children;
	}

	public void setChildren(List<IUIData> children) {
		this.children = children;
	}
}
