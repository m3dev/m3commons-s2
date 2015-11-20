package tutorial.action;

import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class ProtectAction {

    @Resource
    protected HttpServletRequest request;

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(validator = false, roles = "tomcat")
    public String tomcat() {
        return "tomcat.jsp";
    }
}