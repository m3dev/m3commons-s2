package com.m3.m3commons.s2.util;

import org.apache.commons.logging.Log;
import org.seasar.extension.jdbc.SqlLogRegistry;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;

/**
 * {@link SqlLogRegistry}に関するユーティリティ
 *
 * @author k-sera
 */
public final class SqlLogRegistryUtil {

    /**
     * {@link SqlLogRegistry}インスタンスを返します。
     *
     * @return {@link SqlLogRegistry}インスタンス
     */
    public static final SqlLogRegistry getInstance() {
        return SqlLogRegistryLocator.getInstance();
    }

    /**
     * {@link SqlLogRegistry}インスタンスを初期化します。
     */
    public static final void initialize() {
        SqlLogRegistry sqlLogRegistry = getInstance();
        sqlLogRegistry.clear();
    }

    /**
     * infoレベルで全てのクエリをログ出力します。
     */
    public static final void loggingAll(Log log) {
        SqlLogRegistry sqlLogRegistry = getInstance();
        int count = sqlLogRegistry.getSize();
        for (int i = 0; i < count; i++) {
            log.info(sqlLogRegistry.get(i).getCompleteSql());
        }
    }
}
