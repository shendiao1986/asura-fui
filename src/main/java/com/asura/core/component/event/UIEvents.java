import com.asura.fui.component.event;

import java.util.ArrayList;
import java.util.List;

public class UIEvents {
	private List<UIEvent> events;

	public List<UIEvent> getEvents() {
		return this.events;
	}

	public void setEvents(List<UIEvent> events) {
		this.events = events;
	}

	public List<UIEvent> getEvent(String key) {
		List list = new ArrayList();

		for (UIEvent event : this.events) {
			if (event.getKey().equals(key)) {
				list.add(event);
			}
		}

		return list;
	}
}
