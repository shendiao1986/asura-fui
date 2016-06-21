package com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public interface ISiteBean {
	public DataRecord toDataRecord();

	public ISiteBean fromDataRecord(DataRecord paramDataRecord);

	public String getDbName(String paramString1, String paramString2);

	public String getTableName();

	public String[] getKeys();

	public String[] getIndexes();
}
