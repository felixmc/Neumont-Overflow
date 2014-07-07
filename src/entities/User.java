package entities;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import edu.neumont.csc280.crypto.DigestAlgorithm;
import edu.neumont.csc280.crypto.PasswordDigest;

@Entity
public class User extends ManagedEntity {
	@OneToMany(mappedBy = "author")
	private List<Question> questions;

	@OneToMany(mappedBy = "author")
	private List<Answer> answers;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private List<enums.Role> roles;

	@OneToMany(mappedBy = "voter")
	private List<Vote> votes;

	@OneToMany(mappedBy = "flaggingUser")
	private List<Flag> flags;

	@Column(nullable = false)
	private int userScore;

	@Column(nullable = false, length = 255, unique = true)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 255)
	private String salt;

	@Column(nullable = false)
	private boolean isBanned;

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<enums.Role> getRoles() {
		if (this.roles == null)
			this.roles = new Vector<enums.Role>();
		return roles;
	}

	public void addRole(enums.Role role) {
		this.roles.add(role);
	}

	public void setRoles(List<enums.Role> groups) {
		this.roles = groups;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public int getUserScore() {
		int userTempScore = 0;
		List<Answer> tempAnswerList = this.getAnswers();
		List<Question> tempQuestionList = this.getQuestions();
		for (Answer answer : tempAnswerList) {
			userTempScore += answer.getScore();
		}
		for (Question question : tempQuestionList) {

			userTempScore += question.getScore();
		}

		userScore = userTempScore;
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.email.split("@")[0];
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public void setNewPassword(String clearPassword) {
		edu.neumont.csc280.crypto.PasswordDigest digest;
		digest = new PasswordDigest(clearPassword, 32,
				DigestAlgorithm.SHA256.algorithm);
		this.setPassword(digest.getSaltedDigest());
		this.setSalt(digest.getSalt());
	}
}