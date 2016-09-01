package com.asura.fui.service.dispatch;

import com.asura.tools.util.StringUtil;

public class FuiUrl {

	private String server;
	private String cPath;
	private int port;
	private String suffix;

	public FuiUrl() {
	}

	public FuiUrl(String server, String cpath, int port, String suffix) {
		this.server = server;
		this.port = port;
		this.suffix = suffix;
		this.cPath = cpath;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getCPath() {
		return this.cPath;
	}

	public void setCPath(String path) {
		this.cPath = path;
	}

	public String getSuffix() {
		if (this.suffix.startsWith("/")) {
			this.suffix = this.suffix.substring(1);
		}
		return this.suffix.trim();
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	private String getMiddlePath() {
		String c = "";
		if (((!(StringUtil.isNullOrEmpty(this.cPath)))
				&& (((this.server.equals("localhost")) || (StringUtil.isNumberString(this.server)))))
				|| (this.server.equals("test2.surepush.cn")) || (this.server.equals("sp.lenovomm.com"))) {
			c = this.cPath;
		}

		return c;
	}

	public String toUrlBase() {
		String c = getMiddlePath();
		if ((this.port == 0) || (this.port == 80)) {
			return "http://" + this.server + c;
		}
		return "http://" + this.server + ":" + this.port + c;
	}

	public String toUrlBaseWithoutHttp() {
		String c = getMiddlePath();
		if ((this.port == 0) || (this.port == 80)) {
			return this.server + c.trim();
		}
		return this.server + ":" + this.port + c.trim();
	}

	public String toUrl() {
		String c = getMiddlePath();
		if ((this.port == 0) || (this.port == 80)) {
			return "http://" + this.server + c + "/" + getSuffix();
		}
		return "http://" + this.server + ":" + this.port + c + "/" + getSuffix();
	}

	public static String getDomain(String server) {
		if (server.contains(".")) {
			return server.substring(server.indexOf(".") + 1);
		}
		return server;
	}

	public static String getSub(String server) {
		if (server.contains(".")) {
			return server.substring(0, server.indexOf("."));
		}
		return "www";
	}

}
