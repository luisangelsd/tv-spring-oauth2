package com.spring.oauth2.model.entitys;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class EntityRol implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty( message = "El usuario/email no puede estar vació")
	@Column(unique = true)
	private String nombre;


	
	//-- Getters and setters
	public Integer getId() {return id;}
	public void setId(Integer id) {	this.id = id;}
	public String getNombre() {	return nombre;	}
	public void setNombre(String nombre) {	this.nombre = nombre;}
	
	
	//-- Constructores
	
	
	public EntityRol() {
		
	}
	
	public EntityRol(Integer id, @NotEmpty(message = "El usuario/email no puede estar vació") String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	

}
