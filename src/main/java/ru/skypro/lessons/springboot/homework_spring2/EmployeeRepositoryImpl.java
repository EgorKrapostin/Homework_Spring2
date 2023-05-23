package ru.skypro.lessons.springboot.homework_spring2;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> employees = List.of(new Employee("Ivan", 50_000),
            new Employee("Ivan1", 51_000),
            new Employee("Ivan2", 52_000),
            new Employee("Ivan3", 53_000),
            new Employee("Ivan4", 54_000),
            new Employee("Ivan5", 55_000));

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
    public List<Integer> getAllEmployeesWithSalaryHigherThenAvg() {

        List<Integer> list = employees.stream()
                .map(Employee::getSalary)
                .filter(integer -> integer > getSalarySum() / employees.size())
                .toList();
        return list;
    }
}
