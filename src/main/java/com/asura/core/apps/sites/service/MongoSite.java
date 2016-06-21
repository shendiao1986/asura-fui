import com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public class MongoSite extends AbstractSitebean {
	private String sub;
	private String title;
	private String title1;
	private String desc;
	private String keyword;
	private String ip;
	private String urlStyle;

	public MongoSite() {
	}

	public MongoSite(String sub, String title, String title1, String desc, String keyword, String ip, String urlStyle) {
		this.sub = sub;
		this.title = title;
		this.title1 = title1;
		this.desc = desc;
		this.keyword = keyword;
		this.ip = ip;
		this.urlStyle = urlStyle;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle1() {
		return this.title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrlStyle() {
		return this.urlStyle;
	}

	public void setUrlStyle(String urlStyle) {
		this.urlStyle = urlStyle;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", this.sub);
		dr.AddField("title", this.title);
		dr.AddField("title1", this.title1);
		dr.AddField("keyword", this.keyword);
		dr.AddField("desc", this.desc);
		dr.AddField("ip", this.ip);
		dr.AddField("urlStyle", this.urlStyle);

		return dr;
	}

	public String getTableName() {
		return "site";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "title" };
	}

	public String[] getKeys() {
		return new String[] { "sub" };
	}

}
