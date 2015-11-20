package tutorial.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedForeachUpdateForm {

	public List<List<Map<String, Object>>> mapItemsItems = new ArrayList<List<Map<String, Object>>>();

	public void initialize() {
		for (int i = 0; i < 10; i++) {
			List<Map<String, Object>> mapItems = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < 2; j++) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", i * 10 + j);
				m.put("name", "name" + i + j);
				mapItems.add(m);
			}
			mapItemsItems.add(mapItems);
		}
	}
}