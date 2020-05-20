package com.dp.spring.learning.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.annotation.PostConstruct;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
public class WebSecurityConfig1 extends CorsSecurityConfig{

    private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter;
    private AuthenticationProvider authenticationProvider;

    public WebSecurityConfig1(){

    }

    @PostConstruct
    protected void initProcessingFilter() {
        preAuthenticatedProcessingFilter =new Filter1();
        try{
            preAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManager());
        }
        catch (Exception e){
            throw new IllegalStateException("can not set authentication manager", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.antMatcher("/api/first/**").cors().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
        http.addFilterBefore(this.preAuthenticatedProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/api/third/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider);
    }

    @Autowired
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
    }
}
