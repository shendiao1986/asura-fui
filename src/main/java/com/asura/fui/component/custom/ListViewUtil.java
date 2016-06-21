package com.asura.fui.component.custom;

import com.asura.fui.component.bootstrap.BootButtons;
import com.asura.fui.component.bootstrap.BootProgress;
import com.asura.fui.component.data.DataButtonGroup;
import com.asura.fui.component.data.DataProgress;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.basic.DataText;
import com.asura.fui.component.data.basic.DataUrl;

public class ListViewUtil {
	public static IUIData getText(String text) {
		DataText dt = new DataText();
		dt.setText(text);

		SimpleText st = new SimpleText();

		dt.setComponent(st);

		return dt;
	}

	public static IUIData getText(String text, String desc) {
		DataText dt = new DataText();
		dt.setText(text);

		SimpleText st = new SimpleText();
		st.setDesc(desc);
		dt.setComponent(st);

		return dt;
	}

	public static IUIData getUrl(String text, String url) {
		DataUrl dt = new DataUrl();
		dt.setText(text);
		dt.setUrl(url);

		SimpleUrl st = new SimpleUrl();

		dt.setComponent(st);

		return dt;
	}

	public static IUIData getProgress(String percent, String count, String styles) {
		DataProgress dt = new DataProgress();
		dt.setCount(count);
		dt.setPercent(percent);

		BootProgress st = new BootProgress();
		st.setStyles(styles);
		dt.setComponent(st);

		return dt;
	}

	public static IUIData getProgress(String percent, String count) {
		return getProgress(percent, count, "");
	}

	public static IUIData getButtons(String group, String keyLabels, String keyUrls, String selected, String dialog,
			String styles) {
		DataButtonGroup dbg = new DataButtonGroup();
		dbg.setGroup(group);
		dbg.setKeyLabels(keyLabels);
		dbg.setKeyUrls(keyUrls);
		dbg.setSelected(selected);

		BootButtons bb = new BootButtons();
		bb.setMulti(true);
		bb.setType(dialog);
		bb.setStyles(styles);

		dbg.setComponent(bb);

		return dbg;
	}

	public static IUIData getButtons(String group, String keyLabels, String keyUrls, String selected, String dialog) {
		return getButtons(group, keyLabels, keyUrls, selected, dialog, "");
	}
}
