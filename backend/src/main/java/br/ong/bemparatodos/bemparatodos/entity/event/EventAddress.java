package br.ong.bemparatodos.bemparatodos.entity.event;

import br.ong.bemparatodos.bemparatodos.entity.address.Address;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity()
@Table(name = "tb_event_address")
public class EventAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne
  @JoinColumn(name = "event_id")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address file;

  public EventAddress() {
  }

  public EventAddress(UUID id, Event event, Address file) {
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

  public Address getFile() {
    return file;
  }

  public void setFile(Address file) {
    this.file = file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventAddress eventFile)) return false;
    return Objects.equals(id, eventFile.id) && Objects.equals(event, eventFile.event) && Objects.equals(file, eventFile.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, event, file);
  }
}
