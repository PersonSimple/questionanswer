package com.school.question.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


/**
 * @author Kailash Chandra Dimri
 *
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  
	/**
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
	 * #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 * 
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/super/**").hasRole("SUPER")
			.antMatchers("/admin/**").hasAnyRole("ADMIN","SUPER")
			.antMatchers("/user/**").hasAnyRole("USER","ADMIN","SUPER")
			.antMatchers("/", "/home", "/about").permitAll()
			.and()
			.formLogin();
    }

    
	/**
	 *  you have to implement this callback method loadUserByUsername().  
	 *  userDetailsService will call this 
	 *  method and populate the user information 
	 */
    @Autowired
    UserDetailsService userDetailsService;
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
       }
    
    @Bean public LogoutSuccessHandler logoutSuccessHandler() { 
		  return new CustomLogoutSuccessHandler(); 
		  }
	 
    
    @SuppressWarnings("deprecation")
	@Bean
    public PasswordEncoder getPasswordEncoder() {
    	//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	//return new BCryptPasswordEncoder();
    	return NoOpPasswordEncoder.getInstance();
    	
    }
    
}