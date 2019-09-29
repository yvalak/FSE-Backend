package com.training.cognizant.fse.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TASK")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Task() {
		// TODO Auto-generated constructor stub
	}
	
	public Task(long taskId, ParentTask parentTask, Project project, User user, String taskName, Date startDate,
			Date endDate, int priority, String status) {
		super();
		this.taskId = taskId;
		this.parentTask = parentTask;
		this.project = project;
		this.user = user;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		Priority = priority;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Task_ID")
	private long taskId;
	@ManyToOne(targetEntity = ParentTask.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Parent_ID")
	private ParentTask parentTask;
	@ManyToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Project_ID")
	private Project project;
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "User_ID")
	private User user;
	@Column(name="Task")
	private String taskName;
	private Date startDate;
	private Date endDate;
	private int Priority;
	private String status;
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public ParentTask getParentTask() {
		return parentTask;
	}
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", parentTask=" + parentTask + ", project=" + project + ", user=" + user
				+ ", taskName=" + taskName + ", startDate=" + startDate + ", endDate=" + endDate + ", Priority="
				+ Priority + ", status=" + status + "]";
	}
	
	
	

}
