package ru.skypro.lessons.springboot.homework_spring2.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;
import ru.skypro.lessons.springboot.homework_spring2.repository.EmployeeRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminEmployeeControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addEmployeeTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", "name");
        object.put("salary", 100000);
        object.put("positionId", 1);
        mockMvc.perform(post("/admin/employee/add").contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", "name1");
        object.put("salary", 100000);
        object.put("positionId", 1);
        mockMvc.perform(put("/admin/employee/update").contentType(MediaType.APPLICATION_JSON).content(object.toString()))
                .andExpect(status().isOk());
    }


    @Test
    void deleteEmployeeTest() throws Exception {
        mockMvc.perform(delete("/admin/employee/deleteBy{id}"))
                .andExpect(status().isOk());
    }

    @Test
    void uploadTest() throws Exception {
        List<Employee> list = List.of(new Employee(1, "name", 10000, 1));
        String json = objectMapper.writeValueAsString(list);
        MockMultipartFile file = new MockMultipartFile("rep", json.getBytes());
        mockMvc.perform(multipart("/admin/employee/upload")
                        .file(file))
                .andExpect(status().isOk());
    }
}
