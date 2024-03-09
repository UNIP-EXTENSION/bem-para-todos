package br.ong.bemparatodos.bemparatodos.mapper;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.address.Address;
import br.ong.bemparatodos.bemparatodos.record.AddressRecord;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface AddressMapper {

  AddressRecord entitytoDto(Address address);

  Address dtoToEntity(AddressRecord addressDTO);
}
