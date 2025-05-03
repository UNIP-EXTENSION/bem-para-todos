package br.ong.bemparatodos.bemparatodos.mapper.user;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserUpdateRecord;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(config = MapperConfiguration.class)
public interface UserInsertMapper {

  UserInsertRecord entitytoRecord(User entity);

  User recordToEntity(UserInsertRecord record);

  @Named("toUpdatedFieldsMap")
  default Map<String, Object> toUpdatedFieldsMap(UserInsertRecord record) {
    return Stream.of(
            Map.entry("firstName", mapReturnStringEmpty(record.firstName())),
            Map.entry("lastName", mapReturnStringEmpty(record.lastName())),
            Map.entry("email", mapReturnStringEmpty(record.email())),
            Map.entry("password", mapReturnStringEmpty(record.password()))
        )
        .filter(entry -> !entry.getValue().isEmpty())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @Named("mapReturnStringEmpty")
  default String mapReturnStringEmpty(String value) {
    return value == null ? "" : value;
  }
}
