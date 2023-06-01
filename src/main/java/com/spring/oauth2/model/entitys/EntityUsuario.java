package com.spring.oauth2.model.entitys;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "usuarios")
public class EntityUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="El usuario/correo no puede estar vacío")
	private String username;
	
	private String password;
	private Boolean enabled;
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="usuarios_roles", //-- Nombre de la tabla 
	joinColumns = @JoinColumn(name="usuario_id"),inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})}) //Para que no se repitan
	private List<EntityRol> roles;

	
	
	//-- Getters and setters

	public Integer getId() {return id;	}
	public void setId(Integer id) {	this.id = id;	}
	public String getUsername() {return username;}
	public void setUsername(String username) {	this.username = username;	}
	public String getPassword() {	return password;}
	public void setPassword(String password) {	this.password = password;}
	public Boolean getEnabled() {return enabled;}
	public void setEnabled(Boolean enabled) {this.enabled = enabled;	}
	public String getDescripcion() {return descripcion;	}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;	}
	public List<EntityRol> getRoles() {	return roles;}
	public void setRoles(List<EntityRol> roles) {	this.roles = roles;	}
	
	//-- Constructores
	
	
	public EntityUsuario() {
	}
	public EntityUsuario(Integer id, @NotEmpty(message = "El usuario/correo no puede estar vacío") String username,
			String password, Boolean enabled, String descripcion, List<EntityRol> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.descripcion = descripcion;
		this.roles = roles;
	}
	
	
	
	
	
}
