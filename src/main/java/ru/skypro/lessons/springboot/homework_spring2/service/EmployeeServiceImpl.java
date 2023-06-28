package ru.skypro.lessons.springboot.homework_spring2.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.homework_spring2.controler.EmployeeControler;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        logger.debug("List of all employees");

        return employeeRepository.getAllEmployees().stream().
                map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getSalarySum() {

        logger.debug("Sum of salary");

        return employeeRepository.getSalarySum();
    }

    @Override
    public Optional<Integer> getMinSalary() {
        logger.debug("Employee with minimal salary");
        return employeeRepository.getMinSalary();
    }

    @Override
    public Optional<Integer> getMaxSalary() {
        logger.debug("Employee with max salary");
        return employeeRepository.getMaxSalary();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThenAvg() {

        logger.debug("Employees with salary higher then average");

        return employeeRepository.getAllEmployeesWithSalaryHigherThenAvg().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(Employee employee) {

         employeeRepository.save(employee);
         logger.debug("Employee added " + employee);

    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        logger.debug("Employee updated " + employee);
    }

    public List<EmployeeDTO> getEmployeeById(Integer id) {

        List<EmployeeDTO>
                optionalEmployeeDTO =
                employeeRepository.findById(id).stream()
                        .map(EmployeeDTO::fromEmployee)
                        .collect(Collectors.toList());
        logger.debug("Employee with id= "+id);

        return optionalEmployeeDTO;
    }

    @Override
    public void deleteEmployeeById(Integer id) {
         employeeRepository.deleteById(id);
        logger.debug("Employee with id= "+id+" deleted");
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithSalaryHigherThan(int salary) {
        logger.debug("List of employees wiht salary higher than "+salary);
        return employeeRepository.getAllEmployeesWithSalaryHigherThan(salary).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithMatchingPosition(String position) {
        logger.debug("List of employees wiht position "+position);
        return employeeRepository.getAllEmployeesWithMatchingPosition(position).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeFullInfo(int id) {
        logger.debug("Full information about employee with id= "+id);
        return employeeRepository.findById(id).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

    }

    @Override
    public List<EmployeeDTO> getEmployeesInPageFormat(int page) {
        return employeeRepository.findAll(PageRequest.of(page, 10)).stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public void saveEmployeeFromJson(MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<Employee> employees = objectMapper.readValue(file.getBytes(),new TypeReference<>(){});
        employeeRepository.saveAll(employees);
        logger.debug("Employees added");
    }
}
