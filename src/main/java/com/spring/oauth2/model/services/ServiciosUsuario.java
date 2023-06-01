package com.spring.oauth2.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.oauth2.model.entitys.EntityUsuario;

@Service("serviciosUsuario")
public class ServiciosUsuario implements  UserDetailsService {

	
	 //-- Intección de servicios
	@Autowired
	private IServiciosCrudRepository servicioCrudRepository;
	
	//======== Metodos implementados por la implementación de la clase "UserDetailsService"
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		EntityUsuario entityUsuario=this.servicioCrudRepository.buscarUsuarioPorUsername(username);
		
		//-- Validar que exista el usuario
		if (entityUsuario==null) {
			throw new UsernameNotFoundException("El usuario no existe");
		}
		
		//-- Obtenemos los roles y los transformamos en un GrantedAuthority para poder añadirlo al objeto "UserDetails" que debemos devolver
		List<GrantedAuthority> authorities= entityUsuario.getRoles()
				.stream()
				.map( rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.collect(Collectors.toList());
		
		
		//-- Devolvemos nuestro usuario	
		return new User(entityUsuario.getUsername(),entityUsuario.getPassword(), entityUsuario.getEnabled(), true, true,true, authorities);
	}
	



	
	

}
