package com.alebarre.todosimples.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
	
	//Criadas para garantir que seja verificadas o que esta na anotacao de cada atributo
	public interface CreateUser{}
	public interface UpdateUser{}
	
	public static final String TABLE_NAME = "user"; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "username", length = 100, nullable = false, unique = true)
	@NotNull(groups = CreateUser.class)
	@NotEmpty(groups = CreateUser.class)
	@Size(groups = CreateUser.class, min = 2, max = 100)
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)//Só aceita método de escrita pra senha e não retorna o que foi escrito para o usuario
	@NotNull(groups = {CreateUser.class, UpdateUser.class})
	@NotEmpty(groups = {CreateUser.class, UpdateUser.class})
	@Column(name = "password", length = 60, nullable = false)
	@Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
	private String password;
	
	
	//private List<Task> tasks = new ArrayList<Task>();
	

	public User() {
		super();
	}
	
	public User(Long id,
			@NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String username,
			@NotNull(groups = { CreateUser.class, UpdateUser.class }) @NotEmpty(groups = { CreateUser.class,
					UpdateUser.class }) @Size(groups = { CreateUser.class,
							UpdateUser.class }, min = 8, max = 60) String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	//EQUALS e HASHCODE estão personalizados para esse projeto
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (!(obj instanceof User))
			return false;
		
		if (obj == null)
			return false;
		
		User other = (User) obj;
		
		return Objects.equals(this.id,  other.id) 
				&& Objects.equals(this.username, other.username) 
				&& Objects.equals(this.password, other.password);
	}

	@Override
	public int hashCode() {
		final int prime = 31; //numero aleatorio
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

}