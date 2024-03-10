package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.EventDetail;
import br.ong.bemparatodos.bemparatodos.record.event.EventDetailRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface EventDetailMapper {

  EventDetailRecord entitytoDto(EventDetail entity);

  EventDetail dtoToEntity(EventDetailRecord dto);
}
