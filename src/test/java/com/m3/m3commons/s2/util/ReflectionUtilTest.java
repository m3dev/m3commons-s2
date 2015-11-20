package com.m3.m3commons.s2.util;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtilTest extends SimpleS2TestCase {

    public String getStrValue() {
        return null;
    }

    public void setStrValue(String strValue) {
    }

    public boolean isActive() {
        return true;
    }

    public void test_instantiation() throws Exception {
        ReflectionUtil target = new ReflectionUtil();
        assertNotNull(target);
    }

    public void test_isAccessor_A$Method_accessor() throws Exception {
        assertTrue(ReflectionUtil.isAccessor(this.getClass().getDeclaredMethod("getStrValue", (Class<?>[]) null)));
        assertTrue(ReflectionUtil.isAccessor(this.getClass().getDeclaredMethod("setStrValue", String.class)));
        assertTrue(ReflectionUtil.isAccessor(this.getClass().getDeclaredMethod("isActive", (Class<?>[]) null)));
    }

    public void test_isAccessor_A$Method_notAccessor() throws Exception {
        assertFalse(ReflectionUtil.isAccessor(this.getClass().getDeclaredMethod("test_isAccessor_A$Method_accessor", (Class<?>[]) null)));
    }

    public void test_isGetter_A$Method_true() throws Exception {
        Method method = this.getClass().getDeclaredMethod("getStrValue", (Class<?>[]) null);
        boolean actual = ReflectionUtil.isGetter(method);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    public void test_isGetter_A$Method_false() throws Exception {
        Method method = this.getClass().getDeclaredMethod("test_isAccessor_A$Method_accessor", (Class<?>[]) null);
        boolean actual = ReflectionUtil.isGetter(method);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    public void test_isSetter_A$Method_true() throws Exception {
        Method method = this.getClass().getDeclaredMethod("setStrValue", String.class);
        boolean actual = ReflectionUtil.isSetter(method);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    public void test_isSetter_A$Method_false() throws Exception {
        Method method = this.getClass().getDeclaredMethod("test_isAccessor_A$Method_accessor", (Class<?>[]) null);
        boolean actual = ReflectionUtil.isSetter(method);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    public void test_toCapital_A$String_1() throws Exception {
        String str = "something";
        String actual = ReflectionUtil.toCapital(str);
        String expected = "Something";
        assertEquals(expected, actual);
    }

    public void test_toCapital_A$String_2() throws Exception {
        String str = "Something";
        String actual = ReflectionUtil.toCapital(str);
        String expected = "Something";
        assertEquals(expected, actual);
    }

    static class Sample {
        public String name;
    }

    public void test_convertMapToBean_A$Map$Object() throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("name", "aaa");
        Sample bean = new Sample();
        Sample actual = ReflectionUtil.convertMapToBean(map, bean);
        assertEquals("aaa", actual.name);
    }

}
