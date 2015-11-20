package com.m3.m3commons.s2.sastruts.unit;

import com.m3.m3commons.s2.sastruts.action.IndexAction;

public class SAStrutsActionTestCaseTest extends
        SAStrutsActionTestCase<IndexAction> {

    public void test_setUp_A$() throws Exception {
        // SKIP
    }

    public void test_setUpAfterBindFields_A$() throws Throwable {
        SAStrutsActionTestCase<IndexAction> target = new SAStrutsActionTestCaseTest();
        target.setUpAfterBindFields();
    }

    public void test_tearDown_A$() throws Exception {
        // SKIP
    }

    public void test_init_A$ACTION() throws Exception {
        SAStrutsActionTestCase<IndexAction> target = new SAStrutsActionTestCaseTest();
        IndexAction actual = target.init(new IndexAction());
        assertNotNull(actual);
    }

    public void test_init_A$Class() throws Exception {
        SAStrutsActionTestCase<IndexAction> target = new SAStrutsActionTestCaseTest();
        IndexAction actual = target.init(IndexAction.class);
        assertNotNull(actual);
    }

    public void test_autoInjection_A$ACTION() throws Exception {
        SAStrutsActionTestCase<IndexAction> target = new SAStrutsActionTestCaseTest();
        IndexAction actual = target.autoInjection(new IndexAction());
        assertNotNull(actual);
        assertNotNull(actual.taskEntityService);
    }

}
