import com.asura.fui.component.layout;

public class LayoutValue {
	private String key;
	private String tag;
	private String parent;
	private int row;
	private int column;
	private int width;
	private int height;
	private int ml;
	private int mr;
	private int mt;
	private int mb;
	private int pos;
	private String styles;
	private String attrs;
	private boolean noWidth;

	public boolean isNoWidth() {
		return this.noWidth;
	}

	public void setNoWidth(boolean noWidth) {
		this.noWidth = noWidth;
	}

	public int getPos() {
		return this.pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getMl() {
		return this.ml;
	}

	public void setMl(int ml) {
		this.ml = ml;
	}

	public int getMr() {
		return this.mr;
	}

	public void setMr(int mr) {
		this.mr = mr;
	}

	public int getMt() {
		return this.mt;
	}

	public void setMt(int mt) {
		this.mt = mt;
	}

	public int getMb() {
		return this.mb;
	}

	public void setMb(int mb) {
		this.mb = mb;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStyles() {
		return this.styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public String getAttrs() {
		return this.attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
