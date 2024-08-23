package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .httpBasic(Customizer.withDefaults())
//                 .formLogin(form -> form
//                         .loginPage("/auth/login")
//                         .loginProcessingUrl("/auth/login")
//                         .defaultSuccessUrl("/profile")
//                         .failureUrl("/auth/login?error=true")
//                         .permitAll())
//                 .logout(logout -> logout
//                         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                         .permitAll())
                 .authorizeHttpRequests(authorize -> authorize
//                         .requestMatchers(HttpMethod.POST, "/resumes/**").hasAuthority("APPLICANT")
//                         .requestMatchers(HttpMethod.GET, "/resumes/add").hasAuthority("APPLICANT")
//                         .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("APPLICANT")
//                         .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("APPLICANT")
//                         .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("EMPLOYER")
//                         .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasAuthority("EMPLOYER")
//                         .requestMatchers(HttpMethod.POST, "/vacancies/respond/**").hasAuthority("APPLICANT")
//                         .requestMatchers(HttpMethod.POST, "/vacancies/**").hasAuthority("EMPLOYER")
//                         .requestMatchers(HttpMethod.GET, "/vacancies/add").hasAuthority("EMPLOYER")
//                         .requestMatchers(HttpMethod.GET, "/resumes/active").hasAuthority("EMPLOYER")
//                         .requestMatchers("/chat/**").hasAnyAuthority("EMPLOYER", "APPLICANT")
//                         .requestMatchers("/profile/**").hasAnyAuthority("APPLICANT", "EMPLOYER")
//                         .requestMatchers(HttpMethod.GET, "/profile/responses/**").hasAuthority("EMPLOYER")
//                         .requestMatchers(HttpMethod.GET, "/").permitAll()
                         .anyRequest().permitAll())
                 .exceptionHandling(Customizer.withDefaults());

         return http.build();
     }
}
