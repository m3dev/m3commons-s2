package com.m3.m3commons.s2.sastruts.helper;

import org.seasar.extension.unit.S2TestCase;

import javax.servlet.http.HttpServletRequest;

public class RequestHelperTest extends S2TestCase {

    public static class SampleClass {
        public String name;
    }

    public void test_getRequest_A$() throws Exception {
        HttpServletRequest actual = new RequestHelper().getRequest();
        assertNotNull(actual);
    }

    public void test_setAttribute_A$String$T() throws Exception {
        RequestHelper target = new RequestHelper();
        String arg0 = "hoge";
        SampleClass arg1 = new SampleClass();
        target.setAttribute(arg0, arg1);
        SampleClass result = target.getAttribute(arg0);
        assertNotNull(result);
    }

    public void test_getAttribute_A$String() throws Exception {
        RequestHelper target = new RequestHelper();
        String arg0 = "hoge";
        SampleClass arg1 = new SampleClass();
        target.setAttribute(arg0, arg1);
        SampleClass result = target.getAttribute(arg0);
        assertNotNull(result);
    }

    public void test_getQueryString_A$() throws Exception {
        String expected = "hogehoge";
        RequestHelper target = new RequestHelper();
        target.getRequest().setAttribute("javax.servlet.forward.query_string",
                expected);
        String actual = target.getQueryString();
        assertEquals(expected, actual);
    }

    public void test_getServletPath_A$() throws Exception {
        String expected = "hogehoge";
        RequestHelper target = new RequestHelper();
        target.getRequest().setAttribute("javax.servlet.forward.servlet_path",
                expected);
        String actual = target.getServletPath();
        assertEquals(expected, actual);
    }

    public void test_setAttribute_A$String$Object() throws Exception {
        RequestHelper target = new RequestHelper();
        // given
        String key = "aaa";
        Object value = "bbb";
        // e.g. : given(mocked.called()).willReturn(1);
        // when
        target.setAttribute(key, value);
        // then
        // e.g. : verify(mocked).called();
    }

}
