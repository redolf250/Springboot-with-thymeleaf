package com.redolf.thymeleaf.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_course")
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String courseCode;
    private String courseTitle;
    private String department;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> student;
}