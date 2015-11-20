package tutorial.service;

import com.m3.m3commons.s2.s2jdbc.unit.S2JDBCServiceTestCase;
import tutorial.entity.Department;
import tutorial.entity.Employee;

import javax.annotation.Resource;

public class EmployeeServiceTest extends S2JDBCServiceTestCase<EmployeeService> {

    @Resource
    DepartmentService departmentService;

    public void testExample() throws Exception {
        long beforeCount = service.countAll();

        Department dept = new Department();
        dept.name = "Engineering Group";
        departmentService.insert(dept);

        Employee alice = new Employee();
        alice.name = "Alice";
        alice.departmentId = dept.id;
        service.insert(alice);

        assertEquals(beforeCount + 1, service.findAll().size());
        assertNotNull(service.findAllWithDepartment().get(0).department);
        assertNotNull(service.findById(alice.id));

        service.deleteIfExist(alice);
        assertEquals(beforeCount, service.countAll());
    }

}