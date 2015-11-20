package com.m3.m3commons.s2.util;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.extension.jdbc.SqlLogRegistry;

public class SqlLogRegistryUtilTest extends SimpleS2TestCase {

    public void test_instantiation() throws Exception {
        SqlLogRegistryUtil target = new SqlLogRegistryUtil();
        assertNotNull(target);
    }

    public void test_getInstance_A$() throws Exception {
        SqlLogRegistry actual = SqlLogRegistryUtil.getInstance();
        assertNotNull(actual);
    }

    public void test_initialize_A$() throws Exception {
        SqlLogRegistryUtil.initialize();
    }

    public void test_loggingAll_A$Log() throws Exception {
        Log arg0 = LogFactory.getLog(this.getClass());
        SqlLogRegistryUtil.loggingAll(arg0);
    }

}
