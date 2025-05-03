package br.ong.bemparatodos.bemparatodos.config.security;

import br.ong.bemparatodos.bemparatodos.security.filter.SecurityUserFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final SecurityUserFilter securityFilter;

  public SecurityConfig(final SecurityUserFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  /*@Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
    security.csrf(AbstractHttpConfigurer::disable)
       .headers(HeadersConfigurer::disable)
       .authorizeHttpRequests(auth -> {
         auth.requestMatchers("/users").permitAll();
         auth.requestMatchers("/auth").permitAll();
       });

    return security.build();
  }*/

  @Bean
  protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/users").permitAll()
              .requestMatchers("/auth").permitAll()
              .requestMatchers("/users/update/**").permitAll();
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

    return httpSecurity.build();

  }
}
