package tutorial.action;

import com.m3.m3commons.s2.sastruts.unit.SAStrutsActionTestCase;

public class IndexActionTest extends SAStrutsActionTestCase<IndexAction> {

    public void testIndex() throws Exception {
        String result = action.index();
        assertEquals("index.jsp", result);
    }

}