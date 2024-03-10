package br.ong.bemparatodos.bemparatodos.repository.event;

import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DressCodeRepository extends JpaRepository<DressCode, Long> {
}
