package com.spring.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TvSpringOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(TvSpringOauth2Application.class, args);
		
		
	}

	/* ==== Para probar necesitamos un par de registros, como user y admin - Recuerda que las constraseñas son encriptadas ====
	  
    INSERT INTO ROLES (nombre) VALUES('ROLE_ADMIN');
	INSERT INTO ROLES (nombre) VALUES('ROLE_USER');
	INSERT INTO USUARIOS (username, password, enabled, descripcion) VALUES('admin1','$2a$10$GCf2rbx7AQvyQ/SsCvPtBOMYPYTRRw/P7uIGhwwcurQU8Ecar1HMq',1,'Sin descripcion');
    INSERT INTO USUARIOS (username, password, enabled, descripcion) VALUES('user1','$2a$10$TJAbrbbrAX5FSdluDIRBUunomT5q/EkA632n6EVpBspmhH.b2f.n.',1,'Sin descripcion');
    INSERT INTO USUARIOS_ROLES (usuario_id, role_id) VALUES(1,1);
   INSERT INTO USUARIOS_ROLES (usuario_id, role_id) VALUES(2,2);
   
 
Todas estas contraseñas son: 123	 
$2a$10$GCf2rbx7AQvyQ/SsCvPtBOMYPYTRRw/P7uIGhwwcurQU8Ecar1HMq 
$2a$10$TJAbrbbrAX5FSdluDIRBUunomT5q/EkA632n6EVpBspmhH.b2f.n.
$2a$10$8HIah2lUmOFQ/zBnUv1zTeJKxGYSPc1ogil1JHXdSLF/9tqS28alC
$2a$10$MAD6VBLGOV9cVXvZqIsk7.V2SbDNPbjYxhf40rfxt1qpDRbOm1qP2


 * */
	
	
}
