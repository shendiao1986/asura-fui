package com.asura.fui.component.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.asura.tools.util.StringUtil;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FuiCss;

public class LayoutValueSet {
	private List<LayoutValue> values;
	private HashMap<String, HashMap<Integer, List<String>>> children;
	private HashMap<String, LayoutValue> map;
	private HashMap<String, FuiCss> styleMap;
	private int width;

	public LayoutValueSet() {
		this.values = new ArrayList();
	}

	public void addLayoutVaue(LayoutValue lv) {
		int index = -1;
		for (int i = 0; i < this.values.size(); ++i) {
			if (((LayoutValue) this.values.get(i)).getKey().equals(lv.getKey())) {
				index = i;
				break;
			}
		}

		if (index > -1) {
			this.values.remove(index);
			this.values.add(index, lv);
		} else {
			this.values.add(lv);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public List<LayoutValue> getValues() {
		return this.values;
	}

	public void setValues(List<LayoutValue> values) {
		this.values = values;
	}

	public String getClass(String key) {
		LayoutValue lv = getLayoutValue(key);
		if (lv != null) {
			HashMap map = ParamterUtil.convert(lv.getAttrs());
			if (map.containsKey("class")) {
				return ((String) map.get("class"));
			}
		}

		return "";
	}

	public String getAttrs(String key) {
		LayoutValue lv = getLayoutValue(key);
		if (lv != null) {
			return lv.getAttrs();
		}

		return "";
	}

	public FuiCss getStyle(String key) {
		initial();

		compute();

		if (this.styleMap.containsKey(key)) {
			return ((FuiCss) this.styleMap.get(key));
		}

		return new FuiCss();
	}

	public String getTag(String key) {
		initial();

		compute();

		if ("page".equals(key)) {
			return null;
		}
		if (this.map.containsKey(key)) {
			return ((LayoutValue) this.map.get(key)).getTag();
		}
		return null;
	}

	public FuiCss getStyle(LayoutValue value) {
		initial();

		compute();

		if (this.styleMap.containsKey(value.getKey())) {
			return ((FuiCss) this.styleMap.get(value.getKey()));
		}

		return new FuiCss();
	}

	public Integer[] getRows(String key) {
		if (this.children.containsKey(key)) {
			return ((Integer[]) ((HashMap) this.children.get(key)).keySet().toArray(new Integer[0]));
		}
		return new Integer[0];
	}

	public String[] getKeys(String parent, int row) {
		return ((String[]) ((List) ((HashMap) this.children.get(parent)).get(Integer.valueOf(row)))
				.toArray(new String[0]));
	}

	public LayoutValue getLayoutValue(String key) {
		return ((LayoutValue) this.map.get(key));
	}

	private synchronized void compute() {
		if (this.styleMap == null) {
			this.styleMap = new HashMap();

			HashMap<String, Integer> widthMap = buildWidth();
			for (String key : widthMap.keySet()) {
				LayoutValue lv = getLayoutValue(key);
				if (lv != null) {
					if (!(lv.isNoWidth()))
						addStyle(key, "width", widthMap.get(key) + "px");
					else
						addStyle(key, "width", "auto");
				} else {
					addStyle(key, "width", widthMap.get(key) + "px");
				}
			}

			for (LayoutValue lv : this.values) {
				if (lv.getMl() != 0) {
					addStyle(lv.getKey(), "margin-left", lv.getMl() + "px");
				}
				if (lv.getMr() != 0) {
					addStyle(lv.getKey(), "margin-right", lv.getMr() + "px");
				}
				if (lv.getMt() != 0) {
					addStyle(lv.getKey(), "margin-top", lv.getMt() + "px");
				}
				if (lv.getMb() != 0) {
					addStyle(lv.getKey(), "margin-bottom", lv.getMb() + "px");
				}
				if (lv.getPos() == 1) {
					addStyle(lv.getKey(), "float", "right");
				}

				if (!(StringUtil.isNullOrEmpty(lv.getStyles()))) {
					HashMap<String, String> styleMap = ParamterUtil.convert(lv.getStyles());
					for (String s : styleMap.keySet())
						addStyle(lv.getKey(), s, (String) styleMap.get(s));
				}
			}
		}
	}

	private void addStyle(String key, String name, String value) {
		if (!(this.styleMap.containsKey(key))) {
			this.styleMap.put(key, new FuiCss());
		}
		((FuiCss) this.styleMap.get(key)).add(name, value);
	}

	private synchronized void initial() {
		if (this.children == null) {
			this.children = new HashMap();
			this.map = new HashMap();

			for (LayoutValue value : this.values) {
				if (!(this.children.containsKey(value.getParent()))) {
					this.children.put(value.getParent(), new LinkedHashMap());
				}

				if (!(((HashMap) this.children.get(value.getParent())).containsKey(Integer.valueOf(value.getRow())))) {
					((HashMap) this.children.get(value.getParent())).put(Integer.valueOf(value.getRow()),
							new ArrayList());
				}
				((List) ((HashMap) this.children.get(value.getParent())).get(Integer.valueOf(value.getRow())))
						.add(value.getKey());

				this.map.put(value.getKey(), value);
			}
		}
	}

	public HashMap<String, Integer> buildWidth() {
		System.out.println("buildWidth");
		initial();

		HashMap widthMap = new HashMap();
		widthMap.put("page", Integer.valueOf(this.width));
		for (LayoutValue lv : this.values) {
			if (lv.getWidth() > 0) {
				widthMap.put(lv.getKey(), Integer.valueOf(lv.getWidth()));
			}

		}

		while (handleWidth(widthMap) != 0)
			;
		System.out.println("buildWidth ok!");
		return widthMap;
	}

	private int handleWidth(HashMap<String, Integer> widthMap) {
		int i = 0;
		for (LayoutValue value : this.values) {
			if (!(widthMap.containsKey(value.getKey()))) {
				String p = value.getParent();

				if (widthMap.containsKey(p)) {
					int parentW = ((Integer) widthMap.get(p)).intValue();
					List<String> sblings = getSblings(value);
					int w = 0;
					int unknow = 1;
					for (String key : sblings) {
						w = w + ((LayoutValue) this.map.get(key)).getMl() + ((LayoutValue) this.map.get(key)).getMr();
						if (!(key.equals(value.getKey()))) {
							if (widthMap.containsKey(key))
								w += ((Integer) widthMap.get(key)).intValue();
							else {
								++unknow;
							}
						}
					}
					widthMap.put(value.getKey(), Integer.valueOf((parentW - w) / unknow));
				} else {
					++i;
				}
			}
		}

		return i;
	}

	private List<String> getSblings(LayoutValue value) {
		return ((List) ((HashMap) this.children.get(value.getParent())).get(Integer.valueOf(value.getRow())));
	}
}
