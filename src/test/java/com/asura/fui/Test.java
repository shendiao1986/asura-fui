package com.asura.fui;

import com.asura.fui.config.FuiConverter;
import com.asura.tools.util.FileUtil;

public class Test {

	@org.junit.Test
	public void test() {
		String content = FileUtil.getFileContent(
				"E:\\NieFei\\CodeBase\\SearchRelated\\appsearch\\src\\pages\\keyword-effect.xml", "utf8");
		FuiConverter.getPage(content);
	}

}
