package com.preaceleracion.alkemy.auth.config;

import com.preaceleracion.alkemy.auth.filter.JwtRequestFilter;
import com.preaceleracion.alkemy.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //para habilitar la seguridad de tipo web
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


    private UserDetailsCustomService userDetailsCustomService;

    public SecurityConfiguration(@Autowired @Lazy UserDetailsCustomService userDetailsCustomService) {
        this.userDetailsCustomService = userDetailsCustomService;
    }


    @Autowired
    private JwtRequestFilter jwtRequestFilter;



    @Override //le indica quién es el userdetails, para no usar el q viene x default
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsCustomService);
    }

    @Bean //genera un componente en spring
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();  // aca le decimos que no encripte la clave
    }

    @Override  //manejador de la autenticacion
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override //configurar como se comporta nuestro http Security
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);




    }

}
