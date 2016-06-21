package com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.util.StringUtil;

public abstract class AbstractSitebean implements ISiteBean {
	public ISiteBean fromDataRecord(DataRecord dr) {
		ISiteBean bean = null;
		try {
			bean = (ISiteBean) super.getClass().newInstance();
			for (String key : dr.getAllFields()) {
				String v = dr.getFieldValue(key);
				if (StringUtil.isNullOrEmpty(v))
					continue;
				bean.getClass().getMethod("set" + StringUtil.getPascalString(key), new Class[] { String.class })
						.invoke(bean, new Object[] { v });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	public String getDbName(String domain, String sub) {
		return BaseService.getDbNameFromDomain(domain);
	}
}
