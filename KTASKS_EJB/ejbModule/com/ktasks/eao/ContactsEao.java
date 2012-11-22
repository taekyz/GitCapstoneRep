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
public class ContactsEao {
    @PersistenceContext
    EntityManager em;

    /**
     * Default constructor.
     */
    public ContactsEao() {
    }

    // Get
    
    public Contactlist getContact(User user1, User user2) throws Exception {
    	Query query = em.createQuery("SELECT e FROM Contactlist e WHERE e.user1 = :user1 AND e.user2 =:user2");
        query.setParameter("user1", user1);
        query.setParameter("user2", user2);
        Contactlist result = (Contactlist) query.getSingleResult();
		return result;
    }
    
    // Add, Remove & Update
    public void addContact(Contactlist contact) throws Exception {
    	em.persist(contact);
    }
     
    public void deleteContact(Contactlist contact) throws Exception {
    	em.remove(contact);
    }


}
