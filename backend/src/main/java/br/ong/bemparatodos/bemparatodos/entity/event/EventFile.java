package br.ong.bemparatodos.bemparatodos.entity.event;

import br.ong.bemparatodos.bemparatodos.entity.file.File;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity()
@Table(name = "tb_event_file")
public class EventFile {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "file_id")
  private File file;

  public EventFile() {
  }

  public EventFile(UUID id, Event event, File file) {
    this.id = id;
    this.event = event;
    this.file = file;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventFile eventFile)) return false;
    return Objects.equals(id, eventFile.id) && Objects.equals(event, eventFile.event) && Objects.equals(file, eventFile.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, event, file);
  }
}
