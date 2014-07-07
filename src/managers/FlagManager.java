package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Flag;

@Stateless
@LocalBean
public class FlagManager {

	@PersistenceContext
	EntityManager em;
	
	public List<Flag> getAllFlags(){
		
		TypedQuery<Flag> query = em.createQuery("SELECT f FROM Flag f", Flag.class);
		
		return query.getResultList();
	}

	public List<Flag> getAllUnresolvedFlags() {
		TypedQuery<Flag> query = em.createQuery("SELECT f FROM Flag f WHERE f.isResolved ='0' ", Flag.class);
		return query.getResultList();
	}
}
