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
public class UserEao {
    @PersistenceContext
    EntityManager em;

    /**
     * Default constructor.
     */
    public UserEao() {
    }

    
    // Getters
    public User getUserByUsername(String username) throws NoResultException {
		Query query = em.createQuery("SELECT e FROM User e WHERE e.username=:username");
		query.setParameter("username", username);
		User result = (User) query.getSingleResult();
		return result;
    }
    
    public User getUserById(int i) throws NoResultException {
		Query query = em.createQuery("SELECT e FROM User e WHERE e.userId=:id");
		query.setParameter("id", i);
		User result = (User) query.getSingleResult();
		return result;
    }
    
    //Checkers
    public int checkUserExistByUsername(String username) throws Exception {
      Query query = em.createQuery("SELECT COUNT(e.username) FROM User e WHERE e.username=:uname");
      query.setParameter("uname", username);
      int result = Integer.parseInt(query.getSingleResult().toString());
      return result;
    }
    
    public int checkUserLogin(String username, String hash) throws Exception {
        Query query = em.createQuery("SELECT COUNT(e.username) FROM User e WHERE e.username=:uname AND e.passHash=:hash");
        query.setParameter("uname", username);
        query.setParameter("hash", hash);
        int result = Integer.parseInt(query.getSingleResult().toString());
        return result;
    }
    
    public int checkUserExistByEmail(String email) throws Exception {
        Query query = em.createQuery("SELECT COUNT(e.email) FROM User e WHERE e.email=:email");
        query.setParameter("email", email);
        int result = Integer.parseInt(query.getSingleResult().toString());
        return result;
    }
    
    // Add, Remove & Update
    public void addNewUser(User user) throws Exception {
    	em.persist(user);
    }
    
    public void updateUser(User user) throws Exception {
    	em.merge(user);
    }
    
    public void deleteUser(User user) throws Exception {
    	em.remove(user);
    }


}