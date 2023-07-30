package ru.skypro.lessons.springboot.homework_spring2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.homework_spring2.model.Report;
import ru.skypro.lessons.springboot.homework_spring2.model.ReportPath;
import ru.skypro.lessons.springboot.homework_spring2.repository.ReportPathRepository;
import ru.skypro.lessons.springboot.homework_spring2.repository.ReportRepository;
import ru.skypro.lessons.springboot.homework_spring2.service.ReportServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepositoryMock;

    @Mock
    private ReportPathRepository reportPathRepositoryMock;

    @InjectMocks
    private ReportServiceImpl reportService;

    private final static List<Report> listReport = List.of(new Report(1, "23.07"),
            new Report(2, "23.07"),
            new Report(3, "23.07"));

    private final static List<ReportPath> listReportPath = List.of(new ReportPath(1, "file1"),
            new ReportPath(2, "file2"),
            new ReportPath(3, "file3"));

    @Test
    public void shouldCreateReport() {

        reportRepositoryMock.createReport();
        verify(reportRepositoryMock, times(1)).createReport();
    }

    @Test
    public void shouldReturnReportById() {

        Integer id = 1;
        Optional<Report> rep = listReport.stream()
                .filter(report -> report.getId() == id)
                .findFirst();

        Assertions.assertTrue(rep.isPresent());
    }

    @Test
    public void shouldCreateReportWithPath()  {

        reportRepositoryMock.createReport();
        verify(reportRepositoryMock, times(1)).createReport();
    }

    @Test
    public void shouldReturnPathReportById() {
        Integer id = 1;

        Optional<ReportPath> repPath = listReportPath.stream()
                .filter(reportPath -> reportPath.getId() == id)
                .findFirst();

        assertTrue(repPath.isPresent());
    }
}
