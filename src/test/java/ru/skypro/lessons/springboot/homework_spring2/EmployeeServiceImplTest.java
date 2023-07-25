package ru.skypro.lessons.springboot.homework_spring2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.homework_spring2.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.model.Position;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.homework_spring2.service.EmployeeServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static ru.skypro.lessons.springboot.homework_spring2.Constant.EMPLOYEE_LIST;
import static ru.skypro.lessons.springboot.homework_spring2.Constant.employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository mockRepository;

    @InjectMocks
    private EmployeeServiceImpl out;

    private static final Page<Employee> employeeInPageFormat  = new PageImpl<>(EMPLOYEE_LIST);

    @Test
    public void shouldReturnCollectionOfAllEmployees() {

        when(mockRepository.getAllEmployees()).thenReturn(EMPLOYEE_LIST);
        assertIterableEquals(EMPLOYEE_LIST,mockRepository.getAllEmployees());
    }


    @Test
    public void shouldReturnSalarySum() {

        Integer testSalary = EMPLOYEE_LIST.stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);

        assertEquals(testSalary,mockRepository.getSalarySum());
    }


    @Test
    public void shouldReturnMinSalary() {
        Optional<Integer> testMinSalary = EMPLOYEE_LIST.stream()
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder());

        assertTrue(testMinSalary.isPresent());
    }

    @Test
    public void shouldReturnMaxSalary() {
        Optional<Integer> testMaxSalary = EMPLOYEE_LIST.stream()
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder());

        assertTrue(testMaxSalary.isPresent());
    }

    @Test
    public void shouldReturnEmployeesWithSalaryHigherThenAvg() {
        List<Employee> list = EMPLOYEE_LIST.stream()
                .filter(employee -> employee.getSalary() > mockRepository.getSalarySum() / EMPLOYEE_LIST.size())
                .toList();

        assertIterableEquals(list,mockRepository.getAllEmployeesWithSalaryHigherThenAvg());
    }

    @Test
    public void shoudAddEmployee() {
        Employee empl = new Employee();
        out.addEmployee(empl);
        verify(mockRepository, times(1)).save(empl);
    }

    @Test
    public void shouldUpdateEmployee() {
        Employee empl = new Employee();
        empl.setName("Name");
        out.updateEmployee(empl);
        verify(mockRepository, times(1)).save(empl);
    }

    @Test
    public void shouldDeleteEmployee() {
        out.deleteEmployeeById(1);
        verify(mockRepository, times(1)).deleteById(1);
    }

    @Test
    public void shouldReturnAllEmployeesWithSalaryHigherThan() {
        Integer salary = 100000;

        List<Employee> list = EMPLOYEE_LIST.stream()
                .filter(employee -> employee.getSalary() > salary)
                .toList();

        assertIterableEquals(list,mockRepository.getAllEmployeesWithSalaryHigherThan(salary));
    }

    @Test
    public void shouldReturnAllEmployeesWithMatchingPosition() {
        Integer position = 1;

        List<Employee> list = EMPLOYEE_LIST.stream()
                .filter(employee -> employee.getPositionId() == position)
                .toList();

        assertIterableEquals(list,mockRepository.getAllEmployeesWithMatchingPosition(position.toString()));
    }

    @Test
    public void shouldReturnEmployeesInPageFormat() {

        when(mockRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(employeeInPageFormat);


        assertEquals(4,out.getEmployeesInPageFormat(0).size());
    }

    @Test
    public void shouldReturnEmployeeFullInfo() {

        Optional<EmployeeDTO> empl = EMPLOYEE_LIST.stream().map(EmployeeDTO::fromEmployee).findFirst();

        when(mockRepository.findById(1)).thenReturn(Optional.of(employee));
        assertEquals(empl,out.getEmployeeFullInfo(1));
    }

    @Test
    public void shouldSaveEmployeeFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(EMPLOYEE_LIST);
        MockMultipartFile file = new MockMultipartFile("employee", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());
        out.saveEmployeeFromJson(file);
    }

}
