package com.m3.m3commons.s2.s2jdbc.interceptor;

import com.m3.m3commons.s2.s2jdbc.analyzer.QueryAnalyzer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.jdbc.SqlLogRegistry;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.framework.env.Env;

import java.lang.reflect.Method;

/**
 * 本番環境以外でServiceクラスで発行したSQLクエリをログ出力するインタセプタ
 *
 * @author k-sera
 */
public class TraceServiceQueryInterceptor implements MethodInterceptor {

    protected Log log = LogFactory.getLog(this.getClass());

    protected QueryAnalyzer queryAnalyzer;

    private boolean availableFlag = true;

    private boolean skipAtMavenTesting = true;

    public boolean isAvailableFlag() {
        return availableFlag;
    }

    public void setAvailableFlag(boolean availableFlag) {
        this.availableFlag = availableFlag;
    }

    public QueryAnalyzer getQueryAnalyzer() {
        return queryAnalyzer;
    }

    public void setQueryAnalyzer(QueryAnalyzer queryAnalyzer) {
        this.queryAnalyzer = queryAnalyzer;
    }

    public boolean isSkipAtMavenTesting() {
        return skipAtMavenTesting;
    }

    public void setSkipAtMavenTesting(boolean skipAtMavenTesting) {
        this.skipAtMavenTesting = skipAtMavenTesting;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 本番環境ではskip
        // availableFlagがfalseの場合もskip
        // mvn test時にskip（選択可）
        skipAtMavenTesting = skipAtMavenTesting && System.getProperty("localRepository") != null;

        if (Env.getValue().equals(Env.PRODUCT) || !availableFlag || skipAtMavenTesting) {
            return invocation.proceed();
        }
        // サービスクラスの場合のみ
        Object instance = invocation.getThis();
        Method method = invocation.getMethod();
        if (instance instanceof S2AbstractService<?>) {
            SqlLogRegistry sqlLogRegistry = SqlLogRegistryLocator.getInstance();
            sqlLogRegistry.clear();
            Object value = invocation.proceed();
            int count = sqlLogRegistry.getSize();
            for (int i = 0; i < count; i++) {
                String sql = sqlLogRegistry.get(i).getCompleteSql();
                log.info(method.toGenericString() + " [" + i + "] -> " + sql);
                if (queryAnalyzer != null) {
                    queryAnalyzer.analyze(sql);
                }
            }
            return value;
        }
        return invocation.proceed();
    }
}
