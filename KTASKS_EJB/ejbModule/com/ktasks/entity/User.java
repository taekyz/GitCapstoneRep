package com.ktasks.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userId;

	private String firstName;

	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="user")
	private List<Task> tasks;

	//bi-directional many-to-one association to Contactlist
	@OneToMany(mappedBy="user1")
	private List<Contactlist> contactlists1;

	//bi-directional many-to-one association to Contactlist
	@OneToMany(mappedBy="user2")
	private List<Contactlist> contactlists2;

	//bi-directional many-to-one association to Sharedtask
	@OneToMany(mappedBy="user")
	private List<Sharedtask> sharedtasks;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Contactlist> getContactlists1() {
		return this.contactlists1;
	}

	public void setContactlists1(List<Contactlist> contactlists1) {
		this.contactlists1 = contactlists1;
	}

	public List<Contactlist> getContactlists2() {
		return this.contactlists2;
	}

	public void setContactlists2(List<Contactlist> contactlists2) {
		this.contactlists2 = contactlists2;
	}

	public List<Sharedtask> getSharedtasks() {
		return this.sharedtasks;
	}

	public void setSharedtasks(List<Sharedtask> sharedtasks) {
		this.sharedtasks = sharedtasks;
	}

}