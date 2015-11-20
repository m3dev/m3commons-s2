package com.m3.m3commons.s2.sastruts.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Actionクラスの@Executeメソッド後をフックする設定。
 *
 * @author k-sera
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostExecute {

    /**
     * autoExecuteの全ての@PostExecuteMethodを実行します。<br>
     * includeMethodsやexcludeMethodsの指定がある場合はこの設定をベースに上書きされます。
     *
     * @return フックするかどうか
     */
    boolean autoExecute() default true;

    /**
     * 実行する@PostExecuteMethod一覧です。<br>
     * autoExecuteの設定をベースに上書きします。
     *
     * @return 実行する@PostExecuteMethod一覧
     */
    String[] includeMethods() default {};

    /**
     * 実行しない@PostExecuteMethod一覧です。<br>
     * autoExecuteの設定をベースに上書きします。
     *
     * @return　実行しない@PostExecuteMethod一覧
     */
    String[] excludeMethods() default {};

}
