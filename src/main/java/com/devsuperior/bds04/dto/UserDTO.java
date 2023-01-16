package com.devsuperior.bds04.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;

import com.devsuperior.bds04.entities.Role;
import com.devsuperior.bds04.entities.User;


public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Email(message = "Favor entrar com um email válido")
	private String email;
	
	private List<RoleDTO> roles = new ArrayList<>();
	  
	public UserDTO() {}

	public UserDTO(Long id, @Email(message = "Favor entrar com um email válido") String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	// construtor implantado na classe UserService
		public UserDTO(User entity) {
			this.id = entity.getId();
			this.email = entity.getEmail();

			entity.getRoles().forEach(rol -> this.roles.add(new RoleDTO(rol)));
		}


		public UserDTO(User entity, Set<Role> roles) {
			this(entity); // EXECUTA TUDO DO METODO DE CIMA
			roles.forEach(rol -> this.roles.add(new RoleDTO(rol))); // PEGA CADA ELEMENTO E INSERE ELE TRANSFORMADO PARA DTO NA LISTA DE CATEGORIES DA CLASSE
		}
}
