package ru.skypro.lessons.springboot.homework_spring2.service;

import ru.skypro.lessons.springboot.homework_spring2.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Integer getSalarySum();

    Optional<Integer> getMinSalary();

    Optional<Integer> getMaxSalary();

    List<Employee> getAllEmployeesWithSalaryHigherThenAvg();

    List<Employee> addEmployee(Employee employee);

    List<Employee> updateEmployee(Employee employee, int id);

    List<Employee> getEmployeeById(int id);

    List<Employee> deleteEmployeeById(int id);

    List<Employee> getAllEmployeesWithSalaryHigherThan(int salary);
}
