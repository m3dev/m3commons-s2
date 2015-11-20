package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.AddForm;

import javax.annotation.Resource;

public class AddAction {

    public Integer result;

    @ActionForm
    @Resource
    protected AddForm addForm;

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(input = "index.jsp")
    public String submit() {
        result = Integer.valueOf(addForm.arg1) + Integer.valueOf(addForm.arg2);
        return "index.jsp";
    }

}