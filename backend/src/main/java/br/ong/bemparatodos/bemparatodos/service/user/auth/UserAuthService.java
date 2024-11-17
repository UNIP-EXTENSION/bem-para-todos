package br.ong.bemparatodos.bemparatodos.service.user.auth;

import br.ong.bemparatodos.bemparatodos.record.user.auth.request.UserAuthRequest;
import br.ong.bemparatodos.bemparatodos.record.user.auth.response.UserAuthResponse;

public interface UserAuthService {

  UserAuthResponse authenticateUser(UserAuthRequest userAuthRequest);

}
