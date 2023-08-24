package com.skilldistillery.duality.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="direct_message")
public class DirectMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String message;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	private Boolean active;
	
	@OneToOne
	@JoinColumn(name="sender_id")
	private User sender;
	
	@OneToOne
	@JoinColumn(name="recipient_id")
	private User recipient;

	public DirectMessage() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) { 
		this.recipient = recipient;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DirectMessage [id=" + id + ", message=" + message + ", createDate=" + createDate + ", active=" + active
				+ ", sender=" + sender + ", recipient=" + recipient + "]";
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
		DirectMessage other = (DirectMessage) obj;
		return id == other.id;
	}

	
	
	
}
