package com.ktasks.service;

import java.util.List;

import javax.ejb.Remote;

import com.ktasks.data.*;
@Remote
public interface TaskBeanRemote {

	public List<TaskData> getUsersTask(String username);
	public List<TaskData> getAllTask();
	public String addTask(TaskData taskData);
	public String updateTask (TaskData taskData);
	public String deleteTask(int id, String username);
	public String taskCompleted(int id, int completed);
	public String taskShared(int id, int shared);
}

