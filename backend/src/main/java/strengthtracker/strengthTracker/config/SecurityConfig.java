package strengthtracker.strengthTracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(HttpMethod.POST, "/users", "/users/", "/users/login", "/users/login/").permitAll()
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger/**",
                        "/v3/api-docs/**",
                        "/swagger/api",
                        "/swagger/api/**",
                        "/swagger",
                        "/api/swagger",
                        "/api/swagger/**",
                        "/api/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
    return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
  }

  @Autowired private JwtAuthFilter jwtAuthFilter;
}
