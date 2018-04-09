package web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}adminpass")
		.roles("ADMIN", "USER")
		.and()
		.withUser("student")
		.password("{noop}studentpass")
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/predavanja/**")
		.hasRole("USER")
		.antMatchers("/**").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/predavanja/novo", true)
		.and()
		.logout()
		.logoutSuccessUrl("/login");
	}
}
