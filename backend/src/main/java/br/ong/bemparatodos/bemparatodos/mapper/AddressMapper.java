package br.ong.bemparatodos.bemparatodos.mapper;

import br.ong.bemparatodos.bemparatodos.dto.AddressDTO;
import br.ong.bemparatodos.bemparatodos.entity.Address;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddressMapper {

    AddressDTO entitytoDto(Address address);

    Address dtoToEntity(AddressDTO addressDTO);
}
