package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.Event;
import br.ong.bemparatodos.bemparatodos.record.event.EventRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface EventMapper {

  EventRecord entitytoDto(Event entity);

  Event dtoToEntity(EventRecord dto);
}
