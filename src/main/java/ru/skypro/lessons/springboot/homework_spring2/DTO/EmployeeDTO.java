package ru.skypro.lessons.springboot.homework_spring2.DTO;

import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.model.Position;

import java.util.Objects;
import java.util.Optional;

public class EmployeeDTO {

    private String name;
    private int salary;
    private String position;

    public static EmployeeDTO fromEmployee(Employee employee) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(Optional.ofNullable(employee.getPosition())
                .map(Position::getName).
                orElse(null));

        return employeeDTO;
    }

    public Employee toEmployee() {

        Employee employee = new Employee();

        employee.setName(this.getName());
        employee.setSalary(this.getSalary());

        return employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
