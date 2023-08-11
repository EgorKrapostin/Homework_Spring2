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

    @Test
    void getAllEmployeesTest() throws Exception {
        mockMvc.perform(get("/employee/salary/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

    }

    @Test
    void getSalarySumTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("salary", 100000);
        mockMvc.perform(get("/employee/salary/sum"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").isNumber());
    }

    @Test
    void getMinSalaryTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("salary", 100000);
        mockMvc.perform(get("/employee/salary/min"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").isNumber());
    }

    @Test
    void getMaxSalaryTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("salary", 100000);
        mockMvc.perform(get("/employee/salary/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").isNumber());
    }

    @Test
    void getSalaryHigherThanAvgTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("salary", 100000);
        mockMvc.perform(get("/employee/salary/higherThenAvg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").isNumber());
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        Integer ID = 5;
        mockMvc.perform(get("/employee/salary/getBy/id" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getAllEmployeesWithMatchingPositionTest() throws Exception {
        mockMvc.perform(get("/employee/position"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getEmployeeFullInfoTest() throws Exception {
        Integer ID = 5;
        mockMvc.perform(get("/employee/fullInfo/id" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getEmployeesInPageFormatTest() throws Exception {
        mockMvc.perform(get("/employee/page"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
