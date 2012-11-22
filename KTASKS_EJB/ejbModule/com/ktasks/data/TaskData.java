package com.ktasks.data;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class TaskData {
	
	private int taskId;

	private int completed;

	private int shared;

	private String taskAlert;

	private String taskName;

	private String taskNotes;
	
	private String username;

	public TaskData(){	
	}
	
	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskAlert() {
		return this.taskAlert;
	}

	public void setTaskAlert(String taskAlert) {
		this.taskAlert = taskAlert;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskNotes() {
		return this.taskNotes;
	}

	public void setTaskNotes(String taskNotes) {
		this.taskNotes = taskNotes;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getCompleted() {
		return this.completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}
	
	public int getShared() {
		return this.shared;
	}

	public void setShared(int shared) {
		this.shared = shared;
	}
}
