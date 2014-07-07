package managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.RatedPost;
import entities.Tag;
import entities.User;
import entities.Vote;

@Stateless
@LocalBean
public class VoteManager {
	@PersistenceContext
	EntityManager em;
	
	
	public List<Vote> getVote(User u, RatedPost p){
		
		TypedQuery<Vote> getVote = em.createQuery("SELECT v FROM Vote v WHERE v.voter = :user AND v.post = :post" , Vote.class);
		getVote.setParameter("user", u);
		getVote.setParameter("post", p);
		
		return getVote.getResultList();
	}
	
	public boolean voteExists(User u, RatedPost p){
		
		List<Vote> votes = getVote(u, p);
		
		return !votes.isEmpty();
	}
	
	public List<Vote> getPostVotes(RatedPost p){
		TypedQuery<Vote> getPostVotes = em.createQuery("SELECT v FROM Vote v WHERE v.post = :post" , Vote.class);
		getPostVotes.setParameter("post", p);
		return getPostVotes.getResultList();
	}

}
