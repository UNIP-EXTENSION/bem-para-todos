package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import br.ong.bemparatodos.bemparatodos.entity.event.EventFile;
import br.ong.bemparatodos.bemparatodos.record.event.DressCodeRecord;
import br.ong.bemparatodos.bemparatodos.record.event.EventFileRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface DressCodeMapper {

  DressCodeRecord entitytoDto(DressCode entity);

  DressCode dtoToEntity(DressCodeRecord dto);
}
