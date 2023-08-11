package ru.skypro.lessons.springboot.homework_spring2.controler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ReportControlerTest {

    @Autowired
    MockMvc mockMvc;

    MvcResult result;

    @Test
    void createReportTest() throws Exception {
        mockMvc.perform(post("/report/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getReportByIdTest() {
        byte[] resourceContent = result.getResponse().getContentAsByteArray();
        assertThat(resourceContent).isNotEmpty();
    }

    @Test
    void createReportPathTest() throws Exception {
        mockMvc.perform(post("/report/reportWithPath"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void getReportPathByIdTest() {
        byte[] resourceContent = result.getResponse().getContentAsByteArray();
        assertThat(resourceContent).isNotEmpty();
    }
}
