package com.mypractice.student.api;

import com.mypractice.student.api.dto.StudentDto;
import com.mypractice.student.api.model.Student;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentCrudOperationApiApplicationTests {

	@LocalServerPort
	private int randomPort;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Order(1)
	public void testGetStudentListSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/students";
		ResponseEntity<Student[]> responseEntity = testRestTemplate.getForEntity(url, Student[].class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals(7, responseEntity.getBody().length);
	}

	@Test
	@Order(4)
	public void testGetStudentByIdSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/fetchStudents?id=1";
		ResponseEntity<Student> responseEntity = testRestTemplate.getForEntity(url, Student.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals("John", responseEntity.getBody().getFirstName());
		Assert.assertEquals("Smith", responseEntity.getBody().getLastName());
		Assert.assertEquals("3 A", responseEntity.getBody().getDivision());
		Assert.assertEquals("UK", responseEntity.getBody().getNationality());
	}

	@Test
	@Order(5)
	public void testGetStudentByClassSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/fetchStudents?class=3 A";
		ResponseEntity<Student[]> responseEntity = testRestTemplate.getForEntity(url, Student[].class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals(3, responseEntity.getBody().length);
		Assert.assertEquals("John", responseEntity.getBody()[0].getFirstName());
		Assert.assertEquals("Vivian", responseEntity.getBody()[1].getFirstName());
		Assert.assertEquals("Kevin", responseEntity.getBody()[2].getFirstName());
	}

	@Test
	public void testDeleteStudentInvalidId() {
		String url = getBaseURLWithPort()+"/api/v1/students/{id}";
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null,Void.class, 0);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	@Test
	@Order(6)
	public void testDeleteStudentByIdSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/students/{id}";
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null,Void.class, 6);
		Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
	@Test
	public void testCreateStudentSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/students/";
		Student student = new Student();
		student.setFirstName("Hrithik");
		student.setLastName("Roshan");
		student.setDivision("4 A");
		student.setNationality("Indian");
		ResponseEntity<Student> responseEntity = testRestTemplate.postForEntity(url,student, Student.class);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertTrue(responseEntity.getBody().getId()!=null);
	}
	@Test
	@Order(7)
	public void testUpdateStudentSuccess() {
		String url = getBaseURLWithPort()+"/api/v1/students/{id}";
		StudentDto student = new StudentDto();
		student.setFirstName(JsonNullable.of("Jonathan"));
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<StudentDto> httpEntity = new HttpEntity<>(student, headers);
		// send PUT request to update post with `id` 1
		ResponseEntity<Student> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, Student.class, 1);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertTrue(responseEntity.getBody().getId()!=null);
		Assert.assertEquals("Jonathan", responseEntity.getBody().getFirstName());
	}
	private String getBaseURLWithPort() {
		return "http://localhost:" + randomPort;
	}
}
