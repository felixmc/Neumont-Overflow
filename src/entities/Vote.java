package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Vote extends ManagedEntity
{
	@Column(nullable = false)
	private int value;
	
	@ManyToOne(optional = false)
	private User voter;
	
    @ManyToOne(optional = false)
    private RatedPost post;
	
    public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public User getVoter() {
		return voter;
	}

	public void setVoter(User voter) {
		this.voter = voter;
	}

	public RatedPost getPost() {
		return post;
	}

	public void setPost(RatedPost post) {
		this.post = post;
	}

    
}