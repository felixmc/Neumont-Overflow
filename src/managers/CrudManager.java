package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.ManagedEntity;
import entities.Question;

@Stateless
@LocalBean
public class CrudManager
{
	@PersistenceContext
	EntityManager em;

	public List<ManagedEntity> getAll(Class entityType)
	{
		TypedQuery<ManagedEntity> getEntities = em.createQuery("SELECT e FROM " + entityType.getSimpleName()  + " e", entityType);
		return getEntities.getResultList();
	}

	public ManagedEntity get(int id, Class entityType)
	{
		return em.find(entityType, id);
	}

	public List<ManagedEntity> getTop (Class entityType, int count)
	{
		TypedQuery<ManagedEntity> getEntities = em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType);
		
		getEntities.setMaxResults(count);
		
		return getEntities.getResultList();
	}
	
	public List<ManagedEntity> getLatest (Class entityType)
	{
		return getLatest(entityType, 20);
	}
	
	public List<ManagedEntity> getLatest (Class entityType, int count)
	{
		TypedQuery<ManagedEntity> getEntities = em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e ORDER BY e.dateCreated", entityType);
		
		getEntities.setMaxResults(count);
		
		return getEntities.getResultList();
	}
	
	public ManagedEntity create(ManagedEntity e)
	{
		em.persist(e);
		return e;
	}

	public ManagedEntity update(ManagedEntity e)
	{
		em.merge(e);
		return e;
	}

	public void delete (ManagedEntity entity)
	{
		this.delete(entity.getId(), entity.getClass());
	}
	
	public void delete(int id, Class entityType)
	{
		ManagedEntity entity = get(id, entityType);
		em.remove(entity);
	}

}