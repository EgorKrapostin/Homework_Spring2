package ru.skypro.lessons.springboot.homework_spring2.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private int salary;

    @Column(name = "position_id",insertable=false, updatable=false,nullable = true)
    private int positionId;

    @ManyToOne()
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee() {
    }

    public Employee(Integer id, String name, int salary, int positionId, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.positionId = positionId;
        this.position = position;
    }
    public Employee(Integer id, String name, int salary,int positionId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.positionId = positionId;
    }

    public Employee(Integer id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int salary, Position position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public Employee(String name, int salary, int positionId, Position position) {
        this.name = name;
        this.salary = salary;
        this.positionId = positionId;
        this.position = position;
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

}
