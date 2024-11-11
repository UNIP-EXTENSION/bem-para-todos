package br.ong.bemparatodos.bemparatodos.mapper.file;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.file.File;
import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface FileMapper {

  @Mapping(target = "data", ignore = true)
  FileRecord entitytoDto(File entity);

  @Mapping(target = "data", ignore = true)
  File dtoToEntity(FileRecord dto);
}
