package com.m3.m3commons.s2.sastruts.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

/**
 * Actionクラスをフックするインタセプタの抽象基底クラス
 *
 * @author k-dewa
 */
public abstract class AbstractActionInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    public abstract Object invokeExecute(MethodInvocation invocation)
            throws Throwable;

    @Override
    public final Object invoke(MethodInvocation invocation) throws Throwable {
        if (!isExecuteMethod(invocation)) {
            return invocation.proceed();
        }
        return invokeExecute(invocation);
    }

    /**
     * Executeアノテーションがついているかどうかを判定して結果を返します。
     *
     * @param invocation
     * @return Executeアノテーションがついているか
     */
    protected boolean isExecuteMethod(MethodInvocation invocation) {
        Execute annotation = invocation.getMethod().getAnnotation(Execute.class);
        return (annotation == null) ? false : true;
    }
}
