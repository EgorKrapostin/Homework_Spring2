package ru.skypro.lessons.springboot.homework_spring2.service;

import ru.skypro.lessons.springboot.homework_spring2.model.Report;

import java.io.IOException;
import java.util.Optional;

public interface ReportService  {

    Integer createReport() throws IOException;

    Optional<Report> getReportById(int id);
}
