package com.training.cognizant.fse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.cognizant.fse.entities.ParentTask;
import com.training.cognizant.fse.entities.Project;
import com.training.cognizant.fse.entities.Task;
import com.training.cognizant.fse.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FSE_SBA_PMApplicationTest {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetUsers() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/pms/getUsers", List.class)).isNotEmpty();
		;
	}

	@Test
	public void testCreateUser() throws Exception {

		User user = new User(0, "Amelia", "Mourismo", "326622");

		final String baseUrl = "http://localhost:" + port + "/pms/createUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertNotEquals("New User id is Not Zero ", 0, result.getBody().getUserId());

	}

	@Test
	public void TestUpdateUser() throws Exception {

		String empId = generateRandomId();
		User user = new User(10, "Amelia", "Mourismo", empId);
		final String baseUrl = "http://localhost:" + port + "/pms/updateUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, request, User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals("class com.training.cognizant.fse.entities.User", result.getBody().getClass().toString());
		assertEquals(empId, result.getBody().getEmployeeId());
		// assertEquals("New User id is one",1,result.getBody().getUserId());
		assertNotEquals("New User id is Not Zero ", 0, result.getBody().getUserId());

	}

	@Test
	public void testDeleteUser() throws Exception {
		String empId = generateRandomId();
		User user = new User(0, "Amelia", "Mourismo", empId);

		String baseUrl = "http://localhost:" + port + "/pms/createUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);
		baseUrl = "http://localhost:" + port + "/pms/deleteUser/" + result.getBody().getUserId();
		uri = new URI(baseUrl);
		restTemplate.delete(uri);
	}

	@Test
	public void testCreateProject() throws Exception {
		User user = new User(10, "Amelia", "Mourismo", "326622");
		Project project = new Project(0, "Test Project", new Date(), new Date(), 1, user, 2, 2);
		final String baseUrl = "http://localhost:" + port + "/pms/createProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.postForEntity(uri, request, Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.Project", result.getBody().getClass().toString());
		assertNotEquals(0, result.getBody().getProjectId());
	}

	@Test
	public void testProjectWithNoUser() throws Exception {
		Project project = new Project(0, "Test Project", new Date(), new Date(), 1, null, 2, 2);
		final String baseUrl = "http://localhost:" + port + "/pms/createProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.postForEntity(uri, request, Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.Project", result.getBody().getClass().toString());
		assertEquals("Test Project", result.getBody().getProjectName());
		assertNotNull(result.getBody().getPriority());
	}

	@Test
	public void testGetAllProjects() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/pms/getProjects", List.class)).isNotEmpty();
		;
	}

	@Test
	public void testUpdateProject() throws Exception {
		User user = new User(10, "Amelia", "Mourismo", "326622");
		Project project = new Project(1, "Test Project", new Date(), new Date(), 1, user, 2, 2);
		final String baseUrl = "http://localhost:" + port + "/pms/updateProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.exchange(uri, HttpMethod.PUT, request, Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.Project", result.getBody().getClass().toString());
		assertEquals("Test Project", result.getBody().getProjectName());
		assertNotNull(result.getBody().getPriority());
	}

	@Test
	public void testCreateParentTask() throws Exception {
		ParentTask ptask = new ParentTask(0, "PMS going under test");
		final String baseUrl = "http://localhost:" + port + "/pms/createParentTask";
		URI uri = new URI(baseUrl);
		HttpEntity<ParentTask> request = new HttpEntity<>(ptask);
		ResponseEntity<ParentTask> result = restTemplate.postForEntity(uri, request, ParentTask.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.ParentTask", result.getBody().getClass().toString());
		assertNotEquals("New id is not zero", 0, result.getBody().getParentId());

	}

	@Test
	public void testCreateTask() throws Exception {
		ParentTask ptask = new ParentTask(1, "Test parent Task");
		User user = new User(10, "Amelia", "Mourismo", "326622");
		Project project = new Project(1, "Test Project", new Date(), new Date(), 1, user, 2, 2);
		Task task = new Task(0, ptask, project, user, "Test Task", new Date(), new Date(), 1, "ACT");
		final String baseUrl = "http://localhost:" + port + "/pms/createTask?parentId=2&projectId=1&userId=10";
		URI uri = new URI(baseUrl);
		HttpEntity<Task> request = new HttpEntity<>(task);
		ResponseEntity<Task> result = restTemplate.exchange(uri, HttpMethod.POST, request, Task.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.Task", result.getBody().getClass().toString());
		assertEquals("Test Task", result.getBody().getTaskName());
		assertEquals("ACT", result.getBody().getStatus());
		assertNotNull(result.getBody().getPriority());
	}

	@Test
	public void testUpdateTask() throws Exception {
		ParentTask ptask = new ParentTask(1, "ParentTask1");
		User user = new User(10, "Amelia", "Mourismo", "326622");
		Project project = new Project(1, "Test Project", new Date(), new Date(), 1, user, 2, 2);
		Task task = new Task(0, ptask, project, user, "Test Task", new Date(), new Date(), 1, "ACT");
		final String baseUrl = "http://localhost:" + port + "/pms/updateTask?parentId=2&projectId=1&userId=10";
		URI uri = new URI(baseUrl);
		HttpEntity<Task> request = new HttpEntity<>(task);
		ResponseEntity<Task> result = restTemplate.exchange(uri, HttpMethod.PUT, request, Task.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.training.cognizant.fse.entities.Task", result.getBody().getClass().toString());
		assertEquals("Test Task", result.getBody().getTaskName());
		assertNotNull(result.getBody().getPriority());
	}

	@Test
	public void testGetTasks() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/pms/getTasks", List.class)).isNotEmpty();
		;
	}

	@Test
	public void testGetTaskById() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/pms/getTaskById?taskId=2", Task.class))
				.isNotNull();
		;
	}

	@Test
	public void getTaskByProjectId() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/pms/getTasksByPorjectId?projectId=0",
				List.class)).isEmpty();
	}
	
	private String generateRandomId() {
		return String.valueOf((10000 + new Random().nextInt(90000)));
	}

}
