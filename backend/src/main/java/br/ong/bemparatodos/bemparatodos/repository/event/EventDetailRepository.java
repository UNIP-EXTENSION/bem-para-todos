package br.ong.bemparatodos.bemparatodos.repository.event;

import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import br.ong.bemparatodos.bemparatodos.entity.event.EventDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventDetailRepository extends JpaRepository<EventDetail, UUID> {
}
