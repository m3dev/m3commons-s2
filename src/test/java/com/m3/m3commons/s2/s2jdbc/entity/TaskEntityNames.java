package com.m3.m3commons.s2.s2jdbc.entity;

import org.seasar.extension.jdbc.name.PropertyName;

import javax.annotation.Generated;
import java.util.Calendar;

/**
 * {@link TaskEntity}のプロパティ名の集合です。
 */
@Generated(value = {"S2JDBC-Gen 2.4.40",
        "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/04/30 16:18:34")
public class TaskEntityNames {

    /**
     * taskNameのプロパティ名を返します。
     *
     * @return taskNameのプロパティ名
     */
    public static PropertyName<String> taskName() {
        return new PropertyName<String>("taskName");
    }

    /**
     * isDoneのプロパティ名を返します。
     *
     * @return isDoneのプロパティ名
     */
    public static PropertyName<Boolean> isDone() {
        return new PropertyName<Boolean>("isDone");
    }

    /**
     * dataのプロパティ名を返します。
     *
     * @return dataのプロパティ名
     */
    public static PropertyName<String> data() {
        return new PropertyName<String>("data");
    }

    /**
     * idのプロパティ名を返します。
     *
     * @return idのプロパティ名
     */
    public static PropertyName<Long> id() {
        return new PropertyName<Long>("id");
    }

    /**
     * insertTimestampのプロパティ名を返します。
     *
     * @return insertTimestampのプロパティ名
     */
    public static PropertyName<Calendar> insertTimestamp() {
        return new PropertyName<Calendar>("insertTimestamp");
    }

    /**
     * updateTimestampのプロパティ名を返します。
     *
     * @return updateTimestampのプロパティ名
     */
    public static PropertyName<Calendar> updateTimestamp() {
        return new PropertyName<Calendar>("updateTimestamp");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TaskEntityNames extends PropertyName<TaskEntity> {

        /**
         * インスタンスを構築します。
         */
        public _TaskEntityNames() {
        }

        /**
         * インスタンスを構築します。
         *
         * @param name 名前
         */
        public _TaskEntityNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         *
         * @param parent 親
         * @param name   名前
         */
        public _TaskEntityNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * taskNameのプロパティ名を返します。
         *
         * @return taskNameのプロパティ名
         */
        public PropertyName<String> taskName() {
            return new PropertyName<String>(this, "taskName");
        }

        /**
         * isDoneのプロパティ名を返します。
         *
         * @return isDoneのプロパティ名
         */
        public PropertyName<Boolean> isDone() {
            return new PropertyName<Boolean>(this, "isDone");
        }

        /**
         * dataのプロパティ名を返します。
         *
         * @return dataのプロパティ名
         */
        public PropertyName<String> data() {
            return new PropertyName<String>(this, "data");
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Long> id() {
            return new PropertyName<Long>(this, "id");
        }

        /**
         * insertTimestampのプロパティ名を返します。
         *
         * @return insertTimestampのプロパティ名
         */
        public PropertyName<Calendar> insertTimestamp() {
            return new PropertyName<Calendar>(this, "insertTimestamp");
        }

        /**
         * updateTimestampのプロパティ名を返します。
         *
         * @return updateTimestampのプロパティ名
         */
        public PropertyName<Calendar> updateTimestamp() {
            return new PropertyName<Calendar>(this, "updateTimestamp");
        }
    }
}