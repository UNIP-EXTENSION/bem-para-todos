package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.EventFile;
import br.ong.bemparatodos.bemparatodos.record.event.EventFileRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface EventFileMapper {

  EventFileRecord entitytoDto(EventFile entity);

  EventFile dtoToEntity(EventFileRecord dto);
}
