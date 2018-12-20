package ch.supsi.webapp.web.configuration;

import ch.supsi.webapp.web.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebAuthenticationConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/filter/**").permitAll()
                .antMatchers("/blog/new").authenticated()
                .antMatchers("/blog/*/delete").hasRole("ADMIN")
                .antMatchers("/blog/*/edit").authenticated()
                .antMatchers("/blog/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/blogpost/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authenticationService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(authProvider);
    }
}