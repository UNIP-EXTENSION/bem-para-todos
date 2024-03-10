package br.ong.bemparatodos.bemparatodos.repository.event;

import br.ong.bemparatodos.bemparatodos.entity.event.EventFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventFileRepository extends JpaRepository<EventFile, UUID> {
}
