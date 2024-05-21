package br.ong.bemparatodos.bemparatodos.controller.user;

import br.ong.bemparatodos.bemparatodos.controller.CrudController;
import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import br.ong.bemparatodos.bemparatodos.service.CrudService;
import br.ong.bemparatodos.bemparatodos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends CrudController<UserInsertRecord, UUID> {

  private final UserService userService;

  @Autowired
  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @Override
  protected CrudService<UserInsertRecord, UUID> getService() {
    return userService;
  }
}
