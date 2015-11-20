package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.RadioForm;

import javax.annotation.Resource;

public class RadioAction {

    @ActionForm
    @Resource
    protected RadioForm radioForm;

    @Execute(validator = false)
    public String index() {
        radioForm.initialize();
        return "index.jsp";
    }

    @Execute(validator = false)
    public String submit() {
        return "index.jsp";
    }
}