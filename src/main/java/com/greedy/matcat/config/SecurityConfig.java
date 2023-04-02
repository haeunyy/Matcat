package com.greedy.matcat.config;

import com.greedy.matcat.member.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationService authenticationService;
    private final AuthenticationFailureHandler customFailureHandler;

    public SecurityConfig(AuthenticationService authenticationService, AuthenticationFailureHandler customFailureHandler) {
        this.authenticationService = authenticationService;
        this.customFailureHandler = customFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/member/mypage","/member/update", "/member/delete", "/board/userBoardList", "/user/userHelp001").hasRole("MEMBER")
                .antMatchers("/order/admin/**", "/admin/**","/board/adminBoardList").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/member/success")
                .failureHandler(customFailureHandler)
                .usernameParameter("memberId")
                .passwordParameter("memberPwd")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/main")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/member/success")
                .userInfoEndpoint()
                .userService(authenticationService)
                .and()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/common/error")
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }


}



