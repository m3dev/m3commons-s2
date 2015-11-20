package com.m3.m3commons.s2.sastruts.helper;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;

import javax.servlet.ServletOutputStream;

public class ResponseHelperTest extends SimpleS2TestCase {

    public void test_getOutputStream_A$() throws Exception {
        ResponseHelper target = new ResponseHelper();
        ServletOutputStream actual = target.getOutputStream();
        assertNotNull(actual);
    }

    public void test_setStatus_A$int() throws Exception {
        ResponseHelper target = new ResponseHelper();
        int arg0 = 200;
        target.setStatus(arg0);
    }

    public void test_write_A$String() throws Exception {
        ResponseHelper target = new ResponseHelper();
        // given
        String text = "sdfsdfasdfafds";
        // e.g. : given(mocked.called()).willReturn(1);
        // when
        target.write(text);
        // then
        // e.g. : verify(mocked).called();
    }

    public void test_write_A$String$String() throws Exception {
        ResponseHelper target = new ResponseHelper();
        // given
        String text = "<html><head></head><body></body></html>";
        String contentType = "text/html";
        // e.g. : given(mocked.called()).willReturn(1);
        // when
        target.write(text, contentType);
        // then
        // e.g. : verify(mocked).called();
    }

    public void test_write_A$String$String$String() throws Exception {
        ResponseHelper target = new ResponseHelper();
        // given
        String text = "<html><head></head><body></body></html>";
        String contentType = "text/html";
        String encoding = "UTF-8";
        // e.g. : given(mocked.called()).willReturn(1);
        // when
        target.write(text, contentType, encoding);
        // then
        // e.g. : verify(mocked).called();
    }

}
