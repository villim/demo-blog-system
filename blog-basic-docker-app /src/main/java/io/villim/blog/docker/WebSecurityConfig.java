package io.villim.blog.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    public static void main(String[] args) {
//        String password = new BCryptPasswordEncoder().encode("admin");
//        System.out.println("password:" + password);
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().access("hasAnyRole('ADMIN')");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ROLE_ADMIN"); // Keep for testing

        // Using BCryptPasswordEncoder, password is [ admin ]
        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$Yi0KGCZlLsRgC9KCiGcJbOFzHox1LocVhWt503BLL2kFke4IJ9kAq").roles("ADMIN")
                .and().passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
