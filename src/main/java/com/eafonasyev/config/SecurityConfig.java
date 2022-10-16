package com.eafonasyev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add our users for in memory authentication

        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/").hasAnyRole("EMPLOYEE").
                antMatchers("/leader").hasAnyRole("MANAGER").
                antMatchers("/admin").hasAnyRole("ADMIN").
                anyRequest().authenticated().
                and().formLogin().loginPage("/showMyLoginForm").
                loginProcessingUrl("/authenticateTheUser").
                permitAll().
                and().logout().
                permitAll().and().exceptionHandling().accessDeniedPage("/access-denied");
    }
}
