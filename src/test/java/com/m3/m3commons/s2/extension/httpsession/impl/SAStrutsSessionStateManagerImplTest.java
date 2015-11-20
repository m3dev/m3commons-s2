package com.m3.m3commons.s2.extension.httpsession.impl;

import com.m3.m3commons.s2.unit.SimpleS2TestCase;

/**
 * DbSessionStateManagerImplのテストを丸コピーしてデグレ確認
 *
 * @author k-sera
 */
public class SAStrutsSessionStateManagerImplTest extends SimpleS2TestCase {

    public void test_instantiation() throws Exception {
        SAStrutsSessionStateManagerImpl target = new SAStrutsSessionStateManagerImpl();
        assertNotNull(target);
    }

    // /**
    // * @throws Exception
    // */
    // public void testLoadState() throws Exception {
    // SessionStateManager manager = new SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // SessionState sessionState = manager.loadState("hoge");
    // assertNotNull(sessionState);
    // }
    //
    // /**
    // * @throws Exception
    // */
    // @SuppressWarnings("rawtypes")
    // public void testUpdateState_insertTx() throws Exception {
    // SAStrutsSessionStateManagerImpl manager = new
    // SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // SessionState sessionState = new SessionState(new HashMap());
    // sessionState.setAttribute("aaa", new Integer(1));
    // sessionState.setAttribute("bbb", new Integer(2));
    // manager.updateState("hoge", sessionState);
    // SessionState sessionState2 = manager.loadState("hoge");
    // assertEquals(new Integer(1), sessionState2.getAttribute("aaa"));
    // assertEquals(new Integer(2), sessionState2.getAttribute("bbb"));
    // }
    //
    // /**
    // * @throws Exception
    // */
    // @SuppressWarnings("rawtypes")
    // public void testUpdateState_updateTx() throws Exception {
    // SAStrutsSessionStateManagerImpl manager = new
    // SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // SessionState sessionState = new SessionState(new HashMap());
    // sessionState.setAttribute("aaa", new Integer(1));
    // sessionState.setAttribute("bbb", new Integer(2));
    // manager.updateState("hoge", sessionState);
    // SessionState sessionState2 = manager.loadState("hoge");
    // sessionState2.setAttribute("aaa", new Integer(3));
    // sessionState2.setAttribute("bbb", new Integer(4));
    // manager.updateState("hoge", sessionState2);
    // SessionState sessionState3 = manager.loadState("hoge");
    // assertEquals(new Integer(3), sessionState3.getAttribute("aaa"));
    // assertEquals(new Integer(4), sessionState3.getAttribute("bbb"));
    // }
    //
    // /**
    // * @throws Exception
    // */
    // @SuppressWarnings("rawtypes")
    // public void testUpdateState_deleteTx() throws Exception {
    // SAStrutsSessionStateManagerImpl manager = new
    // SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // SessionState sessionState = new SessionState(new HashMap());
    // sessionState.setAttribute("aaa", new Integer(1));
    // sessionState.setAttribute("bbb", new Integer(2));
    // manager.updateState("hoge", sessionState);
    // SessionState sessionState2 = manager.loadState("hoge");
    // sessionState2.setAttribute("aaa", null);
    // sessionState2.setAttribute("bbb", null);
    // manager.updateState("hoge", sessionState2);
    // SessionState sessionState3 = manager.loadState("hoge");
    // assertNull(sessionState3.getAttribute("aaa"));
    // assertNull(sessionState3.getAttribute("bbb"));
    // }
    //
    // /**
    // * @throws Exception
    // */
    // @SuppressWarnings("rawtypes")
    // public void testRemoveStateTx() throws Exception {
    // SAStrutsSessionStateManagerImpl manager = new
    // SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // SessionState sessionState = new SessionState(new HashMap());
    // sessionState.setAttribute("aaa", new Integer(1));
    // sessionState.setAttribute("bbb", new Integer(2));
    // manager.updateState("hoge", sessionState);
    // manager.removeState("hoge");
    // SessionState sessionState2 = manager.loadState("hoge");
    // assertNull(sessionState2.getAttribute("aaa"));
    // assertNull(sessionState2.getAttribute("bbb"));
    // }
    //
    // /**
    // *
    // * @throws Exception
    // */
    // @SuppressWarnings("rawtypes")
    // public void testUpdateStateBatchDisabled_updateTx() throws Exception {
    // SAStrutsSessionStateManagerImpl manager = new
    // SAStrutsSessionStateManagerImpl(
    // getDataSource());
    // manager.setBatchUpdateDisabled(true);
    // SessionState sessionState = new SessionState(new HashMap());
    // sessionState.setAttribute("aaa", new Integer(1));
    // sessionState.setAttribute("bbb", new Integer(2));
    // manager.updateState("hoge", sessionState);
    // SessionState sessionState2 = manager.loadState("hoge");
    // sessionState2.setAttribute("aaa", new Integer(3));
    // sessionState2.setAttribute("bbb", new Integer(4));
    // manager.updateState("hoge", sessionState2);
    // SessionState sessionState3 = manager.loadState("hoge");
    // assertEquals(new Integer(3), sessionState3.getAttribute("aaa"));
    // assertEquals(new Integer(4), sessionState3.getAttribute("bbb"));
    // }

}
