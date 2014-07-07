package entities;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.*;

import managers.VoteManager;

@Entity
public abstract class RatedPost extends Post {
	
	@OneToMany(mappedBy="post")
	private List<Vote> votes;
	
	@Column(nullable = false)
	private int score;
	
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
		getScore();
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore(){
		List<Vote> vote = this.getVotes();
		
		int tempScore = 0;
		
		for(Vote v : vote){
			tempScore += v.getValue();
		}
		
		this.setScore(tempScore);
		
		return score;
	}

	public int useresVote(int id){
		int vote = 0;
		
		for(Vote v: votes){
			if(v.getVoter().getId() == id){
				vote = v.getValue();
			}
		}
		
		return vote;
	}
}
