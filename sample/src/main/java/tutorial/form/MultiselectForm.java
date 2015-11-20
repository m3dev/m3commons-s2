package tutorial.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class MultiselectForm implements Serializable {

	private static final long serialVersionUID = 1L;

	public String[] select1;

	public String[] select2;

	public void initialize() {
		select1 = new String[] { "2", "3" };
		select2 = new String[0];
	}

	public void reset() {
		select1 = new String[0];
	}

	public void reset2() {
		select2 = new String[0];
	}
}