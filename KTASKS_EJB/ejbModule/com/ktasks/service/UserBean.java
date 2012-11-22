package com.ktasks.service;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.ktasks.data.*;
import com.ktasks.eao.*;
import com.ktasks.entity.*;
import com.ktasks.utility.*;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
@WebService
public class UserBean implements UserBeanRemote {
	@EJB TaskEao taskEao;
	@EJB UserEao userEao;
	
    /**
     * Default constructor. 
     */
	
    public UserBean() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
    public String checkLogin(String username, String password){
    	// TODO check user login
		User user = null;
		if(isUser(username)){
			user = userEao.getUserByUsername(username);

			if (user.getPassword().equals(password)){
				System.out.print("DEBUG: LOGIN OK");
				return "OK";
			} else {
				return "PASSWORD_FAIL";
			}
		}
    	return "NO_USER";
    }
    
	@Override
    public String addUser(UserData data){
    	//TODO add new user
		User user = new User();
		System.out.print("DEBUG");
		int count = 0;
		try {
			count = userEao.checkUserExistByUsername(data.getUsername());
			System.out.print(count);
		} catch (Exception e){
			return "ERROR";
		}
		
		if (count==1){
			return "USERNAME_EXIST";
		}
		user.setUsername(data.getUsername());
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setPassword(data.getPassword());
		try{
			userEao.addNewUser(user);
		} catch (Exception e2){
			return "ADD_USER_FAILED";
		}
    	return "OK";
    }
    
	@Override
    public String deleteUser(String username){
    	//TODO delete user
    	User user = new User();
		try{
			user = userEao.getUserByUsername(username);
		}catch(Exception e){
			System.out.print("DEBUG: USERNAME NOT FOUND");
			return "NO_USER_FOUND";
		}
		try{
			userEao.deleteUser(user);
		}catch(Exception e){
			return "DELETE_USER_FAILED";
		}
		return "OK";
    }

	@Override
    public String updateUser(UserData data){
    	//TODO update user
		
		//get user
		User user = new User();
    	try {
			user = userEao.getUserByUsername(data.getUsername());
		} catch (Exception e) {
			System.out.print("DEBUG: USER ID NOT FOUND");
			return "NO_USER";
		}
		
    	//update user
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setPassword(data.getPassword());

    	try {
  			userEao.updateUser(user);
  		} catch (Exception e) {
  			return "UPDATE_FAILED";
  		}
    	return "OK";
    }
	
	@Override
	public UserData getUserDetails(String username){
		UserData userdata = new UserData();
		User user = new User();

		//get user by username
		try {
			user = userEao.getUserByUsername(username);
		} catch (Exception exception) {
			System.out.print("DEBUG: USERNAME NOT FOUND");
			return null;
		}
		
		//convert to data
		JPAtoData converter = new JPAtoData();
		userdata = converter.convertToUserData(user);
		return userdata;
		
	}
	
	private boolean isUser(String username){
		try{
			userEao.getUserByUsername(username);
		} catch (Exception exception){
			return false;
		}
		return true;
	}
}
