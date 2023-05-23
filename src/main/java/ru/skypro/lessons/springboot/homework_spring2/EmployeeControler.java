package ru.skypro.lessons.springboot.homework_spring2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeControler {

    private final EmployeeService employeeService;

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("salary/all")
    public List<Employee> showAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("salary/sum")
    public Integer showSum() {
        return employeeService.getSalarySum();
    }

    @GetMapping("salary/min")
    public Optional<Integer> showMin() {
        return employeeService.getMinSalary();
    }

    @GetMapping("salary/max")
    public Optional<Integer> showMax() {
        return employeeService.getMaxSalary();
    }

    @GetMapping("salary/higherThenAvg")
    public List<Integer> showHigherThenAvg() {
        return employeeService.getAllEmployeesWithSalaryHigherThenAvg();
    }
}
