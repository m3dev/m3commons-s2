package com.m3.m3commons.s2.sastruts.action;

import com.m3.m3commons.s2.sastruts.annotation.PreExecute;
import com.m3.m3commons.s2.sastruts.annotation.PreExecuteMethod;
import org.seasar.struts.annotation.Execute;

public class Index2Action extends IndexAction {

    @PreExecuteMethod
    public void lastExecuted() {
        preExecuted1 = false;
        preExecuted2 = false;
    }

    @PreExecute
    @Execute
    public void statckTest() {
    }

}
