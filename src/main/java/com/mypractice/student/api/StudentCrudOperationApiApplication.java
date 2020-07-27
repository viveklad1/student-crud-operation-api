package com.mypractice.student.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class StudentCrudOperationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudOperationApiApplication.class, args);
	}

}
