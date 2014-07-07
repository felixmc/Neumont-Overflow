package entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity

public class Comment extends Post {
	
	@ManyToOne(optional= false)
	private Answer post;

	public Answer getPost() {
		return post;
	}

	public void setPost(Answer post) {
		this.post = post;
	}
}
