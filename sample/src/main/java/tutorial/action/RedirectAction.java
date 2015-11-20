package tutorial.action;

import org.seasar.struts.annotation.Execute;

public class RedirectAction {

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(validator = false, redirect = true)
    public String showGoogle() {
        return "http://www.google.co.jp";
    }
}