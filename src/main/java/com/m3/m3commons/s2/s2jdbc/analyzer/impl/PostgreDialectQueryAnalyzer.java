package com.m3.m3commons.s2.s2jdbc.analyzer.impl;

import com.m3.m3commons.s2.s2jdbc.analyzer.QueryAnalyzer;
import com.m3.m3commons.s2.s2jdbc.analyzer.exception.QueryAnalyzerWarning;
import com.m3.m3commons.s2.util.S2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.jdbc.JdbcManager;

import java.util.List;

/**
 * PostgreDialect用のQueryAnalyzer
 *
 * @author k-sera
 */
public class PostgreDialectQueryAnalyzer implements QueryAnalyzer {

    private boolean loggingOnly = false;

    protected S2 s2 = new S2();

    protected JdbcManager jdbcManager;

    protected Log log = LogFactory.getLog(PostgreDialectQueryAnalyzer.class);

    public void analyze(String sql) throws QueryAnalyzerWarning {
        if (sql == null) {
            return;
        }
        sql = sql.trim();
        if (!sql.startsWith("select") && !sql.startsWith("select")) {
            return;
        }
        sql = "explain analyze " + sql;
        if (jdbcManager == null) {
            jdbcManager = s2.getComponent(JdbcManager.class);
        }
        // PostgreSQLでは統計情報に基いて（テーブルのrow数が少ない場合など）
        // オプティマイザがindex scanではなくseq scanを選択するケースがあるので
        // この機能を一時的にoffにします
        jdbcManager.callBySql("set enable_seqscan to off;").execute();
        List<String> resultList = jdbcManager.selectBySql(String.class, sql, new Object[]{}).getResultList();
        log.debug("--------------------------------------------");
        log.debug(sql);
        for (String line : resultList) {
            log.debug(line);
            if (line.matches(".*Seq Scan.*")) {
                String message = "Performance issue : Sequential scan query - \"" + sql + "\"";
                if (loggingOnly) {
                    log.warn(message);
                } else {
                    throw new QueryAnalyzerWarning(message);
                }
                break;
            }
        }
    }

    public void setLoggingOnly(boolean loggingOnly) {
        this.loggingOnly = loggingOnly;
    }

    public boolean isLoggingOnly() {
        return loggingOnly;
    }

}
