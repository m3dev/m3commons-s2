package com.m3.m3commons.s2.creator;

import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.convention.NamingConvention;

/**
 * TaskCreator
 *
 * @author k-sera
 */
public class TaskCreator extends ComponentCreatorImpl {

    private String taskSuffix = "Task";

    /**
     * 指定された{@link NamingConvention 命名規約}に従った{@link TaskCreator}を作成します。
     *
     * @param namingConvention 命名規約
     */
    public TaskCreator(NamingConvention namingConvention) {
        super(namingConvention);
        setNameSuffix(taskSuffix);
        setEnableInterface(true);
        setEnableAbstract(true);
    }

    public String getTaskSuffix() {
        return taskSuffix;
    }

    public void setTaskSuffix(String taskSuffix) {
        this.taskSuffix = taskSuffix;
    }

}
