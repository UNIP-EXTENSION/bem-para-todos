package br.ong.bemparatodos.bemparatodos.service.user;

import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import br.ong.bemparatodos.bemparatodos.record.user.auth.response.UserAuthResponse;
import br.ong.bemparatodos.bemparatodos.service.CrudService;

import java.util.UUID;

public interface UserService extends CrudService<UserInsertRecord, UUID> {

}
