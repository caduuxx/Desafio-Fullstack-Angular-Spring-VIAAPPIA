package com.desafioviaappia.api.Security;

import com.desafioviaappia.api.Repositores.UserRepository;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService uds) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(reg -> reg
                // Auth & Swagger liberados
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html"
                ).permitAll()

                // Regras de autorização:
                // GET liberado para QUALQUER autenticado
                .requestMatchers(HttpMethod.GET, "/incidents/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/stats/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/**/comments/**").authenticated()

                // Escrita só ADMIN
                .requestMatchers(HttpMethod.POST, "/incidents/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/incidents/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/incidents/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/incidents/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/incidents/*/comments").hasRole("ADMIN")

                // fallback
                .anyRequest().authenticated()
        );

        http.authenticationProvider(authenticationProvider(userDetailsService(null)));
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
