package com.app.projectdelivery.security;

import com.app.projectdelivery.services.DetailUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfig extends WebSecurityConfigurerAdapter
{
    private final DetailUserServiceImpl userService;
    private PasswordEncoder passwordEncoder;

    public JWTConfig( DetailUserServiceImpl userService,  PasswordEncoder passwordEncoder )
    {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.userDetailsService( userService ).passwordEncoder( passwordEncoder );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/swagger-ui/**", "/swagger-resources/**", "/v2/**", "/webjars/**" ).permitAll()
                .antMatchers( HttpMethod.POST, "/api/**" ).permitAll()
                .antMatchers( HttpMethod.GET, "/api/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration( "/**", corsConfiguration );

        return source;
    }
}
