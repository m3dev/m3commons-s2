package com.m3.m3commons.s2.s2jdbc.unit;

import com.m3.m3commons.s2.s2jdbc.service.TaskEntityService;

public class S2JDBCServiceTestCaseTest extends
        S2JDBCServiceTestCase<TaskEntityService> {

    public void test_setUp_A$() throws Exception {
        // SKIP
    }

    public void test_setUpAfterContainerInit_A$() throws Exception {
        // SKIP
    }

    public void test_tearDown_A$() throws Exception {
        // SKIP
    }

    public void test_init_A$SERVICE() throws Exception {
        S2JDBCServiceTestCase<TaskEntityService> target = new S2JDBCServiceTestCaseTest();
        TaskEntityService actual = target.init(new TaskEntityService());
        assertNotNull(actual);
    }

    public void test_init_A$Class() throws Exception {
        S2JDBCServiceTestCase<TaskEntityService> target = new S2JDBCServiceTestCaseTest();
        TaskEntityService actual = target.init(TaskEntityService.class);
        assertNotNull(actual);
    }

    public void test_setupServiceClass_A$() throws Exception {
        S2JDBCServiceTestCase<TaskEntityService> target = new S2JDBCServiceTestCaseTest();
        target.setupServiceClass();
    }

}
