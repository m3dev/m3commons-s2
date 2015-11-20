package tutorial.action;

import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class LogoutAction {

    @Resource
    protected HttpSession session;

    @Execute(validator = false)
    public String index() {
        session.invalidate();
        return "index.jsp";
    }
}