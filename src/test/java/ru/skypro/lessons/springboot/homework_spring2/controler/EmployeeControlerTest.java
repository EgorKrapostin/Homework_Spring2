package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.model.Position;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.homework_spring2.repository.PositionRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    void getAllEmployeesTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        List<Employee> list = List.of(new Employee("name", 100, position),
                new Employee("name1", 100,  position),
                new Employee("name2", 100,  position));
        employeeRepository.saveAll(list);
        mockMvc.perform(get("/employee/salary/all"))
                .andExpect(status().isOk());

    }

    @Test
    void getSalarySumTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/salary/sum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getMinSalaryTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/salary/min"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getMaxSalaryTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/salary/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getSalaryHigherThanAvgTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        Employee employee = employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/salary/higherThenAvg"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        Employee employee = employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/getBy/" + employee.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getAllEmployeesWithMatchingPositionTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        Employee employee = employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/position/" + position.getName()))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeeFullInfoTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        Employee employee = employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/fullInfo/" + employee.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeesInPageFormatTest() throws Exception {
        Position position = new Position("dev");
        position = positionRepository.save(position);
        Employee employee = employeeRepository.save(new Employee("name", 100,position));
        mockMvc.perform(get("/employee/page"))
                .andExpect(status().isOk());
    }
}
