package com.mypractice.student.api.controller;

import com.mypractice.student.api.dto.StudentDto;
import com.mypractice.student.api.model.Student;
import com.mypractice.student.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Validated
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping(value = "/fetchStudents", params = "id")
    public ResponseEntity<Student> getStudent(@RequestParam("id")
                                                @Positive(message = "Student ID is not valid!") Long id){
        return ResponseEntity.ok(studentService.getStudent(id));
    }
    @GetMapping(value = "/fetchStudents", params = "class")
    public ResponseEntity<List<Student>> getStudentByClass(@RequestParam(value = "class")
                                              String division){
        return ResponseEntity.ok(studentService.getStudentsByClass(division));
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@Positive(message = "Student ID is not valid!")
                                                @NotNull
                                                @PathVariable("id") @NotNull Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.createStudent(student));
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent( @Positive(message = "Student ID is not valid!")
                                                    @NotNull
                                                    @PathVariable("id") Long id,
                                                    @RequestBody StudentDto student){

        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }
}