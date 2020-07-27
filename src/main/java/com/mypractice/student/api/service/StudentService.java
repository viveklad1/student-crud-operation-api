package com.mypractice.student.api.service;

import com.mypractice.student.api.dto.StudentDto;
import com.mypractice.student.api.model.Student;
import com.mypractice.student.api.repository.StudentRepository;
import com.mypractice.student.api.util.JsonNullableUtils;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudent(Long id){
        return studentRepository.findById(id).orElse(null);
    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public  Student updateStudent(Long id , StudentDto student) {
        Student student1 = studentRepository.findById(id).orElseThrow(()->new ObjectNotFoundException(id,"Student"));
        JsonNullableUtils.changeIfPresent(student.getFirstName(),student1::setFirstName);
        JsonNullableUtils.changeIfPresent(student.getLastName(),student1::setLastName);
        JsonNullableUtils.changeIfPresent(student.getDivision(),student1::setDivision);
        JsonNullableUtils.changeIfPresent(student.getNationality(),student1::setNationality);
        return studentRepository.save(student1);
    }
    public List<Student> getStudentsByClass(String division){
        return studentRepository.findByDivision(division);
    }
}
