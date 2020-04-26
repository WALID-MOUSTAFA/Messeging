package com.chat.data.models;

import java.util.Collection;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission{


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	

	@OneToMany(mappedBy = "permission" , fetch = FetchType.LAZY)
	@JsonIgnore
	private Collection<UserPermission> users;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

       
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Collection<UserPermission> getUsers() {
		return users;
	}


	public void setUsers(Collection<UserPermission> users) {
		this.users = users;
	}
} 
