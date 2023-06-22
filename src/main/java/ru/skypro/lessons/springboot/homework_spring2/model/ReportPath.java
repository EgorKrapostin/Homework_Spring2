package ru.skypro.lessons.springboot.homework_spring2.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "report_path")
public class ReportPath implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "path")
    private String filePath;

    public ReportPath() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "ReportPath{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
