package com.m3.m3commons.s2.s2jdbc.analyzer.exception;

/**
 * SQLクエリ分析結果によるワーニング
 *
 * @author k-sera
 */
public class QueryAnalyzerWarning extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueryAnalyzerWarning(String message) {
        super(message);
    }

}
