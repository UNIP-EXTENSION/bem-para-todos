package br.ong.bemparatodos.bemparatodos.entity.event;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "user_id", nullable = false)
  private Long user;

  @OneToOne(mappedBy = "event")
  private EventDetail eventDetail;

  @OneToOne(mappedBy = "event")
  @JoinColumn(name = "address_id", nullable = false)
  private EventAddress address;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", length = 4000)
  private String description;

  @Column(name = "start_date", nullable = false)
  private Instant startDate;

  @Column(name = "end_date", nullable = false)
  private Instant endDate;

  @OneToMany(mappedBy = "event")
  private Collection<EventFile> files;

  public Event() {
  }

  public Event(UUID id, Long user, EventDetail eventDetail, EventAddress address, String name, String description, Instant startDate, Instant endDate, Collection<EventFile> files) {
    this.id = id;
    this.user = user;
    this.eventDetail = eventDetail;
    this.address = address;
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.files = files;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Long getUser() {
    return user;
  }

  public void setUser(Long user) {
    this.user = user;
  }

  public EventDetail getEventDetails() {
    return eventDetail;
  }

  public void setEventDetails(EventDetail eventDetail) {
    this.eventDetail = eventDetail;
  }

  public EventAddress getAddress() {
    return address;
  }

  public void setAddress(EventAddress address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getStartDate() {
    return startDate;
  }

  public void setStartDate(Instant startDate) {
    this.startDate = startDate;
  }

  public Instant getEndDate() {
    return endDate;
  }

  public void setEndDate(Instant endDate) {
    this.endDate = endDate;
  }

  public Collection<EventFile> getFiles() {
    return files;
  }

  public void setFiles(Collection<EventFile> files) {
    this.files = files;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Event event)) return false;
    return Objects.equals(id, event.id) && Objects.equals(user, event.user) && Objects.equals(eventDetail, event.eventDetail) && Objects.equals(address, event.address) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(files, event.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, eventDetail, address, name, description, startDate, endDate, files);
  }
}