package tutorial.form;

import org.seasar.struts.annotation.Required;

public class ClientValidatorForm {

	@Required(target = "submit")
	public String aaa;

	@Required(target = "submit2")
	public String bbb;
}