package br.ong.bemparatodos.bemparatodos.service.user.auth.impl;

import br.ong.bemparatodos.bemparatodos.entity.user.Role;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.record.user.auth.request.UserAuthRequest;
import br.ong.bemparatodos.bemparatodos.record.user.auth.response.UserAuthResponse;
import br.ong.bemparatodos.bemparatodos.record.user.auth.response.UserInfoResponse;
import br.ong.bemparatodos.bemparatodos.repository.user.UserRepository;
import br.ong.bemparatodos.bemparatodos.service.user.auth.UserAuthService;
import br.ong.bemparatodos.bemparatodos.util.token.TokenUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class UserAuthServiceImpl implements UserAuthService {

  @Value("${security.token.bem-para-todos.secret}")
  private String secretKey;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public UserAuthServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
    this.userRepository = requireNonNull(userRepository, "userRepository cannot be null");
    this.passwordEncoder = requireNonNull(passwordEncoder, "passwordEncoder cannot be null");
  }

  @Override
  public UserAuthResponse authenticateUser(final UserAuthRequest userAuthRequest) {
    final User user = userRepository.findByEmail(userAuthRequest.email())
        .orElseThrow(
            () -> new UsernameNotFoundException("Email not found"));

    if (!passwordEncoder.matches(userAuthRequest.password(), user.getPassword()))
      throw new BadCredentialsException("Wrong password");

    final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    final Instant expireIn = Instant.now().plus(Duration.ofHours(2));

    final String token = JWT.create()
        .withIssuer("bem-para-todos")
        .withSubject(user.getId().toString())
        .withClaim("roles", userRepository.findByRoles(user.getId())
            .stream()
            .map(Role::getAuthority)
            .toList())
        .withExpiresAt(expireIn)
        .sign(algorithm);

    final UserInfoResponse userInfoResponse =
        userInfoEncoded(
            user.getId(),
            user.getFirstName(),
            user.getEmail());

    return new UserAuthResponse(
        token,
        expireIn.toEpochMilli(),
        user.getRoles()
            .stream()
            .map(Role::getAuthority)
            .collect(Collectors.toSet()),
        userInfoResponse
    );
  }

  private UserInfoResponse userInfoEncoded(final UUID uuid, final String name, final String email) {
    return new UserInfoResponse(
        uuid,
        TokenUtils.encode(name),
        TokenUtils.encode(email)
    );
  }
}
