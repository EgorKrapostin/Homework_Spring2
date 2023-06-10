package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;
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
    public List<EmployeeDTO> showAll() {
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

    @GetMapping("/salary/withHighestSalary")
    public Optional<Integer> showMax() {
        return employeeService.getMaxSalary();
    }

    @GetMapping("/salary/higherThenAvg")
    public List<EmployeeDTO> showHigherThenAvg() {
        return employeeService.getAllEmployeesWithSalaryHigherThenAvg();
    }

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee) {
         employeeService.addEmployee(employee);
    }

    @PutMapping("/update")
    public void updateEmployee(@RequestBody Employee employee) {
         employeeService.updateEmployee(employee);
    }

    @GetMapping("/getBy{id}")
    public List<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteBy{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
         employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/salary/higher")
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(@RequestParam("salary") int salary) {
        return employeeService.getAllEmployeesWithSalaryHigherThan(salary);
    }

    @GetMapping("/position")
    public List<EmployeeDTO> getAllEmployeesWithMatchingPosition(@RequestParam("position") String position) {

        return employeeService.getAllEmployeesWithMatchingPosition(position);
    }

    @GetMapping("/fullInfo{id}")
    public List<EmployeeDTO> getEmployeeFullInfo(@PathVariable int id) {

        return employeeService.getEmployeeFullInfo(id);
    }

    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeesInPageFormat(@RequestParam(required = false,defaultValue = "0") int page) {

        return employeeService.getEmployeesInPageFormat(page);
    }
}
