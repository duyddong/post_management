package learning.project3.config;
import java.nio.file.AccessDeniedException;

import org.modelmapper.ModelMapper;
//import com.ey.springboot3security.filter.JwtAuthFilter;
//import com.ey.springboot3security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;

import learning.project3.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        // Có thể log ngoại lệ hoặc xử lý khác nếu cần
        String errorMessage = "Access denied: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/user/**").authenticated())hasAuthority("ROLE_USER")
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/user/**").hasAuthority("ROLE_USER"))
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/admin/**").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/tasks/**").hasAuthority("ROLE_USER"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/posts/**").hasAuthority("ROLE_USER"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/comments/**").hasAuthority("ROLE_USER"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/photos/**").hasAuthority("ROLE_USER"))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                
                .build();
    }

    // Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
