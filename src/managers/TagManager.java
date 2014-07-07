package managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.ManagedEntity;
import entities.Tag;

@Stateless
@LocalBean
public class TagManager {

	@PersistenceContext
	EntityManager em;
	
	public List<Tag> getTagName(String s){
		TypedQuery<Tag> getTag = em.createQuery("SELECT t FROM Tag t WHERE t.name = :name" , Tag.class);
		getTag.setParameter("name", s);
		return getTag.getResultList();
		
	}
	
	public boolean tagExists(Tag t){
		
		List<Tag> tags = (List<Tag>) getTagName(t.getName());
		
		return !tags.isEmpty();
	}
	
	public Tag getTagNamed(String s){
		List<Tag> tags = (List<Tag>) getTagName(s);
		
		Tag tag = tags.get(0);
		
		return tag;
	}
}
