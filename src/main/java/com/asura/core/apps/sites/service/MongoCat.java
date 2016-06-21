import com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public class MongoCat extends AbstractSitebean {
	protected String sub;
	protected String name;
	protected String alias;
	protected String keyword;
	protected String desc;

	public MongoCat() {
	}

	public MongoCat(String sub, String name, String alias) {
		this.sub = sub;
		this.name = name;
		this.alias = alias;
		this.keyword = "";
		this.desc = "";
	}

	public MongoCat(String sub, String name, String alias, String keyword, String desc) {
		this.sub = sub;
		this.name = name;
		this.alias = alias;
		this.keyword = keyword;
		this.desc = desc;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", this.sub);
		dr.AddField("name", this.name);
		dr.AddField("alias", this.alias);
		dr.AddField("keyword", this.keyword);
		dr.AddField("desc", this.desc);

		return dr;
	}

	public String getTableName() {
		return "category";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "name", "alias" };
	}

	public String[] getKeys() {
		return new String[] { "sub", "name" };
	}
}
