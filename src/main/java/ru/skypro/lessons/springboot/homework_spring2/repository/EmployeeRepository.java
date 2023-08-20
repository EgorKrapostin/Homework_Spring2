package ru.skypro.lessons.springboot.homework_spring2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.homework_spring2.model.Employee;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "SELECT * FROM employee",
            nativeQuery = true)
    List<Employee> getAllEmployees();

    @Query(value = "SELECT SUM(salary) " +
            "FROM employee",
    nativeQuery = true)
    Integer getSalarySum();

    @Query(value = "SELECT MIN(salary) " +
            "FROM employee",nativeQuery = true)
    Optional<Integer> getMinSalary();

    @Query(value = "SELECT MAX(salary) " +
            "FROM employee",nativeQuery = true)
    Optional<Integer> getMaxSalary();

    @Query(value = "SELECT * FROM employee " +
            "WHERE salary >(SELECT AVG (salary) FROM employee)",
    nativeQuery = true)
    List<Employee> getAllEmployeesWithSalaryHigherThenAvg();

    @Query(value = "SELECT * FROM employee "+
            "WHERE salary > :salary",
    nativeQuery = true)
    List<Employee> getAllEmployeesWithSalaryHigherThan(@Param("salary") int salary);

    @Query(value = "SELECT employee.id, employee.name, employee.salary, employee.position_id FROM employee "+
        "INNER JOIN position " +
        "ON employee.position_id = position.id and position.name = :name",
        nativeQuery = true)
    List<Employee> getAllEmployeesWithMatchingPosition(@Param("name") String name);
}
