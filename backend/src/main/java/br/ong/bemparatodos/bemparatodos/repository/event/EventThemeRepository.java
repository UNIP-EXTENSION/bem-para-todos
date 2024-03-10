package br.ong.bemparatodos.bemparatodos.repository.event;

import br.ong.bemparatodos.bemparatodos.entity.event.EventTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventThemeRepository extends JpaRepository<EventTheme, Long> {
}
