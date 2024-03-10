package br.ong.bemparatodos.bemparatodos.mapper.address;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.address.Country;
import br.ong.bemparatodos.bemparatodos.record.address.CountryRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface CountryMapper {

  CountryRecord entitytoDto(Country entity);

  Country dtoToEntity(CountryRecord dto);
}
