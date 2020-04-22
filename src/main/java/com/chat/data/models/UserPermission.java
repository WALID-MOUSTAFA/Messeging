package com.chat.data.models;
import javax.persistence.*;

@Entity
public class UserPermission {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
       
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "ID" )
	private User user;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(referencedColumnName="ID")
	private Permission permission;

	
	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
