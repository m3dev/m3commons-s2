package com.m3.m3commons.s2.creator;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;
import org.seasar.framework.convention.NamingConvention;

import static org.mockito.Mockito.*;

public class TaskCreatorTest extends SimpleS2TestCase {

    public void test_instantiation() throws Exception {
        NamingConvention namingConvention = mock(NamingConvention.class);
        TaskCreator target = new TaskCreator(namingConvention);
        assertNotNull(target);
    }

}
