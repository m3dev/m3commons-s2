package com.m3.m3commons.s2.s2jdbc.analyzer.exception;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;

public class QueryAnalyzerWarningTest extends SimpleS2TestCase {

    public void test_instantiation() throws Exception {
        String message = null;
        QueryAnalyzerWarning target = new QueryAnalyzerWarning(message);
        assertNotNull(target);
    }

}
