package ru.skypro.lessons.springboot.homework_spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.lessons.springboot.homework_spring2.model.Position;

public interface PositionRepository extends JpaRepository<Position,Integer> {
}
