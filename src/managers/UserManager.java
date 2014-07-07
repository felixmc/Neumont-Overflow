package managers;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;






@Stateless
@LocalBean
public class UserManager {

	@PersistenceContext
	EntityManager em;
	
	public User getUser(int id)
	{
		return em.find(User.class, id);
	}
	
	public User getUser(String email)
	{

		
		TypedQuery<User> getUserFromName = em.createQuery("SELECT u FROM User u Where u.email = :email", User.class);
		getUserFromName.setParameter("email", email);

		
		User returnUser = null;
		List<User> userList = getUserFromName.getResultList();
		
		if(userList.size() < 2 && userList.size() > 0)
		{
			for (User user : userList) {
				returnUser = user;
			}
		}
		
		return returnUser;
				
	}
	
	public int getUserId(String email)
	{
		User user = getUser(email);
		
		return user.getId();
		
		
	}
}
