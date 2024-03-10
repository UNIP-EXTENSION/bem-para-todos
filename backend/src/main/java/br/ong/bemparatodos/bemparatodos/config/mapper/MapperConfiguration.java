package br.ong.bemparatodos.bemparatodos.config.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MapperConfiguration {
}
