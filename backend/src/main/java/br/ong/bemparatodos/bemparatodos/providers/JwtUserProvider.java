package br.ong.bemparatodos.bemparatodos.providers;

import br.ong.bemparatodos.bemparatodos.providers.exceptions.JWTValidateException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtUserProvider {

  @Value("${security.token.bem-para-todos.secret}")
  private String secretkey;

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "").trim();
    Algorithm algorithm = Algorithm.HMAC256(secretkey);

    try {
      return JWT.require(algorithm)
          .build()
          .verify(token);
    } catch (JWTVerificationException e) {
      throw new JWTValidateException(e.getMessage());
    }
  }
}
