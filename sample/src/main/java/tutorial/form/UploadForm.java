package tutorial.form;

import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.Required;

public class UploadForm {

	@Required
	public FormFile formFile;

	public FormFile[] formFiles;
}