package com.m3.m3commons.s2.s2jdbc.service;

import com.m3.m3commons.s2.s2jdbc.entity.TaskEntity;
import com.m3.m3commons.s2.s2jdbc.unit.S2JDBCServiceTestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.framework.exception.SQLRuntimeException;

import java.util.List;

public class AbstractServiceTest extends S2JDBCServiceTestCase<TaskEntityService> {

    public void test_getJdbcManager_A$() throws Exception {
        JdbcManager actual = service.getJdbcManager();
        assertNotNull(actual);
    }

    public void test_setJdbcManager_A$JdbcManager() throws Exception {
        Mockery context = new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        final JdbcManager arg0 = context.mock(JdbcManager.class);
        context.checking(new Expectations() {
            {
            }
        });
        service.setJdbcManager(arg0);
    }

    public void test_findById_A$Long() throws Exception {
        TaskEntity expected = new TaskEntity();
        expected.taskName = TaskEntity.taskNameSample;
        expected.data = "hogehoge";
        service.insert(expected);
        TaskEntity actual = service.findById(expected.id);
        assertEquals(expected.id, actual.id);
        assertEquals(expected.taskName, actual.taskName);
        assertEquals(expected.data, actual.data);
        try {
            service.findById(null);
            fail("Expected exception did not occurred!");
        } catch (IllegalArgumentException e) {
        }
    }

    private static int findAllCount = 0;

    public void test_findAll_A$_01() throws Exception {
        ignoreQueryAnalyzerWarning();
        List<TaskEntity> actual = service.findAll();
        findAllCount = actual.size();
    }

    public void test_countAll_A$() throws Exception {
        ignoreQueryAnalyzerWarning();
        long actual = service.countAll();
        assertEquals(findAllCount, actual);
    }

    public void test_findAllOrderByAsc_A$PropertyName() throws Exception {
        ignoreQueryAnalyzerWarning();
        PropertyName<TaskEntity> arg0 = new PropertyName<TaskEntity>("id");
        assertNotNull(service.findAllOrderByAsc(arg0));
    }

    public void test_findAllOrderByDesc_A$PropertyName() throws Exception {
        ignoreQueryAnalyzerWarning();
        PropertyName<TaskEntity> arg0 = new PropertyName<TaskEntity>("id");
        assertNotNull(service.findAllOrderByDesc(arg0));
    }

    public void test_find_A$Integer$Integer() throws Exception {
        ignoreQueryAnalyzerWarning();
        service.find(10, (Integer) null);
        service.find(10, 1);
        service.find(10, 2);
        try {
            service.find(null, (Integer) null);
            fail("Expected exception did not occurred!");
        } catch (IllegalArgumentException e) {
        }
    }

    public void test_find_A$Integer$Long() throws Exception {
        ignoreQueryAnalyzerWarning();
        Mockery context = new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
        Integer arg0 = 10;
        Long arg1 = null;
        context.checking(new Expectations() {
            {
            }
        });
        List<TaskEntity> actual = service.find(arg0, arg1);
        assertNotNull(actual);
        try {
            service.find(null, arg1);
            fail("Expected exception did not occurred!");
        } catch (IllegalArgumentException e) {
        }
    }

    public void test_findOrderByAsc_A$Integer$Integer$PropertyName()
            throws Exception {
        ignoreQueryAnalyzerWarning();
        Integer arg0 = 1;
        Integer arg1 = 2;
        PropertyName<TaskEntity> arg2 = new PropertyName<TaskEntity>("id");
        assertNotNull(service.findOrderByAsc(arg0, arg1, arg2));
    }

    public void test_findOrderByDesc_A$Integer$Integer$PropertyName()
            throws Exception {
        ignoreQueryAnalyzerWarning();
        Integer arg0 = 1;
        Integer arg1 = 2;
        PropertyName<TaskEntity> arg2 = new PropertyName<TaskEntity>("id");
        assertNotNull(service.findOrderByDesc(arg0, arg1, arg2));
    }

    public void test_deleteIfExist_A$ENTITY() throws Exception {
        TaskEntity one = new TaskEntity();
        one.taskName = TaskEntity.taskNameSample;
        one.data = "hogehoge";
        service.insert(one);
        TaskEntity arg0 = service.findById(one.id);
        assertEquals(1, service.deleteIfExist(arg0));
        assertEquals(0, service.deleteIfExist(arg0));
    }

    public void test_findByIdForUpdate_A$Long() throws Exception {
        TaskEntity expected = new TaskEntity();
        expected.taskName = TaskEntity.taskNameSample;
        expected.data = "hogehoge";
        service.insert(expected);
        TaskEntity actual = service.findByIdForUpdate(expected.id);
        assertEquals(expected.id, actual.id);
    }

    public void test_setEntityClass_A$Class() throws Exception {
        Class<TaskEntity> arg0 = TaskEntity.class;
        service.setEntityClass(arg0);
    }

    public void test_getNextVal_A$String() throws Exception {
        String sequenceName = "task_entity_id";
        Long actual = service.getNextVal(sequenceName);
        assertNotNull(actual);
    }

    public void test_getNextVal_A$String_null() throws Exception {
        String sequenceName = null;
        try {
            service.getNextVal(sequenceName);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("\"sequenceName\" is empty.", e.getLocalizedMessage());
        }
    }

    public void test_getNextVal_A$String_empty() throws Exception {
        String sequenceName = "";
        try {
            service.getNextVal(sequenceName);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("\"sequenceName\" is empty.", e.getLocalizedMessage());
        }
    }

    public void test_getNextVal_A$String_invalidSequenceName() throws Exception {
        String sequenceName = "zzz_invalid_sequence_name";
        try {
            service.getNextVal(sequenceName);
            fail();
        } catch (SQLRuntimeException e) {
        }
    }

}
