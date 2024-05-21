package br.ong.bemparatodos.bemparatodos.mapper.address;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.address.Address;
import br.ong.bemparatodos.bemparatodos.record.address.AddressRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface AddressMapper {

  AddressRecord entitytoDto(Address entity);

  Address dtoToEntity(AddressRecord dto);
}
