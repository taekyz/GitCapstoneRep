package com.ktasks.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int taskId;

	private int completed;

	private int shared;

	@Temporal(TemporalType.TIMESTAMP)
	private Date taskAlert;

	private String taskName;

	private String taskNotes;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	//bi-directional many-to-one association to Sharedtask
	@OneToMany(mappedBy="task")
	private List<Sharedtask> sharedtasks;

	public Task() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

	public Date getTaskAlert() {
		return this.taskAlert;
	}

	public void setTaskAlert(Date taskAlert) {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Sharedtask> getSharedtasks() {
		return this.sharedtasks;
	}

	public void setSharedtasks(List<Sharedtask> sharedtasks) {
		this.sharedtasks = sharedtasks;
	}

}