package com.ktasks.utility;

import java.util.Date;

import javax.ejb.EJB;

import com.ktasks.data.TaskData;
import com.ktasks.eao.TaskEao;
import com.ktasks.eao.UserEao;
import com.ktasks.entity.Task;
import com.ktasks.entity.User;

public class DataToJPA {
	@EJB TaskEao taskEao;
	@EJB UserEao userEao;
	
	public DataToJPA(){
		
	}
	
	public Task convertToTask(TaskData data){
		
		Task task = new Task();
		task.setTaskId(data.getTaskId());
		task.setTaskName(data.getTaskName());
		task.setCompleted(data.getCompleted());
		task.setShared(data.getShared());
		task.setTaskNotes(data.getTaskNotes());
		
		StringToDate formatter = new StringToDate() ;
		Date date = formatter.convert(data.getTaskAlert());
		if (date == null){
			return null;
		}
		task.setTaskAlert(date);
		
		User user = new User();
    	try {
			user = userEao.getUserByUsername(data.getUsername());
		} catch (Exception exception) {
			System.out.print("DEBUG: USER ID NOT FOUND");
			return null;
		}
		task.setUser(user);
		return task;
	}
}
