package com.m3.m3commons.s2.s2jdbc.unit;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;
import org.seasar.framework.util.tiger.GenericUtil;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/**
 * S2JDBCのServiceクラスの基底テストケースです。JUnit 3.xのみ対応です。<br>
 * <br>
 * このクラスを継承したクラスでは、メソッド名のpostfixを「Tx」にしなくても<br>
 * 全てのテストメソッドでトランザクションのロールバックを行います。<br>
 * <br>
 * テストクラスのジェネリクスに指定したServiceクラスの型のインスタンスをSeasar2から取得し、自動的に
 * serviceというフィールドに設定します。 <br>
 * <br>
 * <p/>
 * <pre>
 * public class SampleServiceTest extends S2JDBCServiceTestCase&lt;SampleService&gt; {
 * 	public void test_something() throws Exception {
 * 		Sample result = service.findById(1L);
 *     }
 * }
 * </pre>
 * <p/>
 * 自分自身でServiceクラスをインスタンス化してテストする場合は以下のようなメソッドを使用できます。<br>
 * Resourceアノテーション による自動DI、setter injectionによるDIは自動的に行われた状態のインスタンスが生成されます。
 * <p/>
 * <pre>
 * List&lt;SampleEntity&gt; actual = init(new SampleService()).findAll();
 * List&lt;SampleEntity&gt; actual = init(SampleService.class).findAll();
 * </pre>
 *
 * @param <SERVICE> テスト対象のServiceクラス型
 * @author k-sera
 */
public abstract class S2JDBCServiceTestCase<SERVICE> extends SimpleS2TestCase {

    public S2JDBCServiceTestCase() {
        this.setupServiceClass();
    }

    /**
     * 型パラメータで指定したクラスを取得してserviceClassを自動設定します。
     */
    protected final void setupServiceClass() {
        try {
            Map<TypeVariable<?>, Type> genericsMap = GenericUtil.getTypeVariableMap(getClass());
            Type type = getClass().getGenericSuperclass();
            Type[] arrays = GenericUtil.getGenericParameter(type);
            this.serviceClass = GenericUtil.getActualClass(arrays[0], genericsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * テスト対象のServiceクラスのインスタンス
     */
    protected SERVICE service;

    /**
     * テスト対象のServiceクラス型
     */
    protected Class<?> serviceClass;

    /**
     * S2のフィールドバインドが終わった後にServiceクラスをDIします。<br>
     * このタイミングである理由はsetUp()だとjdbcManagerがdialectを取得できないためです。
     */
    @Override
    protected void setUpAfterContainerInit() throws Throwable {
        if (serviceClass != null) {
            service = init(serviceClass);
        }
        // 以下からトランザクションを開始
        super.setUpAfterContainerInit();
    }

    /**
     * 引数で受け取った空インスタンスのクラスでS2からlookupしたインスタンスを返します。
     *
     * @param service
     * @return インスタンス
     */
    @SuppressWarnings("unchecked")
    public SERVICE init(SERVICE service) {
        return (SERVICE) s2.lookup(service.getClass());
    }

    /**
     * 引数で渡されたクラスでS2からlookupしたインスタンスを返します。
     *
     * @param serviceClass
     * @return インスタンス
     */
    @SuppressWarnings("unchecked")
    public SERVICE init(Class<?> serviceClass) {
        return (SERVICE) s2.lookup(serviceClass);
    }

}
