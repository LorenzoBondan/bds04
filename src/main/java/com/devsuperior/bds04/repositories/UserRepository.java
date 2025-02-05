package com.devsuperior.bds04.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.bds04.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	// METODO QUE BUSCA NO BANCO UM USUARIO POR EMAIL
	User findByEmail(String email);
}
