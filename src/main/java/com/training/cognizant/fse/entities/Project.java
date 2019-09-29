package com.training.cognizant.fse.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="PROJECT")
@SqlResultSetMapping(
        name = "TaskSummary",
        entities = @EntityResult(
                entityClass = Project.class,
                fields = {
                    @FieldResult(name = "projectId", column = "Project_ID"),
                    @FieldResult(name = "projectName", column = "Project"),
                    @FieldResult(name = "startDate", column = "StartDate"),
                    @FieldResult(name = "endDate", column = "EndDate"),
                    @FieldResult(name = "priority", column = "Priority"),
                    @FieldResult(name = "user", column = "User_ID")}),
                    //@FieldResult(name = "tasksCount", column = "tasksCount"),
                    //@FieldResult(name = "completedTasks", column = "completedTasks")}),
        columns ={ 
        		@ColumnResult(name = "tasksCount", type = Long.class),
        		@ColumnResult(name = "completedTasks", type = Long.class)        
        })
        //columns = @ColumnResult(name = "completedTasks", type = Long.class))
//@SecondaryTable(name = "Task_Summary_vw")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Project() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Project_ID")
	private long projectId;
	@Column(name="Project")
	private String projectName;	
	private Date startDate;
	private Date endDate;
	private int priority;
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "User_ID")
	private User user;
	@Transient
	//@Column(name = "taskCount",table ="Task_Summary_vw", insertable=false,updatable=false)
	private long tasksCount;
	@Transient
	//@Column(name = "completedTasks",table ="Task_Summary", insertable=false,updatable=false)
	private long completedTasks;
	
	public Project(long projectId, String projectName, Date startDate, Date endDate, int priority, User user,
			long tasksCount, long completedTasks) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.user = user;
		this.tasksCount = tasksCount;
		this.completedTasks = completedTasks;
	}
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getTasksCount() {
		return tasksCount;
	}
	public void setTasksCount(long tasksCount) {
		this.tasksCount = tasksCount;
	}
	public long getCompletedTasks() {
		return completedTasks;
	}
	public void setCompletedTasks(long completedTasks) {
		this.completedTasks = completedTasks;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priority=" + priority + ", user=" + user + ", tasksCount=" + tasksCount
				+ ", completedTasks=" + completedTasks + "]";
	}
	
}
