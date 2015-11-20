package tutorial.entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    public Long id;

    @Column(length = 20, nullable = true, unique = false)
    public String name;

    @Column(nullable = true, unique = false)
    public Long departmentId;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID")
    public Department department;

}