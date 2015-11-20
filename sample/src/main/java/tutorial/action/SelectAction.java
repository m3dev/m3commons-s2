package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.SelectForm;

import javax.annotation.Resource;

public class SelectAction {

    @ActionForm
    @Resource
    protected SelectForm selectForm;

    @Execute(validator = false)
    public String index() {
        selectForm.initialize();
        return "index.jsp";
    }

    @Execute(validator = false)
    public String submit() {
        return "index.jsp";
    }
}