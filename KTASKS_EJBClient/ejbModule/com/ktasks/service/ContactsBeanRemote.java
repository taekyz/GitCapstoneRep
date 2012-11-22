package com.ktasks.service;

import javax.ejb.Remote;

@Remote
public interface ContactsBeanRemote {
	
	public boolean addContact(String username, String username2);
	public boolean deleteContact(String username, String username2);


}
