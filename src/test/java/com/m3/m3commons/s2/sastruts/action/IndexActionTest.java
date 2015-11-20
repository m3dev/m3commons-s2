package com.m3.m3commons.s2.sastruts.action;

import com.m3.m3commons.s2.s2jdbc.service.TaskEntityService;
import com.m3.m3commons.s2.sastruts.unit.SAStrutsActionTestCase;
import org.jmock.Expectations;

public class IndexActionTest extends SAStrutsActionTestCase<IndexAction> {

    public void test_ready() throws Exception {
        assertNotNull(action);
        assertNotNull(action.taskEntityService);
    }

    public void test_index_A$() throws Exception {
        ignoreQueryAnalyzerWarning();
        String expected = "ret";
        assertEquals(expected, action.index());
        action = init(new IndexAction());
        action.taskEntityService = jmock2.mock(TaskEntityService.class);
        jmock2.checking(new Expectations() {
            {
                one(action.taskEntityService).countAll();
                will(returnValue(999L));
            }
        });
        assertEquals(expected, action.index());
        assertEquals(999L, action.count);
    }
}
