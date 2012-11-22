package com.ktasks.service;

import javax.ejb.Remote;
import com.ktasks.data.*;

@Remote
public interface UserBeanRemote {
	public String checkLogin(String username, String password);
	public String updateUser(UserData data);
	public String deleteUser(String username);
	public String addUser(UserData data);
	public UserData getUserDetails(String username);
	
}
