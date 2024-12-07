package MRTS.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.PATCH,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers("/drugs/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.GET,"/reports/**").hasAnyRole("PATIENT","DOCTOR","PHARMACIST", "LAB_TECHNICIAN","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.PUT,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.PATCH,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.POST,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PUT,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/prescriptions/**").hasAnyRole("DOCTOR","ADMIN","PATIENT","PHARMACIST", "LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.GET,"/patients/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/doctors/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/labtechnicians/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/pharmacists/**").permitAll()
                        .requestMatchers("/patients/**").hasAnyRole("PATIENT","ADMIN")
                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR","ADMIN" )
                        .requestMatchers("/managers/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/labtechnicians/**").hasAnyRole("LAB_TECHNICIAN","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/labs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/labs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/labs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/labs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/labs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/hospitals/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/hospitals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/hospitals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/hospitals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/hospitals/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pharmacies/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pharmacies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pharmacies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pharmacies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/pharmacies/**").hasRole("ADMIN")
                        .requestMatchers("/pharmacists/**").hasAnyRole("PHARMACIST","ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless authentication
                .authenticationProvider(authenticationProvider) // Set up authentication provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("{\"message\":\"Logout successful\"}");
                        })
                );;
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Angular frontend URL
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH")); // Allowed HTTP methods
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept")); // Allowed headers
        configuration.setAllowCredentials(true); // Allow credentials (cookies or Authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
