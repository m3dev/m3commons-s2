package tutorial.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    public Long id;

    @Column(length = 20, nullable = true, unique = false)
    public String name;

    @OneToMany(mappedBy = "department")
    public List<Employee> employeeList;

}
