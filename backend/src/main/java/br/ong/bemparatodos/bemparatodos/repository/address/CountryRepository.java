package br.ong.bemparatodos.bemparatodos.repository.address;

import br.ong.bemparatodos.bemparatodos.entity.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
