package ru.skypro.lessons.springboot.homework_spring2.DTO;

import ru.skypro.lessons.springboot.homework_spring2.model.ReportPath;

public class ReportPathDTO {
    private Integer id;
    private String path;


    public static ReportPathDTO fromReportPath(ReportPath reportPath) {
        ReportPathDTO reportPathDTO = new ReportPathDTO();
        reportPathDTO.setId(reportPath.getId());
        reportPathDTO.setPath(reportPath.getFilePath());
        return reportPathDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
