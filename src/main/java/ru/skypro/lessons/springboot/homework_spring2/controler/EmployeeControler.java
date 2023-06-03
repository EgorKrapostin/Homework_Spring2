package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.homework_spring2.Employee;
import ru.skypro.lessons.springboot.homework_spring2.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeControler {

    private final EmployeeService employeeService;

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/all")
    public List<Employee> showAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/salary/sum")
    public Integer showSum() {
        return employeeService.getSalarySum();
    }

    @GetMapping("/salary/min")
    public Optional<Integer> showMin() {
        return employeeService.getMinSalary();
    }

    @GetMapping("/salary/max")
    public Optional<Integer> showMax() {
        return employeeService.getMaxSalary();
    }

    @GetMapping("/salary/higherThenAvg")
    public List<Employee> showHigherThenAvg() {
        return employeeService.getAllEmployeesWithSalaryHigherThenAvg();
    }

    @PostMapping("/add")
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public List<Employee> updateEmployee(@RequestBody Employee employee,
                                         @PathVariable int id) {
        return employeeService.updateEmployee(employee, id);
    }

    @GetMapping("/getBy{id}")
    public List<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteBy{id}")
    public List<Employee> deleteEmployeeById(@PathVariable int id) {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salary/higher")
    public List<Employee> getAllEmployeesWithSalaryHigherThan(@RequestParam("salary") int salary) {
        return employeeService.getAllEmployeesWithSalaryHigherThan(salary);
    }
}
