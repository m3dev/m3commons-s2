package com.m3.m3commons.s2.s2jdbc.service;

import com.m3.m3commons.s2.s2jdbc.entity.TaskEntity;
import com.m3.m3commons.s2.s2jdbc.unit.S2JDBCServiceTestCase;

public class AbstractServiceGenTypeSequenceTest extends S2JDBCServiceTestCase<TaskEntityService> {

    public void test_insert_A$ENTITY() throws Exception {
        TaskEntity one = new TaskEntity();
        one.taskName = TaskEntity.taskNameSample;
        one.data = "hoge";
        int result = service.insert(one);
        assertEquals(1, result);
        TaskEntity actual = service.findById(one.id);
        assertNotNull(actual.insertTimestamp);
        assertNotNull(actual.updateTimestamp);
    }

    public void test_update_A$ENTITY() throws Exception {
        TaskEntity one = new TaskEntity();
        one.taskName = TaskEntity.taskNameSample;
        one.data = "hoge";
        service.insert(one);
        one.data = "updated";
        int result = service.update(one);
        assertEquals(1, result);
        TaskEntity actual = service.findById(one.id);
        assertEquals("updated", actual.data);
    }

}
