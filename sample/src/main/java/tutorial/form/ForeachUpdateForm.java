package tutorial.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeachUpdateForm {

	public List<Map<String, Object>> mapItems = new ArrayList<Map<String, Object>>();

	public void initialize() {
		for (int i = 0; i < 10; i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", i);
			m.put("name", "name" + i);
			mapItems.add(m);
		}
	}
}