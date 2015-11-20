package tutorial.action;

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadAction {

    @Resource
    protected HttpServletResponse response;

    @Execute(validator = false)
    public String index() {
        return "index.jsp";
    }

    @Execute(validator = false)
    public String download() {
        try {
            ResponseUtil.download(new String(
                    "サンプル.txt".getBytes("Shift_JIS"),
                    "ISO-8859-1"), "こんにちは".getBytes("Shift_JIS"));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return null;
    }
}