package ru.skypro.lessons.springboot.homework_spring2.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.homework_spring2.Employee;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Integer getSalarySum() {
        return employeeRepository.getSalarySum();
    }

    @Override
    public Optional<Integer> getMinSalary() {
        return employeeRepository.getMinSalary();
    }

    @Override
    public Optional<Integer> getMaxSalary() {
        return employeeRepository.getMaxSalary();
    }

    @Override
    public List<Employee> getAllEmployeesWithSalaryHigherThenAvg() {
        return employeeRepository.getAllEmployeesWithSalaryHigherThenAvg();
    }

    @Override
    public List<Employee> addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);

    }

    @Override
    public List<Employee> updateEmployee(Employee employee, int id) {
        return employeeRepository.updateEmployee(employee,id);
    }

    @Override
    public List<Employee> getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> deleteEmployeeById(int id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployeesWithSalaryHigherThan(int salary) {
        return employeeRepository.getAllEmployeesWithSalaryHigherThan(salary);
    }

}
