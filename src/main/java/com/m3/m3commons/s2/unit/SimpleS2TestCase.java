package com.m3.m3commons.s2.unit;

import com.m3.m3commons.s2.s2jdbc.analyzer.QueryAnalyzer;
import com.m3.m3commons.s2.s2jdbc.analyzer.exception.QueryAnalyzerWarning;
import com.m3.m3commons.s2.util.ReflectionUtil;
import com.m3.m3commons.s2.util.S2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.mockito.Mockito;
import org.seasar.extension.jdbc.SqlLogRegistry;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.exception.SIllegalStateException;
import org.seasar.framework.exception.SNotSupportedException;

import javax.annotation.Resource;
import javax.transaction.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * {@link S2TestCase}の拡張<br>
 * <br>
 * ハマリどころやお決まりのところを設定したり、 S2TestCaseがサポートしていないものを追加してより簡単にテストできるようにするのが目的です。
 *
 * @author k-sera
 */
public abstract class SimpleS2TestCase extends S2TestCase {

    /**
     * デフォルトで{@link QueryAnalyzerWarning}を無視するかどうか
     */
    private Boolean isDefaultQueryAnalyzerWarningIgnorable;

    /**
     * ログ
     */
    protected Log log = LogFactory.getLog(this.getClass());

    /**
     * mvn test で実行中なら true
     */
    protected boolean isMavenTesting = (System.getProperty("localRepository") != null) ? true : false;

    /**
     * {@link QueryAnalyzerWarning}を無視する
     */
    protected void ignoreQueryAnalyzerWarning() {
        if (queryAnalyzer == null) {
            try {
                queryAnalyzer = s2.getComponent(QueryAnalyzer.class);
            } catch (Exception e) {
                log.debug("queryAnalyzerのコンポーネント取得に失敗しました。", e);
            }
        }
        if (queryAnalyzer != null) {
            queryAnalyzer.setLoggingOnly(true);
        }
    }

    /**
     * トランザクション
     */
    protected UserTransaction userTransaction;

    /**
     * クエリアナライザー
     */
    protected QueryAnalyzer queryAnalyzer;

    /**
     * 自動ロールバックの仕組みを使用するかどうか
     */
    protected boolean usingAutoRollback = true;

    /**
     * S2でDIできなかった場合に使用するMock Object Framework
     */
    protected MockType mockType = MockType.JMock2;

    /**
     * JMock2のモックコントローラ
     */
    protected Mockery jmock2;

    /**
     * 新しい{@link Mockery}オブジェクトを返します。
     *
     * @return Mockery
     */
    protected Mockery getNewMockey() {
        return new Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
            }
        };
    }

    /**
     * Seasar2へのエイリアス
     */
    protected S2 s2 = new S2();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("app.dicon");
        if (queryAnalyzer == null) {
            try {
                queryAnalyzer = s2.getComponent(QueryAnalyzer.class);
            } catch (Exception e) {
                log.debug("queryAnalyzerのコンポーネント取得に失敗しました。", e);
            }
        }
        if (queryAnalyzer != null) {
            if (isDefaultQueryAnalyzerWarningIgnorable == null) {
                isDefaultQueryAnalyzerWarningIgnorable = queryAnalyzer.isLoggingOnly();
            }
            queryAnalyzer.setLoggingOnly(isDefaultQueryAnalyzerWarningIgnorable);
        }
        jmock2 = getNewMockey();
    }

    /**
     * テストメソッド実行前にDBを準備します。
     */
    protected void prepareDbBeforeTestMethod() {
    }

    @Override
    protected void setUpAfterContainerInit() throws Throwable {
        super.setUpAfterContainerInit();
        autoBind(this);
        if (!this.getName().matches(".+Tx$")) {
            beginTransaction();
        }
        // ここである理由はuserTransactionを使っている場合にトランザクション開始後に実行させるため
        prepareDbBeforeTestMethod();
    }

    /**
     * {@link Resource}のついたフィールドに自動DIを試みます。<br>
     * 失敗した場合はmockに差し替えます。
     *
     * @param <T>       コンポーネントの型
     * @param component コンポーネント
     * @return コンポーネント
     */
    public <T> T autoBind(T component) {
        Class<?> clazz = component.getClass();
        while (true) {
            // @Resource injection
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field != null) {
                    field.setAccessible(true);
                }
                if (field.getAnnotation(Resource.class) != null) {
                    setValue2FieldFromS2(component, clazz, field);
                }
            }
            // Setter injection
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (Modifier.isPublic(method.getModifiers()) && ReflectionUtil.isAccessor(method)) {
                    Class<?>[] argTypes = method.getParameterTypes();
                    if (argTypes == null || argTypes.length <= 0) {
                        continue;
                    }
                    try {
                        Object value = s2.getComponent(argTypes[0]);
                        method.invoke(component, value);
                    } catch (Exception e) {
                    }
                }
            }
            clazz = clazz.getSuperclass();
            if (clazz.equals(Object.class)) {
                break;
            }
        }
        return component;
    }

    public void setValue2FieldFromS2(Object component, Class<?> clazz, Field field) {
        try {
            field.set(component, s2.lookup(field.getType()));
        } catch (Exception e) {
            log.info("Cannot inject to " + clazz.getSimpleName() + "." + field.getName()
                    + " because of " + e.getLocalizedMessage() + " (" + e.getClass().getCanonicalName() + ")");
            try {
                if (mockType.equals(MockType.JMock2)) {
                    field.set(component, jmock2.mock(field.getType()));
                    log.info("JMock2 context mocked " + clazz.getSimpleName() + "." + field.getName());
                }
                if (mockType.equals(MockType.Mockito)) {
                    field.set(component, Mockito.mock(field.getType()));
                    log.info("Mockito mocked " + clazz.getSimpleName() + "." + field.getName());
                }
            } catch (Exception e2) {
                if (mockType.equals(MockType.JMock2)) {
                    log.info("Cannot mock " + clazz.getSimpleName() + "." + field.getName() + " by JMock2 because of "
                            + e2.getLocalizedMessage() + " (" + e2.getClass().getCanonicalName() + ")");
                }
                if (mockType.equals(MockType.Mockito)) {
                    log.info("Cannot mock " + clazz.getSimpleName() + "." + field.getName() + " by Mockito because of "
                            + e2.getLocalizedMessage() + " (" + e2.getClass().getCanonicalName() + ")");
                }
            }
        }
    }

    public MockType getMockType() {
        return mockType;
    }

    public void setMockType(MockType mockType) {
        this.mockType = mockType;
    }

    @Override
    protected void tearDownBeforeContainerDestroy() throws Throwable {
        SqlLogRegistry sqlLogRegistry = SqlLogRegistryLocator.getInstance();
        try {
            super.tearDownBeforeContainerDestroy();
            int count = sqlLogRegistry.getSize();
            for (int i = 0; i < count; i++) {
                String sql = sqlLogRegistry.get(i).getCompleteSql();
                String logMessage = this.getName() + " [" + i + "] -> " + sql;
                if (isMavenTesting) {
                    log.debug(logMessage);
                } else {
                    log.info(logMessage);
                }
                if (queryAnalyzer != null) {
                    try {
                        queryAnalyzer.analyze(sql);
                    } catch (QueryAnalyzerWarning warn) {
                        throw warn;
                    } catch (Exception e) {
                        log.info(e);
                    }
                }
            }
        } finally {
            if (sqlLogRegistry != null) {
                sqlLogRegistry.clear();
            }
        }
    }

    /**
     * 事後処理
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (!this.getName().matches(".+Tx$")) {
            rollbackTransaction();
        }
        // 手動で取得した Seasar2 のコンポーネントへの参照を削除する
        // テストケースが GC されない限りクラスが解放されないため Permanent 領域の枯渇につながるため
        // なお、Seasar2 が DI するコンポーネントは自動的に解放されるためこの限りではない
        Class<?> clazz = this.getClass();
        while (!clazz.equals(S2TestCase.class) && !clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getType().isPrimitive()) {
                    field.setAccessible(true);
                    field.set(this, null);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * トランザクションを初期化します。
     */
    protected void initTransaction() {
        if (!usingAutoRollback) {
            return;
        }
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
        if (!usingAutoRollback) {
            return;
        }
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
     * @throws javax.transaction.SystemException
     * @throws SystemException
     */
    protected void rollbackTransaction() throws IllegalStateException, SecurityException, SystemException {
        if (!usingAutoRollback) {
            return;
        }
        if (userTransaction == null) {
            log.warn("トランザクションは開始されていません。");
        } else {
            try {
                userTransaction.rollback();
            } catch (SIllegalStateException e) {
                log.info("トランザクションをロールバックしようとしましたが、開始されていないか既に終了していました。");
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
        if (!usingAutoRollback) {
            return;
        }
        if (userTransaction == null) {
            log.warn("トランザクションは開始されていません。");
        } else {
            userTransaction.commit();
        }
    }

}
