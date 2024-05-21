package br.ong.bemparatodos.bemparatodos.mapper.user;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

  UserRecord entitytoRecord(User entity);

  User recordToEntity(UserRecord record);
}
