package com.m3.m3commons.s2.sastruts.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Actionクラスの@Executeメソッド後をフックするメソッド。<br>
 * 引数なしのメソッドしか実行できません。<br>
 *
 * @author k-sera
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PostExecuteMethod {

    /**
     * 特にexcludeされない場合はデフォルトで実行されます。
     *
     * @return デフォルト実行するかどうか
     */
    boolean autoExecute() default true;

}
