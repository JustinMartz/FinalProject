package com.skilldistillery.duality.entities;

import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;   

	private String password;   

	private Boolean enabled;    

	private String role;     

	private String email;    

	private String avatar;     

	@Column(name = "first_name")       
	private String firstName;

	@Column(name = "last_name")        
	private String lastName;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;         

	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updateDate;         

	@Column(name = "date_of_birth")         
	private LocalDate dob;

	@Column(name = "about_me")          
	private String aboutMe;

	@OneToMany(mappedBy = "user")
	private List<BehaviorReport> behaviorReports;    

	@OneToMany(mappedBy = "user")
	private List<BehaviorReportRemark> behaviorReportRemarks;     

	@OneToMany(mappedBy = "creator")
	private List<Resource> resources;     

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "flagged_post", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> flaggedPosts;           

	@OneToMany(mappedBy = "creator")
	private List<Post> posts;

	public User() { 
	} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<BehaviorReport> getBehaviorReports() {
		return behaviorReports;
	}

	public void setBehaviorReports(List<BehaviorReport> behaviorReports) {
		this.behaviorReports = behaviorReports;
	}

	public List<BehaviorReportRemark> getBehaviorReportRemarks() {
		return behaviorReportRemarks;
	}

	public void setBehaviorReportRemarks(List<BehaviorReportRemark> behaviorReportRemarks) {
		this.behaviorReportRemarks = behaviorReportRemarks;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Post> getFlaggedPosts() {
		return flaggedPosts;
	}

	public void setFlaggedPosts(List<Post> flaggedPosts) {
		this.flaggedPosts = flaggedPosts;
	}

	public List<Post> getPosts() {
		return posts;
	} 

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	} 

	public void addFlaggedPost(Post post) {
		if (flaggedPosts == null) {
			flaggedPosts = new ArrayList<>();
		}
		if (!flaggedPosts.contains(post)) {
			flaggedPosts.add(post);
			post.addUserWhoFlagged(this);
		}
	}

	public void removeFlaggedPost(Post post) {
		if (flaggedPosts != null && flaggedPosts.contains(post)) {
			flaggedPosts.remove(post);
			post.removeUserWhoFlagged(this);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", email=" + email + ", avatar=" + avatar + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", createDate=" + createDate + ", updateDate=" + updateDate + ", dob="
				+ dob + ", aboutMe=" + aboutMe + "]";
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
		User other = (User) obj;
		return id == other.id;
	}

}
