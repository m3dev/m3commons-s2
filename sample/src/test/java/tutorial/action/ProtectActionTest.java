package tutorial.action;

import com.m3.m3commons.s2.sastruts.unit.SAStrutsActionTestCase;

public class ProtectActionTest extends SAStrutsActionTestCase<ProtectAction> {

    public void testMockingRequest() throws Exception {
        assertNotNull(action.request);
    }

    public void testIndex() throws Exception {
        String result = action.index();
        assertEquals(result, "index.jsp");
    }

    public void testTomcat() throws Exception {
        String result = action.tomcat();
        assertEquals(result, "tomcat.jsp");
    }

}