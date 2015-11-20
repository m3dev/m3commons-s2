package com.m3.m3commons.s2.s2jdbc.analyzer;

import com.m3.m3commons.s2.s2jdbc.analyzer.exception.QueryAnalyzerWarning;

/**
 * SQLクエリのアナライザー
 *
 * @author k-sera
 */
public interface QueryAnalyzer {

    void analyze(String sql) throws QueryAnalyzerWarning;

    boolean isLoggingOnly();

    void setLoggingOnly(boolean loggingOnly);

}
