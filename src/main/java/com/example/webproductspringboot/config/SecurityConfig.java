package com.example.webproductspringboot.config;

import com.example.webproductspringboot.filter.CustomAuthenticationFilter;
import com.example.webproductspringboot.filter.CustomAuthorizationFilter;
import com.example.webproductspringboot.utils.ContainsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.activation.DataSource;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(GET, "/admin", "/admin/index.html", "/admin/load-home").permitAll();

        http.authorizeRequests().antMatchers(GET,
                "/admin/load/invoice",
                "/admin/load/invoice/form",
                "/admin/load/invoice/form/product",
                "/admin/load/bill-order",
                "/admin/load/bill-order/form",
                "/admin/load/bill-order/form/product",
                "/admin/load/product",
                "/admin/load/returns",
                "/admin/load/returns/form/add-by-id-order/**",
                "/admin/load/transport"
        ).hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(GET,
                "/admin/load/category",
                "/admin/load/brand",
                "/admin/load/invoice/form/**",
                "/admin/load/bill-order/form/**",
                "/admin/load/product/form",
                "/admin/load/product/form/**",
                "/admin/load/returns/form/**",
                "/admin/load/user",
                "/admin/load/voucher"
        ).hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(GET,
                "/admin/load/history"
        ).hasAnyAuthority(ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(GET, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);
        http.authorizeRequests().antMatchers(PUT, "/admin/**").hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(
                POST,
                "/api/users/register",
                "/api/wish-list",
                "/api/reviews",
                "/api/orders/checkout",
                "/api/orders/payment"
        ).permitAll();

        http.authorizeRequests().antMatchers(POST,
                "/api/invoices",
                "/api/orders",
                "/api/transports",
                "/api/returns"
        ).hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(POST, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(PUT, "/api/**").hasAnyAuthority(ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers(GET, "/token/check").hasAnyAuthority(ContainsUtils.ROLE_STAFF, ContainsUtils.ROLE_ADMIN, ContainsUtils.ROLE_SUPPER_ADMIN);

        http.authorizeRequests().antMatchers("/api/login", "/token/refresh", "/signout").permitAll();

        http.authorizeRequests().antMatchers(GET, "/**").permitAll();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
