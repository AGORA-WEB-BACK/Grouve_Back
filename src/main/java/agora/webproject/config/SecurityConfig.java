package agora.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/user/login", "/api/user/register", "/").permitAll()
                        .requestMatchers("/api/meetings/create", "/api/meetings/list").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/api/user/login")
                        .defaultSuccessUrl("/api/meetings/list", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/user/login")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}