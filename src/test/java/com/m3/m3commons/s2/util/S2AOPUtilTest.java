package com.m3.m3commons.s2.util;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

public class S2AOPUtilTest extends SimpleS2TestCase {

    public void test_getClassNameWithoutS2AOP_A$String() throws Exception {
        // ## Arrange
        String canonicalName = "com.m3.m3commons.s2.util.S2AOPUtilTest$$EnhancedByS2AOP$$134b07e";
        // ## Act
        String actual = S2AOPUtil.getClassNameWithoutS2AOP(canonicalName);
        // ## Assert
        String expected = "com.m3.m3commons.s2.util.S2AOPUtilTest";
        assertEquals(expected, actual);
    }

    @SuppressWarnings("serial")
    public void test_getNonEnhancedClass() throws Exception {
        // ## Arrange
        AbstractInterceptor interceptor = new AbstractInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                return null;
            }
        };
        TestBean enhanced = (TestBean) interceptor.createProxy(TestBean.class);
        // ## Arrange
        Class<?> originalClass = S2AOPUtil.getNonEnhancedClass(enhanced);
        // ## Assert
        assertEquals(TestBean.class.getName(), originalClass.getName());
    }

    public static class TestBean {
        public void hoge() {
        }
    }

}
