package com.m3.m3commons.s2.util;

import org.apache.commons.lang.CharUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * リフレクションAPIを使用するユーティリティ
 *
 * @author k-sera
 */
public class ReflectionUtil extends
        org.seasar.framework.util.tiger.ReflectionUtil {

    private static final String SET = "set";

    private static final String GET = "get";

    private static final String IS = "is";

    /**
     * メソッドがアクセサメソッドかどうかを調べる。
     *
     * @param method メソッド
     * @return アクセサであれば{@code true}
     */
    public static boolean isAccessor(Method method) {
        return isGetter(method) || isSetter(method);
    }

    /**
     * メソッドがgetterかどうか調べる。
     *
     * @param method メソッド
     * @return getterであれば{@code true}
     */
    public static boolean isGetter(Method method) {
        Class<?> returnType = method.getReturnType();
        String name = method.getName();
        if (returnType == void.class || returnType == Void.class) {
            return false;
        } else if (returnType == boolean.class || returnType == Boolean.class) {
            boolean result = name.startsWith(IS) && name.length() > 2
                    && CharUtils.isAsciiAlphaUpper(name.toCharArray()[2]);
            if (result == true) {
                return true;
            }
        }
        return name.startsWith(GET) && name.length() > 3
                && CharUtils.isAsciiAlphaUpper(name.toCharArray()[3]);
    }

    /**
     * メソッドがsetterかどうか調べる。
     *
     * @param method メソッド
     * @return setterであれば{@code true}
     */
    public static boolean isSetter(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            return false;
        }
        String name = method.getName();
        return name.startsWith(SET) && name.length() > 3
                && CharUtils.isAsciiAlphaUpper(name.toCharArray()[3]);
    }

    /**
     * 文字列の1文字目を大文字にする。
     *
     * @param str 入力文字列
     * @return 出力文字列
     */
    public static String toCapital(final String str) {
        final char[] ch = str.toCharArray();
        ch[0] = Character.toUpperCase(ch[0]);
        return new String(ch);
    }

    /**
     * Map型オブジェクトからJavaBeanのインスタンスに詰め替えます。
     *
     * @param <T>  Bean型
     * @param map  Mapオブジェクト
     * @param bean 書き換えるBean型インスタンス
     * @return　書き換えたBean型インスタンス
     */
    public static <T> T convertMapToBean(Map<Object, Object> map, T bean) {
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Set<Object> keys = map.keySet();
        for (Object key : keys) {
            for (Field field : fields) {
                if (field.getName().equals(key)) {
                    field.setAccessible(true);
                    try {
                        field.set(bean, map.get(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return bean;
    }

}
