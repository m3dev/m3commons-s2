package tutorial.action;

import com.m3.m3commons.s2.sastruts.unit.SAStrutsActionTestCase;

public class AddActionTest extends SAStrutsActionTestCase<AddAction> {

    public void testIndex() throws Exception {
        String result = action.index();
        assertEquals(result, "index.jsp");
    }

    public void testSubmit() throws Exception {
        action.addForm.arg1 = "2";
        action.addForm.arg2 = "3";
        String result = action.submit();
        assertEquals(result, "index.jsp");
        assertEquals("5", action.result.toString());
    }

}