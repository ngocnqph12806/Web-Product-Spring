package com.example.webproductspringboot.config;

import com.example.webproductspringboot.utils.ContainsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, status " +
//                        "from users where username = ? and status = 1")
//                .authoritiesByUsernameQuery("select username, role " +
//                        "from users where username = ?")
//                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers(POST, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers(PUT, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers(GET, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers(POST, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers(PUT, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
//        http.authorizeRequests().antMatchers("/login", "/token/refresh").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/**").permitAll();
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

//        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // Cấu hình cho Login Form.
        http.formLogin()
                .loginProcessingUrl("/j_spring_security_login")
                .loginPage("/login")
                .defaultSuccessUrl("/index.html")
                .failureHandler(customAuthenticationFailureHandler)
                .usernameParameter("username")
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
                , "/web-admin/**"
                , "/web/**"
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
