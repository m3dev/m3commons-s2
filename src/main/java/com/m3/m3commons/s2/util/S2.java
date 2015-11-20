package com.m3.m3commons.s2.util;

import org.seasar.framework.container.SingletonS2Container;

/**
 * Seasar2コンテナへのエイリアス<br>
 * <br>
 * http://s2container.seasar.org/<br>
 *
 * @author k-dewa
 */
public class S2 {

    /**
     * デフォルトコンストラクタ
     */
    public S2() {
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentClass
     * @return S2コンポーネント
     */
    public <T> T lookup(Class<T> componentClass) {
        return S2._lookup(componentClass);
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentClass
     * @return S2コンポーネント
     */
    public <T> T getComponent(Class<T> componentClass) {
        return lookup(componentClass);
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentClass
     * @return S2コンポーネント
     */
    private static <T> T _lookup(Class<T> componentClass) {
        return (T) SingletonS2Container.getComponent(componentClass);
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentName
     * @return S2コンポーネント
     */
    @SuppressWarnings("unchecked")
    public <T> T lookup(String componentName) {
        return (T) S2._lookup(componentName);
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentName
     * @return S2コンポーネント
     */
    @SuppressWarnings("unchecked")
    public <T> T getComponent(String componentName) {
        return (T) lookup(componentName);
    }

    /**
     * S2コンテナのコンポーネントをルックアップします。
     *
     * @param <T>
     * @param componentName
     * @return S2コンポーネント
     */
    @SuppressWarnings("unchecked")
    private static <T> T _lookup(String componentName) {
        return (T) SingletonS2Container.getComponent(componentName);
    }

}
