package br.ong.bemparatodos.bemparatodos.mapper;

import br.ong.bemparatodos.bemparatodos.config.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.dto.AddressDTO;
import br.ong.bemparatodos.bemparatodos.entity.Address;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface FileMapper {

    AddressDTO entitytoDto(Address address);

    Address dtoToEntity(AddressDTO addressDTO);
}
