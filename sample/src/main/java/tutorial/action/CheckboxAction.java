package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.CheckboxForm;

import javax.annotation.Resource;

public class CheckboxAction {

    @ActionForm
    @Resource
    protected CheckboxForm checkboxForm;

    @Execute(validator = false)
    public String index() {
        checkboxForm.initialize();
        return "index.jsp";
    }

    @Execute(validator = false, reset = "reset")
    public String submit() {
        return "submit.jsp";
    }
}