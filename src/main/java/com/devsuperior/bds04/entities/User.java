package com.devsuperior.bds04.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) // ELE NÃO DEIXA COLOCAR O MESMO EMAIL PRA MAIS DE UM USUÁRIO
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER) // SEMPRE QUE CARREGAR, JÁ VAI TRAZER JUNTO A LISTA DE PERFIS DESTE USUÁRIO
	@JoinTable(name = "tb_user_role",
				joinColumns = @JoinColumn(name = "user_id"), // CLASSE ONDE EU ESTOU
				inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();
	
	public User() {
	}
	
	public User(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return Objects.equals(id, other.id);
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	/// ---------- METODOS GERADOS PELO IMPLEMENTS USERDETAILS
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// PERCORRER CADA ELEMENTO DA LISTA DE ROLES E CONVERTER ELE PARA GRANTEDAUTHOTIRY
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	// SE QUISESSE, DARIA PRA IMPLEMENTAR ALGUMA LÓGICA PRA VALIDAR ESSAS OPÇÕES ABAIXO

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// ---------------------------------------
}
