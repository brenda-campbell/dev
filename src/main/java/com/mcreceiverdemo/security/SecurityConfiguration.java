package com.mcreceiverdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    //@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customAuthenticationProvider);
    }

 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http
         .csrf().disable()
         .headers().disable()
         //.and()
         .authorizeRequests()
         .antMatchers("/assets/**", "/js/**", "/css/**", "/img/**", "/favicon.ico", "/").permitAll()
         //.antMatchers("/index.jsp", "/index.html", "/index").hasRole("USER")
         .antMatchers("/de", "/qa/**", "/folder/**").hasRole("ADMIN")
         .and()
         //.formLogin().loginProcessingUrl("/processLogin").loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
         //.and()
         .exceptionHandling().accessDeniedPage("/")
         .and()
         .logout().permitAll();
    }
    
}
