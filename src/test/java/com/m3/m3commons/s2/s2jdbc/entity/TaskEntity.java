package com.m3.m3commons.s2.s2jdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class TaskEntity extends AbstractEntityGenTypeSequence {

    private static final long serialVersionUID = 1L;

    public static final String taskNameSample = "some_task";

    /**
     * タスク名
     */
    @Column(length = 256, nullable = true, unique = false)
    public String taskName;

    /**
     * 処理済かどうか
     */
    @Column(nullable = false)
    public Boolean isDone = false;

    /**
     * タスクデータ(JSON形式の文字列)
     */
    @Lob
    @Column(nullable = true, unique = false)
    public String data;

}