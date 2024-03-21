package br.ong.bemparatodos.bemparatodos.entity.event;

import br.ong.bemparatodos.bemparatodos.entity.address.Address;
import br.ong.bemparatodos.bemparatodos.entity.file.File;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_event", schema = "main")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", length = 4000)
  private String description;

  @Column(name = "start_date", nullable = false)
  private Instant startDate;

  @Column(name = "end_date", nullable = false)
  private Instant endDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "tb_event_file",
     joinColumns = @JoinColumn(name = "event_id"),
     inverseJoinColumns = @JoinColumn(name = "file_id"),
     schema = "main")
  private Collection<File> files = new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "tb_event_address",
     joinColumns = @JoinColumn(name = "event_id"),
     inverseJoinColumns = @JoinColumn(name = "address_id"),
     schema = "main")
  private Address address;

  @OneToOne(mappedBy = "event")
  private EventDetail eventDetail;

  public Event() {
  }

  public Event(UUID id, User user, EventDetail eventDetail, Address address, String name, String description, Instant startDate, Instant endDate, Collection<File> files) {
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public EventDetail getEventDetails() {
    return eventDetail;
  }

  public void setEventDetails(EventDetail eventDetail) {
    this.eventDetail = eventDetail;
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

  public Collection<File> getFiles() {
    return files;
  }

  public void setFiles(Collection<File> files) {
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