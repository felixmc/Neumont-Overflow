package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Flag extends ManagedEntity
{
	public enum FlagType { Spam, AcademicMisconduct, OffTopic, Offensive }
	
	@Column(nullable = false)	
	private FlagType type;
	
	@ManyToOne(optional = false)
	private Post post;
	
	@ManyToOne(optional = false)
	private User flaggingUser;
	
	@Column(nullable = false, name="RESOLVED")
	private boolean isResolved;
	public FlagType getType() {
		return type;
	}

	
	public boolean isResolved() {
		return isResolved;
	}


	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}


	public void setType(FlagType type) {
		this.type = type;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getFlaggingUser() {
		return flaggingUser;
	}

	public void setFlaggingUser(User flaggingUser) {
		this.flaggingUser = flaggingUser;
	}

}