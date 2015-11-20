package com.m3.m3commons.s2.sastruts.action;

import com.m3.m3commons.s2.s2jdbc.service.TaskEntityService;
import com.m3.m3commons.s2.sastruts.annotation.PreExecute;
import com.m3.m3commons.s2.sastruts.annotation.PreExecuteMethod;
import org.seasar.struts.annotation.Execute;

import javax.annotation.Resource;

public class IndexAction {

    @Resource
    public TaskEntityService taskEntityService;

    public long count;

    public boolean preExecuted1 = false;
    public boolean preExecuted2 = false;

    @PreExecuteMethod
    public void preExecuteMethod1() {
        preExecuted1 = true;
    }

    @PreExecuteMethod
    public void preExecuteMethod2() {
        preExecuted2 = true;
    }

    @Execute(validator = false)
    public String index() {
        count = taskEntityService.countAll();
        return "ret";
    }

    @PreExecute(excludeMethods = {"preExecuteMethod1"})
    @Execute(validator = false)
    public String index2() {
        count = taskEntityService.countAll();
        return "ret";
    }

    @PreExecute(autoExecute = false, includeMethods = {"preExecuteMethod1"})
    @Execute(validator = false)
    public String index3() {
        count = taskEntityService.countAll();
        return "ret";
    }

}
