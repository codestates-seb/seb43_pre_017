package com.homunculus.preproject.auth.cofig;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.User;
import java.util.Arrays;

@AllArgsConstructor
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .formLogin()
//                .loginPage("/api/login")
//                .loginProcessingUrl("/process_login")
//                .failureUrl("/api/login?error")
//                .and()
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/api/login/access-denied")
                .and()
//                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        // 회원 접근 제어
                        .antMatchers(HttpMethod.POST, "/api/signup").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/api/member/*").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/api/member/*").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/member/*").hasRole("USER")
                        // 질문 접근 제어
                        .antMatchers(HttpMethod.POST, "/api/article").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/api/article/*").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/api/article/*").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/articles/*").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/api/article/*").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/articles").hasRole("USER")
                        // 답변 접근 제어
                        .antMatchers(HttpMethod.POST, "/api/article/*/answer").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/api/article/*/answer/*").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/api/article/*/answers/*").permitAll()
                        .antMatchers(HttpMethod.DELETE, "/api/article/*/answer/*").hasRole("USER")
                        // 로그아웃 접근 제어
                        .antMatchers(HttpMethod.POST, "/api/logout").hasRole("USER")

                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("http://localhost:3000");
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user@gmail.com")
                        .password("1111")
                        .roles("USER")
                        .build();


        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin@gmail.com")
                        .password("2222")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user, admin);

    }
}
