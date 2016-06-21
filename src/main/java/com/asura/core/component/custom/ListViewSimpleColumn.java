import com.asura.fui.component.custom;

import com.asura.core.html.HtmlDiv;
import com.asura.fui.FrontData;

public class ListViewSimpleColumn implements IListViewColumn {
	public void decorate(HtmlDiv column, FrontData data, int index) {
		if (index++ % 2 == 1)
			column.addStyle("background-color", "#dddddd");
		else
			column.addStyle("background-color", "rgb(242,242,242)");
	}
}
