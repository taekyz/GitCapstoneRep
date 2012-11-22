package com.ktasks.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sharedtask database table.
 * 
 */
@Entity
public class Sharedtask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int shareId;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="taskId")
	private Task task;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="friendId")
	private User user;

	public Sharedtask() {
	}

	public int getShareId() {
		return this.shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}