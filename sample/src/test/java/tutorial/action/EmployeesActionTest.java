package tutorial.action;

import com.m3.m3commons.s2.sastruts.unit.SAStrutsActionTestCase;
import tutorial.service.EmployeeService;

import javax.annotation.Resource;

public class EmployeesActionTest extends SAStrutsActionTestCase<EmployeesAction> {

    @Resource
    EmployeeService service;

    public void testIndex() throws Exception {
        String result = action.index();
        assertEquals("index.jsp", result);
    }

    public void testIndex_withCreation() throws Exception {
        long beforeCount = service.countAll();
        action.departmentName = "Engineering Group";
        action.employeeName = "Brian Hooper";
        action.prepareWithParameters();

        String result = action.index();
        assertEquals("index.jsp", result);
        assertEquals(beforeCount + 1, action.employees.size());
    }

}