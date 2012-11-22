package com.ktasks.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contactlist database table.
 * 
 */
@Entity
public class Contactlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int friendId;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId1")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId2")
	private User user2;

	public Contactlist() {
	}

	public int getFriendId() {
		return this.friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}