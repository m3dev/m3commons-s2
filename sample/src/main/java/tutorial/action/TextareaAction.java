package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.TextareaForm;

import javax.annotation.Resource;

public class TextareaAction {

    @ActionForm
    @Resource
    protected TextareaForm textareaForm;

    @Execute(validator = false)
    public String index() {
        textareaForm.initialize();
        return "index.jsp";
    }

    @Execute(validator = false)
    public String submit() {
        return "index.jsp";
    }
}