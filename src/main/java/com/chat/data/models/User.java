package com.chat.data.models;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;


	@Column
	private String username;

	@Column
	private String fullname;

	@Column
	private String password;

	@Null
	@OneToMany(mappedBy= "user", fetch = FetchType.LAZY)
	private Collection<UserPermission> userPermissions;
	
	public void setId(Long id){
		this.id = id;
	}


	public Long getId(){
		return this.id;
	}

	public void  setUsername(String username){
		this.username= username;
	}
	public String  getUsername(){
		return this.username;
	}
	
	public void setFullname(String fullname){
		this.fullname= fullname;
	}

	public String getFullname(){
		return this.fullname;
	}

	public void setPassword(String password){
		this.password= password;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setUserPermissions(Collection<UserPermission> userPermissions){
		this.userPermissions= userPermissions;
	}

	public Collection<UserPermission> getUserPermissions(){
		return this.userPermissions;
	} 
}
