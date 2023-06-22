package ru.skypro.lessons.springboot.homework_spring2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.homework_spring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.homework_spring2.DTO.ReportPathDTO;
import ru.skypro.lessons.springboot.homework_spring2.model.Report;
import ru.skypro.lessons.springboot.homework_spring2.model.ReportPath;
import ru.skypro.lessons.springboot.homework_spring2.repository.ReportPathRepository;
import ru.skypro.lessons.springboot.homework_spring2.repository.ReportRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final ReportPathRepository reportPathRepository;

    public ReportServiceImpl(ReportRepository reportRepository, ReportPathRepository reportPathRepository) {
        this.reportRepository = reportRepository;
        this.reportPathRepository = reportPathRepository;
    }

    @Override
    public Integer createReport() throws IOException {

        Report report = new Report();
        report.setData(String.valueOf((reportRepository.createReport())));

        reportRepository.save(report);

        return report.getId();

    }

    @Override
    public Optional<Report> getReportById(int id) {

        return reportRepository.findById(id);
    }

    @Override
    public Integer createReportWithPath() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = String.valueOf(reportRepository.createReport());
        objectMapper.writeValue(new File("rep.json"),json);
        ReportPath reportPath = new ReportPath();
        Path path = Paths.get(("rep.json"));

//        String json = String.valueOf(reportRepository.createReport());
        Resource resource = new ByteArrayResource(json.getBytes());
        reportPath.setFilePath(String.valueOf(path.toAbsolutePath()));
        reportPathRepository.save(reportPath);
        return reportPath.getId();
    }

    @Override
    public Optional<ReportPathDTO> getReportPathById(int id) {

        return Optional.of(ReportPathDTO.fromReportPath(reportPathRepository.getReferenceById(id)));
    }
}
