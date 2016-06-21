package com.asura.fui.service.data;

import java.util.List;

import com.asura.fui.FrontData;
import com.cpkf.yyjd.tools.data.DataRecord;

public interface IDataProvider {
	public List<DataRecord> build(FrontData paramFrontData);
}
