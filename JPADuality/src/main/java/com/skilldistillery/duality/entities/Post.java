package com.skilldistillery.duality.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User creator;

	private Boolean active;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	private String message;

	private Boolean anonymous;

	private Boolean personal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "post")
	private List<Comment> comments;
	
	@ManyToMany(mappedBy = "flaggedPosts")
	private List<User> usersWhoFlagged;

	public Post() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Boolean getPersonal() {
		return personal;
	}

	public void setPersonal(Boolean personal) {
		this.personal = personal;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getUsersWhoFlagged() {
		return usersWhoFlagged;
	}

	public void setUsersWhoFlagged(List<User> usersWhoFlagged) {
		this.usersWhoFlagged = usersWhoFlagged;
	}

	public void addUserWhoFlagged(User User) {
		if (usersWhoFlagged == null) {
			usersWhoFlagged = new ArrayList<>();
		}
		if (!usersWhoFlagged.contains(User)) {
			usersWhoFlagged.add(User);
			User.addFlaggedPost(this);
		}
	}

	public void removeUserWhoFlagged(User User) {
		if (usersWhoFlagged != null && usersWhoFlagged.contains(User)) {
			usersWhoFlagged.remove(User);
			User.removeFlaggedPost(this);
		}
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", creator=" + creator + ", active=" + active + ", createDate="
				+ createDate + ", message=" + message + ", anonymous=" + anonymous + ", personal=" + personal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	} 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return id == other.id;
	}

}
