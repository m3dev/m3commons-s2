package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.ClientValidatorForm;

import javax.annotation.Resource;

public class ClientValidatorAction {

    @ActionForm
    @Resource
    protected ClientValidatorForm clientValidatorForm;

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(input = "index.jsp")
    public String submit() {
        return "index.jsp";
    }

    @Execute(input = "index.jsp")
    public String submit2() {
        return "index.jsp";
    }
}