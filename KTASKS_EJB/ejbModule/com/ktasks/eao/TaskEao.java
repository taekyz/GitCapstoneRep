package com.ktasks.eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ktasks.entity.*;

/**
 * Session Bean implementation class UserEao
 */
@Stateless
@LocalBean
public class TaskEao {
    @PersistenceContext
    EntityManager em;

    /**
     * Default constructor.
     */
    public TaskEao() {
    }
    
    //Get
    
    @SuppressWarnings("unchecked")
    public List<Task> getUserTasks(User user){
		Query query = em.createQuery("SELECT e FROM Task e WHERE e.user = :user");
		query.setParameter("user", user);	
		List<Task> taskList = (List<Task>) query.getResultList();
		return taskList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Sharedtask> getSharedTasks(User user){
		Query query = em.createQuery("SELECT e FROM Sharedtask e WHERE e.user = :user");
		query.setParameter("user", user);	
		List<Sharedtask> sharedList = (List<Sharedtask>) query.getResultList();
		return sharedList;
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getTasks(){
		Query query = em.createQuery("SELECT e.taskName FROM Task e");
		List<String> taskList = (List<String>) query.getResultList();
		return taskList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Task> getAllTasks(){
		Query query = em.createQuery("SELECT e FROM Task e");
		List<Task> taskList = (List<Task>) query.getResultList();
		return taskList;
    }
    
    public Task getTaskById(int id) throws NoResultException {
		Query query = em.createQuery("SELECT e FROM Task e WHERE e.taskId = :id");
		query.setParameter("id", id);
		Task result = (Task) query.getSingleResult();
		return result;
    }
    
    //Add, Remove & Update
    public void addNewTask(Task task) throws Exception {
    	em.persist(task);
    }
    
    public void deleteTask(Task task) throws Exception {
    	em.remove(task);
    }
    
    public void updateTask(Task task) throws Exception {
    	em.merge(task);
    }


}