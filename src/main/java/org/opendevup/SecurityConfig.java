package org.opendevup;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true) //protect method
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	//method de configuration global
	public void globalConfig(AuthenticationManagerBuilder auth,DataSource datasource) throws Exception
	{
		//mode d'acces soit en jdbc, in memory ,...
		
		//mode in memory
		/*auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");*/
		
		//jdbc
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery("select username, password, true from users where username= ?")
			.authoritiesByUsernameQuery("select user_username, roles_role from users_roles where user_username=?")
			/*.usersByUsernameQuery("select username as principal, password a credentials, true from users where username= ?")
			.authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username=?")*/
			.rolePrefix("ROLE_");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //on demande à spring de ne pas utiliser le jeton d'authentification 
			.authorizeRequests()
				.antMatchers("/css/**","/js/**","/images/**").permitAll()//ressouce autorisé sans passé par authentification
				.anyRequest()
					.authenticated()
						.and()
			.formLogin()
				.loginPage("/login")//url générer coté serveur , redirection vers /login par spring security
				.permitAll()
			.defaultSuccessUrl("/index.html")//apr succé authentification
			.and()
		.logout()
			.invalidateHttpSession(true)
			.logoutUrl("/logout")
			.permitAll();
		
	}
}
