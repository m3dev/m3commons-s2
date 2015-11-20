package com.m3.m3commons.s2.sastruts.unit;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;
import org.seasar.framework.exception.SIllegalStateException;
import org.seasar.framework.exception.SNotSupportedException;
import org.seasar.framework.util.tiger.GenericUtil;

import javax.annotation.Resource;
import javax.transaction.*;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/**
 * SAStrutsのActionクラスの基底テストケースです。JUnit 3.xのみ対応です。<br>
 * <br>
 * テストクラスのジェネリクスに指定したActionクラスの型のインスタンスをSeasar2から取得し、自動的に actionというフィールドに設定します。
 * <p/>
 * <pre>
 * public class SampleActionTest extends SAStrutsActionTestCase&lt;SampleAction&gt; {
 *   public void test_doSomething() throws Exception {
 * 	   action.doSomething();
 *   }
 * }
 * </pre>
 * <p/>
 * 自分自身でActionクラスをインスタンス化してテストする場合は以下のようなメソッドを使用できます。<br>
 * Resourceアノテーション による自動DI、setter injectionによるDIは自動的に行われた状態のインスタンスが生成されます。
 * <p/>
 * <pre>
 * SampleAction action = init(new SampleAction());<br>
 * String expected = "index.jsp";<br>
 * String actual = action.index();<br>
 * assertEquals(expected, actual);<br>
 * </pre>
 *
 * @param <ACTION> テスト対象のActionクラス型
 * @author k-sera
 */
public abstract class SAStrutsActionTestCase<ACTION> extends SimpleS2TestCase {

    public SAStrutsActionTestCase() {
        setupActionClass();
    }

    /**
     * 型パラメータで指定したクラスを取得してactionClassを自動設定します。
     */
    protected final void setupActionClass() {
        try {
            Map<TypeVariable<?>, Type> genericsMap = GenericUtil.getTypeVariableMap(getClass());
            Type type = getClass().getGenericSuperclass();
            Type[] arrays = GenericUtil.getGenericParameter(type);
            this.actionClass = GenericUtil.getActualClass(arrays[0], genericsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * テスト対象のActionクラスのインスタンス
     */
    protected ACTION action;

    /**
     * テスト対象のActionクラス型
     */
    protected Class<?> actionClass;

    /**
     * トランザクション
     */
    protected UserTransaction userTransaction;

    /**
     * S2のフィールドバインドが終わった後にServiceクラスをDIします。<br>
     * このタイミングである理由はsetUp()だとjdbcManagerがdialectを取得できないためです。
     */
    @Override
    protected void setUpAfterBindFields() throws Throwable {
        super.setUpAfterBindFields();
        if (actionClass != null) {
            action = init(actionClass);
        }
    }

    /**
     * Actionクラスのインスタンスを生成します。 <br>
     * <br> {@link Resource} アノテーションのついたフィールドにはSeasar2からの自動DIを試みます。<br>
     * 失敗した場合はmockTypeで指定されたmock frameworkを使ってmock objectを設定します。<br>
     *
     * @param action
     * @return 引数で受け取ったインスタンス
     */
    protected ACTION init(ACTION action) {
        return autoInjection(action);
    }

    /**
     * Actionクラスのインスタンスを生成します。 <br>
     * <br> {@link Resource} アノテーションのついたフィールドにはSeasar2からの自動DIを試みます。<br>
     * 失敗した場合はmockTypeで指定されたmock frameworkを使ってmock objectを設定します。<br>
     *
     * @param clazz
     * @return インスタンス
     */
    @SuppressWarnings("unchecked")
    protected ACTION init(Class<?> clazz) {
        try {
            return autoInjection((ACTION) actionClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 渡されたActionクラスのインスタンスに対してSeasar2からの自動DIを試みます。<br>
     * 失敗した場合はmockTypeで指定されたmock frameworkを使ってmock objectを設定します。<br>
     * <br>
     * Actionクラスのインスタンスを戻り値で返していますが、<br>
     * deepCopyではなく引数のオブジェクトを直接いじっている破壊的なメソッドです。<br>
     *
     * @param action
     * @return 引数で受け取ったインスタンス
     */
    protected ACTION autoInjection(ACTION action) {
        return autoBind(action);
    }

    /**
     * トランザクションを初期化します。
     */
    protected void initTransaction() {
        if (userTransaction == null) {
            userTransaction = s2.lookup(UserTransaction.class);
        }
    }

    /**
     * トランザクションを開始します。
     *
     * @throws NotSupportedException
     * @throws SystemException
     */
    protected void beginTransaction() throws NotSupportedException, SystemException {
        initTransaction();
        try {
            userTransaction.begin();
        } catch (SNotSupportedException e) {
            log.warn("トランザクションは既に開始されています。");
        }
    }

    /**
     * トランザクションをロールバックします。
     *
     * @throws IllegalStateException
     * @throws SecurityException
     * @throws SystemException
     */
    protected void rollbackTransaction() throws IllegalStateException, SecurityException, SystemException {
        if (userTransaction == null) {
            log.warn("トランザクションは開始されていません。");
        } else {
            try {
                userTransaction.rollback();
            } catch (SIllegalStateException e) {
                log.warn("トランザクションは開始されていません。");
            }
        }
    }

    /**
     * トランザクションをコミットします。
     *
     * @throws IllegalStateException
     * @throws SecurityException
     * @throws HeuristicMixedException
     * @throws HeuristicRollbackException
     * @throws RollbackException
     * @throws SystemException
     */
    protected void commitTransaction() throws IllegalStateException, SecurityException, HeuristicMixedException,
            HeuristicRollbackException, RollbackException, SystemException {
        if (userTransaction == null) {
            log.warn("トランザクションは開始されていません。");
        } else {
            userTransaction.commit();
        }
    }

}
