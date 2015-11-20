package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.ForeachForm;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForeachAction {

    public List<Map<String, Object>> mapItems = new ArrayList<Map<String, Object>>();

    @ActionForm
    @Resource
    protected ForeachForm foreachForm;

    @Execute(validator = false)
    public String index() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", i);
            m.put("name", "name" + i);
            mapItems.add(m);
        }
        return "index.jsp";
    }

    @Execute(validator = false, urlPattern = "result/{id}")
    public String result() {
        return "result.jsp";
    }
}
