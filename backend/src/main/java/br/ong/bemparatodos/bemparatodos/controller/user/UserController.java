package br.ong.bemparatodos.bemparatodos.controller.user;

import br.ong.bemparatodos.bemparatodos.controller.CrudController;
import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserUpdateRecord;
import br.ong.bemparatodos.bemparatodos.service.CrudService;
import br.ong.bemparatodos.bemparatodos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

  @PatchMapping("/update/{id}")
  public CompletableFuture<ResponseEntity<Map<String, Object>>> updateSingle(
      @PathVariable final UUID id,
      @RequestBody final UserInsertRecord userInsertRecord) {

    return CompletableFuture
        .supplyAsync(() -> ResponseEntity.ok(userService.updatePartially(id, userInsertRecord)))
        .exceptionally((e) -> ResponseEntity.status(500).body(null));
  }
}
