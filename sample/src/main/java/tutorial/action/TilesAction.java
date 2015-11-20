package tutorial.action;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import tutorial.form.TilesForm;

import javax.annotation.Resource;

public class TilesAction {

    @ActionForm
    @Resource
    protected TilesForm tilesForm;

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }
}