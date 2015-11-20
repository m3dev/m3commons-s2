package com.m3.m3commons.s2.util;

/**
 * S2AOPに関するユーティリティ
 *
 * @author k-dewa
 */
public class S2AOPUtil {

    /**
     * S2でenhancedされたクラスの{@link Object#toString()}の出力から<br>
     * 自動設定されている文字列部分を取り除きます。
     *
     * @param canonicalName
     * @return 結果
     */
    public static String getClassNameWithoutS2AOP(String canonicalName) {
        return canonicalName.replaceFirst("\\$\\$EnhancedByS2AOP\\$\\$.+", "");
    }

    /**
     * S2でenhancedされていないクラスを返します。
     *
     * @param obj
     * @return S2でenhancedされていないクラス
     */
    public static Class<?> getNonEnhancedClass(Object obj) {
        String enhancedClassName = obj.getClass().getName();
        String originalClassName = getClassNameWithoutS2AOP(enhancedClassName);
        try {
            return Class.forName(originalClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
