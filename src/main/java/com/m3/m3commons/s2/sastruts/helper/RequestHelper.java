package com.m3.m3commons.s2.sastruts.helper;

import org.seasar.struts.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Requestに関するヘルパークラス
 *
 * @author k-dewa
 */
// @Component(instance = InstanceType.REQUEST)
public class RequestHelper {

    private HttpServletRequest req;

    public RequestHelper() {
        this(RequestUtil.getRequest());
    }

    public RequestHelper(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalStateException("HttpServletRequestがnullになっています。"
                    + "このクラスのインスタンスをインタセプタなどスコープの違うコンポーネントのフィールドにもたせていませんか？"
                    + "メソッド内のローカル変数に変更させて下さい。");
        }
        this.req = request;
    }

    public HttpServletRequest getRequest() {
        return this.req;
    }

    public <T> void setAttribute(String key, T value) {
        this.req.setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key) {
        return (T) this.req.getAttribute(key);
    }

    /**
     * SAStrutsがセットしたrequest属性にある元々のクエリーストリングを取得する
     *
     * @return 元々のクエリーストリング
     */
    public String getQueryString() {
        String key = "javax.servlet.forward.query_string";
        return (String) req.getAttribute(key);
    }

    /**
     * SAStrutsがセットしたrequest属性にある元々のservletPathを取得する<br>
     *
     * @return 元々のservletPath<br>
     * (ex.) http://localhost:8080/m/hoge/ → "/hoge/"
     */
    public String getServletPath() {
        String key = "javax.servlet.forward.servlet_path";
        return (String) req.getAttribute(key);
    }
}
