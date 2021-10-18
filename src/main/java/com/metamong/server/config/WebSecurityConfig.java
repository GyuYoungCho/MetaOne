package com.metamong.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsUtils;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Bean
////    public AuthenticationSuccessHandler successHandler() {
////        return new CustomOAuthLoginSuccessHandler();
////    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception
//    {
//        web.ignoring().antMatchers(
//                "/css/**", "/script/**", "image/**", "/fonts/**", "lib/**"
//        );
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .anyRequest().permitAll()
//                .and()
////                .cors().configurationSource(corsConfigurationSource())
////                .and()
//                .csrf().disable()
//                .oauth2Login()
//                .authorizationEndpoint().baseUri("/api/oauth2/authorization")
//                .and()
//                .redirectionEndpoint().baseUri("/api/login/oauth2/code/*");
////                .and()
////                .successHandler(successHandler())
////                .userInfoEndpoint()
////                .userService(customOAuth2UserService);
//
//    }
//
//}
