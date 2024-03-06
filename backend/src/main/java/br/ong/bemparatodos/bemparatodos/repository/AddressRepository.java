package br.ong.bemparatodos.bemparatodos.repository;

import br.ong.bemparatodos.bemparatodos.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
