package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import br.ong.bemparatodos.bemparatodos.entity.event.EventTheme;
import br.ong.bemparatodos.bemparatodos.record.event.DressCodeRecord;
import br.ong.bemparatodos.bemparatodos.record.event.EventThemeRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface EventThemeMapper {

  EventThemeRecord entitytoDto(EventTheme entity);

  EventTheme dtoToEntity(EventThemeRecord dto);
}
