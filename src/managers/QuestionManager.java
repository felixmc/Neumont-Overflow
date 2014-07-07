package managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.ManagedEntity;
import entities.Question;
import entities.Tag;

@Stateless
@LocalBean
public class QuestionManager
{
	@PersistenceContext
	EntityManager em;
	
	public enum Param
	{
		open("q.isOpen = 1"),
		unanswered("q.answers IS EMPTY"),
		notDeleted("q.isDeleted = 0"),
		byScore(" ORDER BY q.score DESC");
		
		final String query;
		
		Param (String query)
		{
			this.query = query;
		}
	}
	
	public List<Question> getLatest (int count)
	{
		return getLatest(count, new ArrayList());
	}
	
	public List<Question> getLatest (int count, List<Param> params)
	{
		return getLatest (count, params, null);
	}
	
	public List<Question> getLatest (int count, List<Param> params, Integer tagId)
	{
		params.add(Param.notDeleted);
		
		String query = "SELECT q FROM Question q WHERE";
		
		if (tagId != null)
		{
			query += " :tag MEMBER OF q.tags AND";
		}
		
		String ending;
		
		if (params.contains(Param.byScore) )
		{
			ending = Param.byScore.query;
			params.remove(Param.byScore);
		}
		else
			ending = " ORDER BY q.dateCreated DESC";
		
		for (int i = 0; i < params.size(); i++)
		{
				query += ( i == 0 ? " " : " AND ") + params.get(i).query;
		}
		
		query += ending;
		
		TypedQuery<Question> getQuestions = em.createQuery(query, Question.class);
		
		if (tagId != null)
			getQuestions.setParameter("tag", em.find(Tag.class, tagId));
		
		
		getQuestions.setMaxResults(count);
		
		return getQuestions.getResultList();
	}
	
}