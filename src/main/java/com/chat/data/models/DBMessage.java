package com.chat.data.models;

import javax.persistence.*;

import java.util.List;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.lang.Nullable;

import com.chat.data.models.User;

@Entity
@Table(name="message")
public class DBMessage {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String body;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;


	@OneToMany(mappedBy="message")
	List<UserDBMessage> userDBMessages;
	
	public void setId(Long id){
		this.id= id;
	}

	public Long getId() {
		return this.id;
	}

	public void setBody(String body) {
		this.body= body;
	}

	public String getBody() {  
		return this.body;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt= createdAt;
	}

	public Date getCreatedAt (){
		return this.createdAt;
	}


	
	
	
}

