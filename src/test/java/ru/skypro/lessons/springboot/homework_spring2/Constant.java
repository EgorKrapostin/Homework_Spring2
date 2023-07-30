package ru.skypro.lessons.springboot.homework_spring2;

import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;

import java.util.List;

public class Constant {
    public static final Employee employee = new Employee(1);
    public static final Employee employee1 = new Employee(2);
    public static final Employee employee2 = new Employee(3);
    public static final Employee employee3 = new Employee(4);

    public static final List<Employee> EMPLOYEE_LIST = List.of(
            employee,
            employee1,
            employee2,
            employee3
    );
}
