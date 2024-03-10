package br.ong.bemparatodos.bemparatodos.entity.event;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_event_theme")
public class EventTheme {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "theme", nullable = false)
  private String theme;

  public EventTheme() {
  }

  public EventTheme(UUID id, String theme) {
    this.id = id;
    this.theme = theme;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventTheme that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(theme, that.theme);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, theme);
  }
}
