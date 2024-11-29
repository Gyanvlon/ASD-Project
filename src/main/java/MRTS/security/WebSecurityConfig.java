package MRTS.security;

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

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.PATCH,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("ADMIN","PATIENT","DOCTOR","PHARMACIST","LAB_TECHNICIAN","MANAGER")
                        .requestMatchers("/drugs/**").hasAnyRole("ADMIN","MANAGER")
                        .requestMatchers(HttpMethod.GET,"/reports/**").hasAnyRole("PATIENT","DOCTOR","LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.POST,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.PUT,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.PATCH,"/reports/**").hasRole("LAB_TECHNICIAN")
                        .requestMatchers(HttpMethod.POST,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PUT,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.PATCH,"/prescriptions/**").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/prescriptions/**").hasAnyRole("DOCTOR","ADMIN","PATIENT","PHARMACIST")
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
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }
}
