package com.chat.data.models;

import javax.persistence.*;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.lang.Nullable;

import com.chat.data.models.User;


@Entity
@Table(name="user_message")
public class UserDBMessage {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="ID")
	private DBMessage message;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="ID")
	private User sender;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="ID")
	private User target;

		public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DBMessage getMessage() {
		return message;
	}

	public void setMessage(DBMessage message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}


}
