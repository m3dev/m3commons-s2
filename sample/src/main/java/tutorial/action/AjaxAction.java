package tutorial.action;

import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

public class AjaxAction {

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(validator = false)
    public String hello() {
        ResponseUtil.write("こんにちは");
        return null;
    }
}