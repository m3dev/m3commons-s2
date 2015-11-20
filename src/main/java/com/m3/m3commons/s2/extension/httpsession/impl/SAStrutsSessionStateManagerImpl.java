package com.m3.m3commons.s2.extension.httpsession.impl;

import org.seasar.extension.httpsession.SessionState;
import org.seasar.extension.httpsession.SessionStateManager;
import org.seasar.extension.jdbc.impl.BasicBatchHandler;
import org.seasar.extension.jdbc.impl.BasicSelectHandler;
import org.seasar.extension.jdbc.impl.BasicUpdateHandler;
import org.seasar.extension.jdbc.impl.MapListResultSetHandler;
import org.seasar.framework.container.hotdeploy.HotdeployHttpSession.SerializedObjectHolder;
import org.seasar.framework.util.SerializeUtil;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

/**
 * HOTDeploy時にNotSerializableExceptionが発生するため 該当箇所の処理を修正した拡張
 * <p/>
 * <pre>
 * com.m3.xxx.firm.extension.httpsession.impl.SAStrutsSessionStateManagerImpl
 * org.seasar.extension.httpsession.SessionStateManager
 *
 * 致命的: サーブレット default のServlet.service()が例外を投げました
 * org.seasar.framework.exception.IORuntimeException: [ESSR0040]IO例外が発生しました。理由はjava.io.NotSerializableException: org.seasar.framework.container.hotdeploy.HotdeployHttpSession$SerializedObjectHolder
 * 	at org.seasar.framework.util.SerializeUtil.fromObjectToBinary(SerializeUtil.java:77)
 * 	....
 * 	at com.m3.xxx.firm.extension.httpsession.impl.SAStrutsSessionStateManagerImpl.updateState(SAStrutsSessionStateManagerImpl.java:40)
 * 	at org.seasar.extension.httpsession.SessionFilter.doFilter(SessionFilter.java:66)
 * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235)
 * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
 * 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)
 * 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:175)
 * 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)
 * 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)
 * 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)
 * 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:263)
 * 	at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:844)
 * 	at org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:584)
 * 	at org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:447)
 * 	at java.lang.Thread.run(Thread.java:619)
 * Caused by: java.io.NotSerializableException: org.seasar.framework.container.hotdeploy.HotdeployHttpSession$SerializedObjectHolder
 * 	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1156)
 * 	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:326)
 * 	at org.seasar.framework.util.SerializeUtil.fromObjectToBinary(SerializeUtil.java:71)
 * 	... 14 more
 * </pre>
 *
 * @author k-sera
 */
public class SAStrutsSessionStateManagerImpl implements SessionStateManager {

    /**
     * {@link SAStrutsSessionStateManagerImpl}を作成します。
     */
    public SAStrutsSessionStateManagerImpl() {
    }

    /**
     * {@link SAStrutsSessionStateManagerImpl}を作成します。
     *
     * @param dataSource データソース
     */
    public SAStrutsSessionStateManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String INSERT_SQL = "insert into s2session values(?, ?, ?, ?)";
    private static final String UPDATE_SQL = "update s2session set value = ?, last_access = ? where session_id = ? and name = ?";
    private static final String DELETE_SQL = "delete from s2session where session_id = ? and name = ?";
    private static final String SELECT_SQL = "select name, value from s2session where session_id = ?";
    private static final String DELETE_ALL_SQL = "delete from s2session where session_id = ?";

    private DataSource dataSource;
    private boolean batchUpdateDisabled;

    @Override
    public void updateState(String sessionId, SessionState sessionState) {
        List<Object[]> insertedData = new ArrayList<Object[]>();
        List<Object[]> updatedData = new ArrayList<Object[]>();
        List<Object[]> deletedData = new ArrayList<Object[]>();
        Timestamp lastAccess = new Timestamp(new Date().getTime());
        Enumeration<?> e = sessionState.getAccessedAttributeNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            if (sessionState.needInsert(name)) {
                Object obj = sessionState.getAttribute(name);
                if (obj instanceof SerializedObjectHolder) {
                    SerializedObjectHolder holder = (SerializedObjectHolder) obj;
                    obj = holder.getDeserializedObject(name);
                }
                byte[] value = SerializeUtil.fromObjectToBinary(obj);
                insertedData.add(new Object[]{sessionId, name, value, lastAccess});
            } else if (sessionState.needUpdate(name)) {
                Object obj = sessionState.getAttribute(name);
                if (obj instanceof SerializedObjectHolder) {
                    SerializedObjectHolder holder = (SerializedObjectHolder) obj;
                    obj = holder.getDeserializedObject(name);
                }
                byte[] value = SerializeUtil.fromObjectToBinary(obj);
                updatedData.add(new Object[]{value, lastAccess, sessionId, name});
            } else if (sessionState.needDelete(name)) {
                deletedData.add(new Object[]{sessionId, name});
            }
        }
        execute(INSERT_SQL, insertedData);
        execute(UPDATE_SQL, updatedData);
        execute(DELETE_SQL, deletedData);
        sessionState.persisted();
    }

    /**
     * データソースを設定します。
     *
     * @param dataSource データソース
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * バッチ更新を無効にする場合<code>true</code>を設定します。
     *
     * @param batchUpdateDisabled バッチ更新を無効にする場合<code>true</code>
     */
    public void setBatchUpdateDisabled(boolean batchUpdateDisabled) {
        this.batchUpdateDisabled = batchUpdateDisabled;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public SessionState loadState(String sessionId) {
        BasicSelectHandler handler = new BasicSelectHandler(dataSource, SELECT_SQL, new MapListResultSetHandler());
        List<?> result = (List<?>) handler.execute(new String[]{sessionId});
        Map binaryData = new HashMap(result.size());
        for (int i = 0; i < result.size(); i++) {
            Map m = (Map) result.get(i);
            binaryData.put(m.get("name"), m.get("value"));
        }
        return new SessionState(binaryData);
    }

    public void removeState(String sessionId) {
        BasicUpdateHandler handler = new BasicUpdateHandler(dataSource, DELETE_ALL_SQL);
        handler.execute(new Object[]{sessionId});
    }

    /**
     * データを更新します。
     *
     * @param sql  SQL
     * @param data データ
     */
    @SuppressWarnings({"rawtypes"})
    protected void execute(String sql, List data) {
        if (data.size() == 0) {
            return;
        }
        if (batchUpdateDisabled) {
            executeUpdate(sql, data);
        } else {
            executeBatch(sql, data);
        }
    }

    /**
     * バッチ更新を行ないます。
     *
     * @param sql  SQL
     * @param data データ
     */
    @SuppressWarnings({"rawtypes"})
    protected void executeBatch(String sql, List data) {
        BasicBatchHandler handler = new BasicBatchHandler(dataSource, sql);
        handler.execute(data);
    }

    /**
     * 1行ずつ更新処理を行ないます。
     *
     * @param sql  SQL
     * @param data データ
     */
    @SuppressWarnings({"rawtypes"})
    protected void executeUpdate(String sql, List data) {
        BasicUpdateHandler handler = new BasicUpdateHandler(dataSource, sql);
        for (Iterator i = data.iterator(); i.hasNext(); ) {
            handler.execute((Object[]) i.next());
        }
    }
}
