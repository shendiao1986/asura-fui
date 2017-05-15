package com.asura.fui.util;

import com.asura.tools.data.mysql.ConnectionInformation;
import com.asura.tools.data.mysql.MysqlHandler;

public class FuiMysqlHandler {

	private static String configFile;

	public static void setConfigFile(String confFile) {
		FuiMysqlHandler.configFile = confFile;
	}

	public static String getConfigFile() {
		return FuiMysqlHandler.configFile;
	}

	public static MysqlHandler getFuiMysqlHandler() {
		return new MysqlHandler(ConnectionInformation.fromClassLoaderFile(configFile));
	}

}
