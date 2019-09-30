	package com.training.cognizant.fse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.cognizant.fse.entities.ParentTask;
import com.training.cognizant.fse.entities.Project;
import com.training.cognizant.fse.entities.Task;
import com.training.cognizant.fse.entities.User;
import com.training.cognizant.fse.service.impl.ParentTaskServiceImpl;
import com.training.cognizant.fse.service.impl.ProjectServiceImpl;
import com.training.cognizant.fse.service.impl.TaskServiceImpl;
import com.training.cognizant.fse.service.impl.UserServiceImpl;

/**
 *
 * A Task Manager controller
 */
@RestController("/pms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PMSController {
	Logger logger = LoggerFactory.getLogger(PMSController.class);
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private ProjectServiceImpl projectServiceImpl;
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	@Autowired
	private ParentTaskServiceImpl parentTaskServImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText() {
        return "PMS Backend running and this is a Keep alive screen!";
    }
    
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@RequestBody User user){
    	logger.debug("create User");
    	return userServiceImpl.create(user);
    }
    
	@PutMapping("updateUser")
    public User updateUser(@RequestBody User user){
    	logger.debug("update User");
    	return userServiceImpl.update(user);
    }
	
	@DeleteMapping(value = "deleteUser/{userId}")
    public void deleteUser(@PathVariable long userId){
    	logger.debug("delete User");
    	userServiceImpl.deleteById(userId);
    }
    
    @GetMapping("/getUsers")
    public List<User> getAllUsers(){
    	logger.debug("get All Users");
    	return userServiceImpl.findAll();
    }
    
    @PostMapping("/createProject")
    public Project createProject(@RequestBody Project project, @RequestParam("userId")long userId){
    	logger.debug("create Project");
    	User user = userServiceImpl.findById(userId);
    	project.setUser(user);
    	return projectServiceImpl.create(project);
    }
    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project project, @RequestParam("userId")long userId){
    	logger.debug("update Project");
    	User user = userServiceImpl.findById(userId);
    	project.setUser(user);
    	return projectServiceImpl.update(project);
    }
    @GetMapping("/getProjects")
    public List<Project> getAllProjects(){
    	logger.debug("get All Projects");
    	return projectServiceImpl.getProjectsWithTaskSummary(JpaSort.unsafe("Project").ascending());
    }
    
    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task task, @RequestParam("parentId")long parentId, @RequestParam("projectId")long projectId, @RequestParam("userId")long userId){
    	logger.debug("create Task");
    	ParentTask parentTask = parentTaskServImpl.findById(parentId);
    	Project project = projectServiceImpl.findById(projectId);
    	User user = userServiceImpl.findById(userId);
    	task.setParentTask(parentTask);
    	task.setProject(project);
    	task.setUser(user);
    	return taskServiceImpl.create(task);
    }
    @PutMapping("/updateTask")
    public Task updateTask(@RequestBody Task task, @RequestParam("parentId")long parentId, @RequestParam("projectId")long projectId, @RequestParam("userId")long userId){
    	logger.debug("update Task");
    	ParentTask parentTask = parentTaskServImpl.findById(parentId);
    	Project project = projectServiceImpl.findById(projectId);
    	User user = userServiceImpl.findById(userId);
    	task.setParentTask(parentTask);
    	task.setProject(project);
    	task.setUser(user);
    	return taskServiceImpl.update(task);
    }
	@GetMapping("/getTaskById")
    public Task getTask(@RequestParam("taskId")long taskId){
    	logger.debug("get Task");
    	return taskServiceImpl.findById(taskId);
    }
    @GetMapping("/getTasks")
    public List<Task> getAllTasks(){
    	logger.debug("get All Task");
    	return taskServiceImpl.findAll();
    }
    @GetMapping("/getTasksByPorjectId")
    public List<Task> getAllTasks(@RequestParam("projectId")long projectId){
    	logger.debug("get All Task by ProjectId");
    	return taskServiceImpl.findByProjectId(projectId);
    }
    
    @PostMapping("/createParentTask")
    public ParentTask createParentTask(@RequestBody ParentTask parentTask){
    	logger.debug("create Parent task");
    	return parentTaskServImpl.create(parentTask);
    }
	
    @GetMapping("/getParentTasks")
    public List<ParentTask> getParentTasks(){
    	logger.debug("get Parent tasks");
    	return parentTaskServImpl.findAll();
    }
}
