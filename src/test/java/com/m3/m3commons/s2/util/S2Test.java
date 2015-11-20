package com.m3.m3commons.s2.util;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class S2Test extends SimpleS2TestCase {

    @Resource
    protected HttpServletRequest request;

    public void test_lookup_A$Class() throws Exception {
        S2 target = new S2();
        HttpServletRequest actual = target.lookup(HttpServletRequest.class);
        assertEquals(request, actual);
    }

    public void test_lookup_A$String() throws Exception {
        S2 target = new S2();
        HttpServletRequest actual = target.lookup("request");
        assertEquals(request, actual);
    }

    public void test_getComponent_A$Class() throws Exception {
        S2 target = new S2();
        HttpServletRequest actual = target
                .getComponent(HttpServletRequest.class);
        assertEquals(request, actual);
    }

    public void test_getComponent_A$String() throws Exception {
        S2 target = new S2();
        HttpServletRequest actual = target.getComponent("request");
        assertEquals(request, actual);
    }

}
