package ru.skypro.lessons.springboot.homework_spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.lessons.springboot.homework_spring2.DTO.ReportDTO;
import ru.skypro.lessons.springboot.homework_spring2.model.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT new ru.skypro.lessons.springboot.homework_spring2.DTO.ReportDTO( " +
            "p.name, " +
            "count(e.id), " +
            "min(e.salary), " +
            "max(e.salary), " +
            "avg(e.salary)) " +
            "from Employee e join fetch Position p " +
            "where e.position = p " +
            "group by p.id")
    public List<ReportDTO> createReport();
}
