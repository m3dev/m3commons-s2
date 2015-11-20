package tutorial.form;

import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Required;

public class AddForm {

    @Required
    @IntegerType
    public String arg1;

    @Required
    @IntegerType
    public String arg2;
}
