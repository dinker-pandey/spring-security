package com.dp.spring.learning.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig2 extends CorsSecurityConfig{


    private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter2;
    private AuthenticationProvider authenticationProvider;

    public WebSecurityConfig2(){

    }

    @Configuration
    @Order(2)
    public static class WebSecurityConfig12 extends CorsSecurityConfig{

        private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter1;

        @PostConstruct
        protected void initProcessingFilter() {
            preAuthenticatedProcessingFilter1 =new Filter1();
            try{
                log.info("inside first ");
                preAuthenticatedProcessingFilter1.setAuthenticationManager(authenticationManager());
            }
            catch (Exception e){
                throw new IllegalStateException("can not set authentication manager", e);
            }
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.antMatcher("/api/**").cors().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
            http.addFilterBefore(this.preAuthenticatedProcessingFilter1, UsernamePasswordAuthenticationFilter.class);
        }

        /*@Override
        public void configure(WebSecurity web) throws Exception{
            web.ignoring().antMatchers("/api/third/**");
        }*/
    }


    @Configuration
    @Order(1)
    public static class WebSecurityConfig11 extends CorsSecurityConfig{


        private AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter2;

        @PostConstruct
        protected void initProcessingFilter() {
            log.info("inside second ");
            preAuthenticatedProcessingFilter2 =new Filter2();
            try{
                preAuthenticatedProcessingFilter2.setAuthenticationManager(authenticationManager());
            }
            catch (Exception e){
                throw new IllegalStateException("can not set authentication manager", e);
            }
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.antMatcher("/api/third/**").cors().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
            http.addFilterBefore(this.preAuthenticatedProcessingFilter2, UsernamePasswordAuthenticationFilter.class);
        }
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
