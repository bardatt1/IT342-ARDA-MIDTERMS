package com.arda.GoogleContacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable CSRF protection for /contacts/** endpoints
                .csrf(csrf -> csrf.ignoringRequestMatchers("/contacts/**"))
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Permit all requests to these endpoints
                        .requestMatchers("/login", "/error", "/webjars/**", "/css/**", "/js/**").permitAll()
                        // Require authentication for any other request
                        .anyRequest().authenticated()
                )
                // Configure OAuth2 login
                .oauth2Login(oauth2 -> oauth2
                        // Custom login page
                        .loginPage("/login")
                        // Redirect to /index on successful login
                        .defaultSuccessUrl("/index", true)
                        // Redirect to /login?error=true on login failure
                .failureUrl("/login?error=true")
                )
                // Configure logout behavior
                .logout(logout -> logout
                        // Redirect to /login?logout=true on successful logout
                        .logoutSuccessUrl("/login?logout=true")
                        // Invalidate the HTTP session
                        .invalidateHttpSession(true)
                        // Delete JSESSIONID cookie
                        .deleteCookies("JSESSIONID")
                )
                // Configure session management
                .sessionManagement(session -> session
                        // Create a session only if required
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .build();
    }

    /**
     * OAuth2 Authorized Client Manager to manage tokens.
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        // Build an OAuth2AuthorizedClientProvider that supports authorization code and refresh token grants
        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .authorizationCode()
                .refreshToken()
                .build();

        // Create a DefaultOAuth2AuthorizedClientManager with the provided client registration and authorized client repositories
        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);

        // Set the authorized client provider to the one created above
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

}
