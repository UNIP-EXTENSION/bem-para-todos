package br.ong.bemparatodos.bemparatodos.mapper.file;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.file.File;
import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface FileMapper {

  FileRecord entitytoDto(File entity);

  File dtoToEntity(FileRecord dto);
}
