package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public abstract class Post extends ManagedEntity {
	
	public enum DeleteReason { Spam, AcademicMisconduct, OffTopic, Offensive }
	
	@JoinColumn(nullable = false)
	@ManyToOne(optional=false)
	private User author;
	
	@Column(nullable = false)
	@Lob
	private String content;

	@Column(nullable = false)
	private boolean isDeleted;
	
	@OneToMany(mappedBy = "post")
	private List<Flag> flags;
	
	@Enumerated(EnumType.STRING)
	public DeleteReason deleteReason;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public DeleteReason getDeleteReason() {
		return deleteReason;
	}
	
	public void setDeleteReason(DeleteReason deleteReason) {
		this.deleteReason = deleteReason;
	}
}
