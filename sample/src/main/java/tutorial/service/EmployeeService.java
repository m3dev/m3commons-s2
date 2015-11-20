package tutorial.service;

import com.m3.m3commons.s2.s2jdbc.service.AbstractService;
import tutorial.entity.Employee;

import java.util.List;

public class EmployeeService extends AbstractService<Employee> {

    public List<Employee> findAllWithDepartment() {
        return select().innerJoin("department").getResultList();
    }

}
