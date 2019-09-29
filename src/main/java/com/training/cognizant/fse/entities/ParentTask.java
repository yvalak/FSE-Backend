package com.training.cognizant.fse.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARENTTASK")
public class ParentTask implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ParentTask() {
		// TODO Auto-generated constructor stub
	}
	
public ParentTask(long parentId, String parentTaskName) {
		super();
		this.parentId = parentId;
		this.parentTaskName = parentTaskName;
	}

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="Parent_ID")
private long parentId;
@Column(name ="Parent_Task")
private String parentTaskName;
public long getParentId() {
	return parentId;
}
public void setParentId(long parentId) {
	this.parentId = parentId;
}
public String getParentTaskName() {
	return parentTaskName;
}
public void setParentTaskName(String parentTaskName) {
	this.parentTaskName = parentTaskName;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "ParentTask [parentId=" + parentId + ", parentTaskName=" + parentTaskName + "]";
}



}
