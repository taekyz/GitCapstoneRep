package com.ktasks.service;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.ktasks.eao.ContactsEao;
import com.ktasks.eao.UserEao;
import com.ktasks.entity.Contactlist;
import com.ktasks.entity.User;

/**
 * Session Bean implementation class ContactsBean
 */
@Stateless
@LocalBean
@WebService
public class ContactsBean implements ContactsBeanRemote {
	@EJB ContactsEao contactsEao;
	@EJB UserEao userEao;
    /**
     * 
     * Default constructor. 
     */
    public ContactsBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public boolean addContact(String username, String username2){
    	User user1 = new User();
    	User user2 = new User();
    	
    	// get user by username
    	try {
			user1 = userEao.getUserByUsername(username);
		} catch (Exception exception) {
			System.out.print("DEBUG: USER1 ID NOT FOUND");
			return false;
		}
    	
    	// get friend by username
    	try {
			user2 = userEao.getUserByUsername(username2);
		} catch (Exception exception) {
			System.out.print("DEBUG: USER2 ID NOT FOUND");
			return false;
		}
    	 	
    	// add contact
    	Contactlist contact = new Contactlist();
    	contact.setUser1(user1);
    	contact.setUser2(user2);
    	try {
  			contactsEao.addContact(contact);
  		} catch (Exception exception) {
  			return false;
  		}
    	return true; 
    }
    
    @Override
   	public boolean deleteContact(String username, String username2){
       	User user1 = new User();
       	User user2 = new User();
       	Contactlist contact = null;
       	
       	// get users by username
       	try {
			user1 = userEao.getUserByUsername(username);
		} catch (Exception exception) {
			System.out.print("DEBUG: USER1 ID NOT FOUND");
			return false;
		}
    	
    	// get friend by username
    	try {
			user2 = userEao.getUserByUsername(username2);
		} catch (Exception exception) {
			System.out.print("DEBUG: USER2 ID NOT FOUND");
			return false;
		}
      	
       	// look for user1 as userid1 and user2 as userid2
       	// if fail look for user2 as userid1 and user1 as userid2
       	// if fail return false
    	try {
      		System.out.print("DEBUG 0");
    		contact = contactsEao.getContact(user1, user2);
    	} catch (Exception e) {
    		System.out.print("DEBUG 1");
    		return false;
    	}
      	
      	
      	// delete contact
      	try{
      		contactsEao.deleteContact(contact);
      	} catch (Exception e3){
      		System.out.println("DEBUG: CANNOT DELETE CONTACT");
      		return false;
      	}
       	
       	// return
       	return true;

       }  

}
