package ru.skypro.lessons.springboot.homework_spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.homework_spring2.model.ReportPath;
@Repository
public interface ReportPathRepository extends JpaRepository<ReportPath, Integer> {


}
