package com.school.question.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

//@Configuration
/**
 * @author a123456
 *
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  //  @Autowired
 //   private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
	
	
    /*  commenting this 
     * 
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.csrf().disable() .authorizeRequests() .antMatchers("/", "/home",
		 * "/about").permitAll() .antMatchers("/admin/**").hasAnyRole("ADMIN")
		 * .antMatchers("/user/**").hasAnyRole("USER") .anyRequest().authenticated()
		 * .and() .formLogin() .loginPage("/login") .permitAll() .and() .logout()
		 * .logoutUrl("/perform_logout") .deleteCookies("JSESSIONID")
		 * .logoutSuccessHandler(logoutSuccessHandler());
		 * 
		 * //.logout() // .permitAll() // .and() //
		 * .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		 */
        
        
        http.csrf().disable()
        	.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/", "/home", "/about").permitAll()
			.and()
			.formLogin();
    }

    
	
	
	
	
    
// create two users, admin and user
	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * 
	 * auth.inMemoryAuthentication()
	 * .withUser("user").password("password").roles("USER") .and()
	 * .withUser("admin").password("password").roles("ADMIN"); }
	 */
  
    
    /**
     * this is JDBC authenticaton
     */
    
    
    
    @Autowired
    UserDetailsService userDetailsService;
       
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
       }
    
   
 /**
  *  this is inmemory authentication   
  
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
       {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
            .withUser("user").password("password").roles("USER")
            .and()
            .withUser("admin").password("password").roles("ADMIN");
            
    }
    */
    
    
  
    
	/*
	 * @Autowired DataSource datasource;
	 */
    
	
	  @Bean public LogoutSuccessHandler logoutSuccessHandler() { 
		  return new CustomLogoutSuccessHandler(); 
		  }
	 
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
    	//return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	return NoOpPasswordEncoder.getInstance();
    	
    }
    
}