package br.ong.bemparatodos.bemparatodos.security.filter;

import br.ong.bemparatodos.bemparatodos.providers.JwtUserProvider;
import br.ong.bemparatodos.bemparatodos.repository.user.UserRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class SecurityUserFilter extends OncePerRequestFilter {

  private final JwtUserProvider jwtUserProvider;

  public SecurityUserFilter(final JwtUserProvider jwtUserProvider) {
    this.jwtUserProvider = jwtUserProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String authorization = request.getHeader("Authorization");

    if (request.getRequestURI().startsWith("/events")) {
      Optional.ofNullable(authorization).ifPresent(x -> {
        DecodedJWT decodedJWT = jwtUserProvider.validateToken(authorization);

        if (isNull(decodedJWT)) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        request.setAttribute("user_id", decodedJWT.getSubject());

        List<Object> roles = decodedJWT.getClaim("roles").asList(Object.class);

        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.toString().toUpperCase()))
            .toList();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
      });
    }
    filterChain.doFilter(request, response);
  }
}
