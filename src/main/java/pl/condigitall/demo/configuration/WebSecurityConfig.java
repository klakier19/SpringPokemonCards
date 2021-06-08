package pl.condigitall.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.condigitall.demo.service.LoginService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private LoginService loginService;

    public WebSecurityConfig(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/console").permitAll()
                .antMatchers("/register").permitAll()
//              .antMatchers(HttpMethod.POST, "/register").permitAll()
//              .antMatchers(HttpMethod.GET, "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService);
    }
}
