package edu.eci.usermicro.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.ws.rs.HttpMethod;

@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true, jsr250Enabled = true, prePostEnabled = true )
public class SecurityConfiguration
        extends WebSecurityConfigurerAdapter
{

    JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration( @Autowired JwtRequestFilter jwtRequestFilter )
    {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


    /*http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers( HttpMethod.GET, "/v1/user" ).permitAll()
                .antMatchers( HttpMethod.POST,"/v1/user" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS );*/

        http.addFilterBefore(jwtRequestFilter, BasicAuthenticationFilter.class)
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/auth").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}