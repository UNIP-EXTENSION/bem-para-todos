package br.ong.bemparatodos.bemparatodos.mapper.user;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.user.Role;
import br.ong.bemparatodos.bemparatodos.record.user.RoleRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface RoleMapper {

  RoleRecord entitytoRecord(Role entity);

  Role recordToEntity(RoleRecord record);
}
