package com.spring.oauth2.model.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.oauth2.model.entitys.EntityUsuario;

public interface IServiciosCrudRepository extends CrudRepository<EntityUsuario, Integer> {

	@Query(value="select * from usuarios where username= :username",nativeQuery = true)
	public EntityUsuario buscarUsuarioPorUsername(@Param("username")String username);
}
