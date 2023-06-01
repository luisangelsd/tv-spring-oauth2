package com.spring.oauth2.config;



import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	//== Esta nos sirve para traernos datos del properties
	@Autowired
	private Environment env;
	
	//=== Creamos un passwordEncoder
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//-- Intectar: Metodo "authenticationManager" en la clase "SpringSecurityConfig"
	@Autowired
	@Qualifier(value = "authenticationManager")
	private AuthenticationManager authenticationManager;


	
	//--------- Implementamos los siguientes metodos de la clase "AuthorizationServerConfigurerAdapter"-------------

	//-- Primer metodo: Aquí configuramos quien puede solicitar el acceso para generar un token
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")//-- end pont para generar el token (oauth/token)
		.checkTokenAccess("isAuthenticated()");//-- Valida el tocken
		
	}


	//-- Segundo metodo: Configura el acceso a los clientes que tendras acceso, usuario y contraseña
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(env.getProperty("config.security.oauth.client.id"))
		.secret(  passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
		.scopes("read","write")
		.authorizedGrantTypes("password","refresh_token")//-- Aun. por password y refrescaremos el token
		.accessTokenValiditySeconds(3600)				//-- El tocken es valido por 1 hora
		.refreshTokenValiditySeconds(3600);				//-- Refresca el toquen cada 1 hora
	} 


	//-- Tercer metodo: Esta configuración es para añadir  información a añadir al token
	//-- Aquí tambien puedes añadir información al token (en este proyecto no lo hize)
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter());
	}
	
	//======== Implementamos los siguientes metodos ========================="

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore( accessTokenConverter());
	}
	
	
	@Bean //-- Este nos sirve para añadir alguna firma
	public JwtAccessTokenConverter accessTokenConverter(){
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(Base64.getEncoder().encodeToString(env.getProperty("config.security.oauth.jwt.key").getBytes()));					//-- Añadimos la firma de nuestro token
		//tokenConverter.setSigningKey(OpenSslConfig.privateKey);
		//tokenConverter.setVerifierKey(OpenSslConfig.publicKey);
		return tokenConverter;
	}
	
	
	
}
