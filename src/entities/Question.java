package entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "Question.getSome", query = "SELECT q FROM User u, IN(u.questions) q WHERE u.id = :userid ORDER BY q.dateCreated")
public class Question extends RatedPost {
	@ManyToMany
	// TODO map to tags
	private List<Tag> tags;

	@Column(length = 255, nullable = false)
	private String title;

	private boolean isOpen;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public List<Answer> getAnswers() {
		Collections.sort(answers, new Comparator<Answer>()
		{
			@Override
			public int compare(Answer a1, Answer a2) {
				int result = 0;
				
				if (a1.isBestAnswer())
					result = -1;
				else if (a2.isBestAnswer())
					result = 1;
				else
					result = a2.getScore() - a1.getScore();
					
				return result;
			}
		});
		
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}