package com.m3.m3commons.s2.sastruts.helper;

import org.seasar.struts.util.ResponseUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServletResponseに関するヘルパークラス
 *
 * @author k-sera
 */
public class ResponseHelper {

    private HttpServletResponse response;

    public ResponseHelper() {
        this(ResponseUtil.getResponse());
    }

    public ResponseHelper(HttpServletResponse response) {
        if (response == null) {
            throw new IllegalStateException("HttpServletResponseがnullになっています。"
                    + "このクラスのインスタンスをインタセプタなどスコープの違うコンポーネントのフィールドにもたせていませんか？"
                    + "メソッド内のローカル変数に変更させて下さい。");
        }
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public void setStatus(int status) {
        response.setStatus(status);
    }

    public void write(String text) {
        ResponseUtil.write(text);
    }

    public void write(String text, String contentType) {
        ResponseUtil.write(text, contentType);
    }

    public void write(String text, String contentType, String encoding) {
        ResponseUtil.write(text, contentType, encoding);
    }

}
