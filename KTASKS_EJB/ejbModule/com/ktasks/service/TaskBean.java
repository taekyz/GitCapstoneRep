package com.ktasks.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.ktasks.entity.*;
import com.ktasks.eao.*;
import com.ktasks.data.*;
import com.ktasks.utility.*;

/**
 * Session Bean implementation class TaskBean
 */
@Stateless
@LocalBean
@WebService
public class TaskBean implements TaskBeanRemote {
	@EJB TaskEao taskEao;
	@EJB UserEao userEao;

    /**
     * Default constructor. 
     */
    public TaskBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<TaskData> getUsersTask(String username){
 	    // TODO get list of users tasks given username
    	
    	List<Task> taskList = new LinkedList<Task>();
    	List<Sharedtask> sharedList = new LinkedList<Sharedtask>();
    	List<TaskData> taskDataList = new LinkedList<TaskData>();

    	User user = new User();
    	
    	// get user by username
    	try{
    		user = userEao.getUserByUsername(username);
    	} catch (Exception e){
			System.out.print("DEBUG: USERNAME NOT FOUND");
    		return null;
    	}
    	
    	// get tasks by user id
    	try{
    		taskList = taskEao.getUserTasks(user);
    	} catch (Exception e){
			System.out.print("DEBUG: USER TASKS NOT FOUND");
    		return null;
    	}
    	
    	// get shared tasks by userid
    	try{
    		sharedList = taskEao.getSharedTasks(user);
    	} catch (Exception e){
			System.out.print("DEBUG: USER SHARED TASKS NOT FOUND");
			return null;
    	}
    	
    	
    	// traverse shared list and get task and add to task list
    	Iterator<Sharedtask> itr = sharedList.iterator();
		while(itr.hasNext()){
			Sharedtask sTask = itr.next();
	    	taskList.add((sTask.getTask()));
		}
		
    	// traverse task list and convert to task data list
		Set<Task> taskSet = new HashSet<Task>(taskList);
		Iterator<Task> itr2 = taskSet.iterator();
		while(itr2.hasNext()){
			Task task = itr2.next();
			TaskData data = new TaskData();
			JPAtoData converter = new JPAtoData();
			data = converter.convertToTaskData(task);
			taskDataList.add(data);
		}
		
		
    	// return
		System.out.print("DEBUG: TASKS SENT");
    	return taskDataList;
    }
    
    @Override
    public List<TaskData> getAllTask(){
   
		List<TaskData> taskDataList = new LinkedList<TaskData>();
		List<Task> taskList = taskEao.getAllTasks();
		Iterator<Task> itr = taskList.iterator();
		while(itr.hasNext()){
			Task task = itr.next();
			TaskData data = new TaskData();
			JPAtoData converter = new JPAtoData();
			data = converter.convertToTaskData(task);
			taskDataList.add(data);
		}
		return taskDataList;
    }
    public String addTask(TaskData taskData){
    	Task task = new Task();
    	User user = new User();
    	try {
			user = userEao.getUserByUsername(taskData.getUsername());
		} catch (Exception exception) {
			System.out.print("DEBUG: USERNAME NOT FOUND");
			return "USER_NOT_FOUND";
		}
    	
    	task.setUser(user);
    	task.setTaskName(taskData.getTaskName());
		task.setCompleted(taskData.getCompleted());
		task.setShared(taskData.getShared());
		task.setTaskNotes(taskData.getTaskNotes());
		
		StringToDate formatter = new StringToDate() ;
		Date date = formatter.convert(taskData.getTaskAlert());
		task.setTaskAlert(date);
		
  		try {
  			taskEao.addNewTask(task);
  		} catch (Exception exception) {
  			return "ADD_TASK_FAIL";
  		}
  		return "OK";
    }
    /*------ OLD METHOD - WORKING ----
    @Override 
    public boolean addTask(TaskData taskData){
    	int i = 1;
    	Task task = new Task();
    	User user = new User();
    	try {
			user = userEao.getUserById(i);
		} catch (Exception exception) {
			System.out.print("DEBUG: USER ID NOT FOUND");
			return false;
		}
    	task.setUser(user);
    	task.setTaskName(name);
    	task.setTaskNotes(note);
  		try {
  			taskEao.addNewTask(task);
  		} catch (Exception exception) {
  			return false;
  		}
  		return true;
  		
  		//TODO edit addTask for new database
    }
    */
    
    @Override
	public String updateTask (TaskData taskData){
		Task task = new Task();

       	// get task
    	try {
			task = taskEao.getTaskById(taskData.getTaskId());
		} catch (Exception exception) {
			System.out.print("DEBUG: TASK ID NOT FOUND");
			return "TASK_NOT_FOUND";
		}
    	
    	// update task
		task.setTaskName(taskData.getTaskName());
		task.setCompleted(taskData.getCompleted());
		task.setShared(taskData.getShared());
		task.setTaskNotes(taskData.getTaskNotes());
		
		StringToDate formatter = new StringToDate() ;
		Date date = formatter.convert(taskData.getTaskAlert());
		task.setTaskAlert(date);
		
		User user = new User();
    	try {
			user = userEao.getUserByUsername(taskData.getUsername());
		} catch (Exception exception) {
			System.out.print("DEBUG: USERNAME NOT FOUND");
			return "USER_NOT_FOUND";
		}
		task.setUser(user);
		

    	try {
  			taskEao.updateTask(task);
  		} catch (Exception exception) {
  			return "UPDATE_FAILED";
  		}
    	return "OK";
	}
	
    @Override
    public String deleteTask(int id, String username){
    	Task task = null;
		try{
			task = taskEao.getTaskById(id);
		}catch(Exception e){
			System.out.print("DEBUG: TASK ID NOT FOUND");
			return "TASK_NOT_FOUND";
		}
		if (!(username.equals(task.getUser().getUsername()))){
			return "NOT_OWNER";
		}
		try{
			taskEao.deleteTask(task);
		}catch(Exception e){
			return "DELETE_FAIL";
		}
		return "OK";
	}
    
    @Override
    public String taskCompleted(int id, int completed){
    	// get task using id
    	Task task = null;
		try{
			task = taskEao.getTaskById(id);
		}catch(Exception e){
			System.out.print("DEBUG: TASK ID NOT FOUND");
			return "ERROR";
		}
		
		// update completed
		task.setCompleted(completed);
		
		// update task
		try {
  			taskEao.updateTask(task);
  		} catch (Exception exception) {
  			return "ERROR";
  		}
    	return "OK";
    }
    
    @Override
    public String taskShared(int id, int shared){
    	// get task using id
    	Task task = null;
		try{
			task = taskEao.getTaskById(id);
		}catch(Exception e){
			System.out.print("DEBUG: TASK ID NOT FOUND");
			return "ERROR";
		}
		
		// update shared
		task.setShared(shared);
		
		// update task
		try {
  			taskEao.updateTask(task);
  		} catch (Exception exception) {
  			return "ERROR";
  		}
    	return "OK";
    }
}
