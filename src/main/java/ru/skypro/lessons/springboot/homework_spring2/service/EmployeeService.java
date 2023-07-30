package ru.skypro.lessons.springboot.homework_spring2.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    Integer getSalarySum();

    Optional<Integer> getMinSalary();

    Optional<Integer> getMaxSalary();

    List<EmployeeDTO> getAllEmployeesWithSalaryHigherThenAvg();

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    List<EmployeeDTO>  getEmployeeById(Integer id);

    void deleteEmployeeById(Integer id);

    List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(int salary);

    List<EmployeeDTO> getAllEmployeesWithMatchingPosition(String position);

    Optional<EmployeeDTO> getEmployeeFullInfo(int id);

    List<EmployeeDTO> getEmployeesInPageFormat(int page);

    void saveEmployeeFromJson(MultipartFile file) throws IOException;
}
