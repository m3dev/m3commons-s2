package tutorial.action;

import com.m3.m3commons.s2.sastruts.annotation.PreExecute;
import com.m3.m3commons.s2.sastruts.annotation.PreExecuteMethod;
import org.seasar.framework.util.StringUtil;
import org.seasar.struts.annotation.Execute;
import tutorial.entity.Department;
import tutorial.entity.Employee;
import tutorial.service.DepartmentService;
import tutorial.service.EmployeeService;

import javax.annotation.Resource;
import java.util.List;

public class EmployeesAction {

    @Resource
    DepartmentService departmentService;
    @Resource
    EmployeeService employeeService;

    public List<Employee> employees;
    public String departmentName;
    public String employeeName;

    @PreExecuteMethod
    public void prepareWithParameters() throws Exception {
        if (StringUtil.isNotEmpty(departmentName) && StringUtil.isNotEmpty(employeeName)) {
            Department dept = new Department();
            dept.name = departmentName;
            departmentService.insert(dept);
            Employee employee = new Employee();
            employee.name = employeeName;
            employee.departmentId = dept.id;
            employeeService.insert(employee);
        }
    }

    @PreExecute
    @Execute(validator = false)
    public String index() {
        employees = employeeService.findAllWithDepartment();
        return "index.jsp";
    }

}
