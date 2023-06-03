package ru.skypro.lessons.springboot.homework_spring2.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.homework_spring2.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Employee> employees = List.of(new Employee("Ivan", 50_000, 1),
            new Employee("Ivan1", 51_000, 2),
            new Employee("Ivan2", 52_000, 3),
            new Employee("Ivan3", 53_000, 4),
            new Employee("Ivan4", 54_000, 5),
            new Employee("Ivan5", 55_000, 6));

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Integer getSalarySum() {

        Integer list = employees.stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);

        return list;
    }

    @Override
    public Optional<Integer> getMinSalary() {

        Optional<Integer> list = employees.stream()
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder());

        return list;
    }

    @Override
    public Optional<Integer> getMaxSalary() {

        Optional<Integer> list = employees.stream()
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder());

        return list;
    }

    @Override
    public List<Employee> getAllEmployeesWithSalaryHigherThenAvg() {

        List<Employee> list = employees.stream()
                .filter(employee -> employee.getSalary() > getSalarySum() / employees.size())
                .toList();

        return list;
    }

    @Override
    public List<Employee> addEmployee(Employee employee) {

        List<Employee> list = new ArrayList<>(employees);
        list.add(employee);

        return list;
    }

    @Override
    public List<Employee> updateEmployee(Employee updEmployee, int id) {

        List<Employee> list = employees.stream()
                .filter(employee -> employee.getId() == id)
                .peek(employee -> employee.setId(updEmployee.getId()))
                .peek(employee -> employee.setName(updEmployee.getName()))
                .peek(employee -> employee.setSalary(updEmployee.getSalary()))
                .toList();

        return list;
    }

    @Override
    public List<Employee> getEmployeeById(int id) {

        List<Employee> list = employees.stream()
                .filter(employee -> employee.getId() == id)
                .toList();

        return list;
    }

    @Override
    public List<Employee> deleteEmployeeById(int id) {

        List<Employee> list = employees.stream()
                .filter(employee -> employee.getId() != id)
                .toList();

        return list;
    }

    @Override
    public List<Employee> getAllEmployeesWithSalaryHigherThan(int salary) {

        List<Employee> list = employees.stream()
                .filter(employee -> employee.getSalary() > salary)
                .toList();

        return list;
    }
}
