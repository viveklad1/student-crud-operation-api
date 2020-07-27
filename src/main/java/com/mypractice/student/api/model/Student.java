package com.mypractice.student.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "FirstName is required!")
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @JsonProperty("class")
    @Column(name="class")
    @NotBlank(message = "Class is required!")
    private String division;
    @Column(name="nationality")
    private String nationality;
}