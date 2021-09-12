package com.example.webproductspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, status " +
                        "from users where email = ? or username = ? and block = 0")
                .authoritiesByUsernameQuery("select username, role " +
                        "from users where email = ? or username = ?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // bỏ qua security đối với link /api/
//        http.csrf().ignoringAntMatchers("/api/users");
//        http.csrf().disable();
//        http.cors().disable();

//        http.cors().disable().csrf().disable();

        // Chỉ cho phép user đã đăng nhập mới được truy cập đường dẫn /admin/**
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        // Cấu hình cho Login Form.
        http.formLogin()
                .loginProcessingUrl("/j_spring_security_login")
                .loginPage("/login")
                .defaultSuccessUrl("/index.html")
                .failureHandler(customAuthenticationFailureHandler)
                .usernameParameter("email")
                .passwordParameter("password");

        // Cấu hình cho Logout Page.
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/login?message=logout");

        // Cấu hình concurrent session
        http.sessionManagement().sessionFixation().newSession()
                .invalidSessionUrl("/login?message=timeout")
                .maximumSessions(1).expiredUrl("/login?message=max_session")
                .maxSessionsPreventsLogin(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/resources/**"
                , "/static/**"
                , "/admin/css/**"
                , "/admin/js/**"
                , "/admin/image/**"
                , "/web/css/**"
                , "/web/js/**"
                , "/web/image/**"
                , "/web/files/**"
                , "/api/**"
        );
    }

}
