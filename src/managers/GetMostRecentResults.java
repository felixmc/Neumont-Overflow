package managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Answer;
import entities.Comment;
import entities.Question;

@LocalBean
@Stateful
public class GetMostRecentResults {
	
	@PersistenceContext
	EntityManager em;
	
	int questionNcount = 0;
	int answerNCount = 0;
	int commentNCount = 0;


	public GetMostRecentResults()
	{
		
	}
	
	public List<Question> getNextNQuestion(int n, int userId){
		
		List<Question> questions = new ArrayList<>();
		
		TypedQuery<Question> q = em.createNamedQuery("Question.getSome", Question.class);
		System.out.println(userId);
		q.setParameter("userid", userId);
		q.setFirstResult(questionNcount);
		q.setMaxResults(n);
		//questionNcount += n;
		
		questions.addAll(q.getResultList());
			
		  
		return questions;
		
	}

	public List<Answer> getNextNAnswer(int n,int userId){
		
		List<Answer> answers = new ArrayList<>();
		TypedQuery<Answer> a = em.createNamedQuery("Answer.getSome", Answer.class);
		System.out.println(userId);
		a.setParameter("userid", userId);
		a.setFirstResult(answerNCount);
		a.setMaxResults(n);
		//answerNCount += n;
		
		answers.addAll(a.getResultList());
		
		return answers;
		
	}
	
	
}
