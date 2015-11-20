package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.ValidatorForm;

import javax.annotation.Resource;

public class ValidatorAction {

    @ActionForm
    @Resource
    protected ValidatorForm validatorForm;

    @Execute(validator = false)
    public String index() {
        validatorForm.initialize();
        return "index.jsp";
    }

    @Execute(input = "index.jsp")
    public String submit() {
        return "index.jsp";
    }
}