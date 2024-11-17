package br.ong.bemparatodos.bemparatodos.controller.auth;

import br.ong.bemparatodos.bemparatodos.record.user.auth.request.UserAuthRequest;
import br.ong.bemparatodos.bemparatodos.record.user.auth.response.UserAuthResponse;
import br.ong.bemparatodos.bemparatodos.service.user.auth.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserAuthService userAuthService;

  public AuthController(final UserAuthService userAuthService) {
    this.userAuthService = userAuthService;
  }

  @PostMapping
  public CompletableFuture<ResponseEntity<UserAuthResponse>> auth(final @RequestBody UserAuthRequest authRequest) {
    try {
      final UserAuthResponse userAuthResponse = userAuthService.authenticateUser(authRequest);

      return CompletableFuture.completedFuture(new ResponseEntity<>(userAuthResponse, HttpStatus.OK));
    } catch (Exception e) {
      return CompletableFuture.supplyAsync(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
  }
}
