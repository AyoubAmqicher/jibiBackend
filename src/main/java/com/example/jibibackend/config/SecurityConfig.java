//package com.example.jibibackend.config;
//
//import com.example.jibibackend.service.AgentDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private final AgentDetailsService agentDetailsService;
//
//    public SecurityConfig(AgentDetailsService agentDetailsService) {
//        this.agentDetailsService = agentDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(csrfConfigurer -> csrfConfigurer.disable()).authorizeHttpRequests((
//                requests) -> requests.requestMatchers("/login", "/api/agents/**")
//                .permitAll().anyRequest().authenticated()).formLogin((form)->form.
//                loginPage("/login").defaultSuccessUrl("/dashboard",
//                        true).permitAll()).logout((logout) -> logout.permitAll());
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return agentDetailsService;
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
//    }
//}
