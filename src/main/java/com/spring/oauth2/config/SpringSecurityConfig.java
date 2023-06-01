package com.spring.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.oauth2.model.services.ServiciosUsuario;

@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	
	//=== Intección de servicios
	@Autowired
	private ServiciosUsuario servicioUsuarios;
	
	
	//=== Metodo: Creamos un metodo para encriptar nuestras contraseñas
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
	//----------- Los metodos posteriores los sobreescribiremos y configuramos -----------
	
	//===Metodo: Encriptamos la contraseña
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.servicioUsuarios).passwordEncoder(passwordEncoder());
	}

	//===Metodo: Practicamente este lo deje como esta, solo añadi el Bean
	@Override
	@Bean(name = "authenticationManager")
	protected AuthenticationManager authenticationManager() throws Exception {	
		return super.authenticationManager();
	}

	
	// == Metodo:Le dice a Spring que vamos a trabajar con tokens y no con sessiones
		// Esta configuración no es necesario cuando tenemos en Frontend Incluido
		// Tambine ayuda a proteger nuestro back contra ataques
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//-- Indica que cualquier otra pagina no señalada arriba es privada
	}
	
	
	
	
}
