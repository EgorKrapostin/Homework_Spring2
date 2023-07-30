package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ReportControlerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void createReportTest() throws Exception {
        mockMvc.perform(post("/report/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getReportByIdTest() {

    }

    @Test
    void createReportPathTest() throws Exception {
        mockMvc.perform(post("/report/reportWithPath"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getReportPathByIdTest() {

    }
}
