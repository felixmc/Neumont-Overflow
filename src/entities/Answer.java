package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Answer.getSome", query = "SELECT a FROM User u, IN(u.answers) a WHERE u.id = :userid ORDER BY a.dateCreated")
public class Answer extends RatedPost {
	
	@ManyToOne(optional = false)
	private Question question;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	private boolean isBestAnswer;
	
	public Answer(){}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isBestAnswer() {
		return isBestAnswer;
	}

	public void setBestAnswer(boolean isBestAnswer) {
		this.isBestAnswer = isBestAnswer;
	}
	
	
	
}
