package com.redolf.thymeleaf.model;

import com.redolf.thymeleaf.auditing.CustomAuditorAware;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_student")
@EntityListeners(CustomAuditorAware.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "This field is required!")
    private String reference;

    @NotBlank(message = "This field is required!")
    private String firstname;

    @NotBlank(message = "This field is required!")
    private String lastname;

    @NotBlank(message = "This field is required!")
    private String program;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDate createdDate;

    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parent> parents;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> course;


}
