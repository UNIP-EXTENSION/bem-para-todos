package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.EventDetail;
import br.ong.bemparatodos.bemparatodos.record.event.EventDetailRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface EventDetailMapper {

  @Mapping(target = "event", ignore = true)
  EventDetailRecord entitytoDto(EventDetail entity);

  @Mapping(target = "event", ignore = true)
  EventDetail dtoToEntity(EventDetailRecord dto);



}
