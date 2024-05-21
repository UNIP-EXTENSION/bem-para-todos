package br.ong.bemparatodos.bemparatodos.repository.address;

import br.ong.bemparatodos.bemparatodos.entity.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
