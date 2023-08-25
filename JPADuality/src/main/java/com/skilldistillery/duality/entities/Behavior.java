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
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Behavior {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	private Integer severity;
	
	@OneToOne
	@JoinColumn(name="behavior_type_id")
	private BehaviorType behaviorType;


	public Behavior() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getSeverity() {
		return severity;
	}


	public void setSeverity(Integer severity) {
		this.severity = severity;
	}


	

	public BehaviorType getBehaviorType() {
		return behaviorType;
	}


	public void setBehaviorType(BehaviorType behaviorType) {
		this.behaviorType = behaviorType;
	}


	@Override
	public String toString() {
		return "Behavior [id=" + id + ", name=" + name + ", severity=" + severity + ", behaviorType=" + behaviorType
				+ "]";
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
		Behavior other = (Behavior) obj;
		return id == other.id;
	}



}
