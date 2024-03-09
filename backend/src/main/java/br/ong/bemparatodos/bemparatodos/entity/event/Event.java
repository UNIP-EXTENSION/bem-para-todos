package br.ong.bemparatodos.bemparatodos.entity.event;

import br.ong.bemparatodos.bemparatodos.entity.address.Address;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Collection;
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

  @OneToOne()
  @JoinColumn(name = "event_details", nullable = false)
  private EventDetails eventDetails;

  @ManyToOne
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;

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

  public Event(UUID id, Long user, EventDetails eventDetails, Address address, String name, String description, Instant startDate, Instant endDate, Collection<EventFile> files) {
    this.id = id;
    this.user = user;
    this.eventDetails = eventDetails;
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

  public EventDetails getEventDetails() {
    return eventDetails;
  }

  public void setEventDetails(EventDetails eventDetails) {
    this.eventDetails = eventDetails;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
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
    return Objects.equals(id, event.id) && Objects.equals(user, event.user) && Objects.equals(eventDetails, event.eventDetails) && Objects.equals(address, event.address) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(files, event.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, eventDetails, address, name, description, startDate, endDate, files);
  }
}