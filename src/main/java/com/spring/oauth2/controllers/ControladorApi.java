package com.spring.oauth2.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({"*"})
@RestController
@RequestMapping("api/")
public class ControladorApi {
	
	//-- Variables
	Map<String, Object> respuesta=new HashMap<>();
	
	@GetMapping("saludo-publico")
	public ResponseEntity<?>saludoPublico(){	    
		respuesta.put("saludo", "Hola, este es un saludo publico");
		return new ResponseEntity<Map<String, Object>>(this.respuesta,HttpStatus.OK);
	}

	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("saludo-privado")
	public ResponseEntity<?>saludoPrivado(){
		respuesta.put("saludo", "Hola, este es un saludo privado");
		return new ResponseEntity<Map<String, Object>>(this.respuesta,HttpStatus.OK);
	}
	
	
}
