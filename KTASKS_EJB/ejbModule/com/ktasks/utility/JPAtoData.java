package com.ktasks.utility;

import com.ktasks.eao.*;
import javax.ejb.EJB;
import com.ktasks.entity.*;
import com.ktasks.data.*;

public class JPAtoData {
	@EJB TaskEao taskEao;
	@EJB UserEao userEao;
	public JPAtoData(){
		
	}
	
	public TaskData convertToTaskData(Task task){
		TaskData data = new TaskData();
		data.setTaskId(task.getTaskId());
		data.setTaskName(task.getTaskName());
		data.setCompleted(task.getCompleted());
		data.setShared(task.getShared());
		if (task.getTaskAlert() != null){
			data.setTaskAlert(task.getTaskAlert().toString());
		}
		data.setTaskNotes(task.getTaskNotes());
		data.setUsername(task.getUser().getUsername());
		return data;
	}
	
	public UserData convertToUserData(User user){
		UserData data = new UserData();
		data.setUserId(user.getUserId());
		data.setUsername(user.getUsername());
		data.setFirstName(user.getFirstName());
		data.setLastName(user.getLastName());
		data.setPassword(user.getPassword());
		return data;
	}
	
	
}
