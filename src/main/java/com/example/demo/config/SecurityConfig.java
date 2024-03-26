package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

   private static final String USER_QUERY = "select email, password, enabled\n" +
            "from users\n" +
            "where email = ?;";

    private static final String AUTHORITY_QUERY = "SELECT u.email, r.AUTHORITY\n" +
            "FROM USER_ROLE ur\n" +
            "         JOIN users u ON ur.USER_ID = u.id\n" +
            "         JOIN AUTHORITIES r ON ur.ROLE_ID = r.id\n" +
            "WHERE u.email = ?;";


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_QUERY)
                .authoritiesByUsernameQuery(AUTHORITY_QUERY)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .httpBasic(Customizer.withDefaults())
                 .formLogin(AbstractHttpConfigurer::disable)
                 .logout(AbstractHttpConfigurer::disable)
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(authorize -> authorize
                         .requestMatchers(HttpMethod.POST, "/resumes/**").hasAuthority("APPLICANT")
                         .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasAuthority("APPLICANT")
                         .requestMatchers(HttpMethod.PUT, "/resumes/**").hasAuthority("APPLICANT")
                         .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasAuthority("EMPLOYER")
                         .requestMatchers(HttpMethod.DELETE, "/vacancies/").hasAuthority("EMPLOYER")
                         .requestMatchers(HttpMethod.POST, "/vacancies/").hasAuthority("EMPLOYER")
                         .requestMatchers(HttpMethod.POST, "vacancies/respond").hasAuthority("APPLICANT")
                         .requestMatchers(HttpMethod.GET, "/resumes/**").hasAuthority("EMPLOYER")
                         .requestMatchers(HttpMethod.GET, "vacancies/**").permitAll()
                         .anyRequest().permitAll());

         return http.build();
     }
}
