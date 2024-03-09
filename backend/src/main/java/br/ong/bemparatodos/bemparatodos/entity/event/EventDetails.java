package br.ong.bemparatodos.bemparatodos.entity.event;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_event_details")
public class EventDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @OneToOne(mappedBy = "eventDetails")
  private Event event;

  @ManyToOne
  @JoinColumn(name = "dress_code_id")
  private DressCode dressCode;

  @ManyToOne
  @JoinColumn(name = "event_theme_id")
  private EventTheme eventTheme;

  @Column(name = "required_item")
  private String requiredItem;

  public EventDetails() {
  }

  public EventDetails(UUID id, Event event, DressCode dressCode, EventTheme eventTheme, String requiredItem) {
    this.id = id;
    this.event = event;
    this.dressCode = dressCode;
    this.eventTheme = eventTheme;
    this.requiredItem = requiredItem;
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

  public DressCode getDressCode() {
    return dressCode;
  }

  public void setDressCode(DressCode dressCode) {
    this.dressCode = dressCode;
  }

  public EventTheme getEventTheme() {
    return eventTheme;
  }

  public void setEventTheme(EventTheme eventTheme) {
    this.eventTheme = eventTheme;
  }

  public String getRequiredItem() {
    return requiredItem;
  }

  public void setRequiredItem(String requiredItem) {
    this.requiredItem = requiredItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EventDetails that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(event, that.event) && Objects.equals(dressCode, that.dressCode) && Objects.equals(eventTheme, that.eventTheme) && Objects.equals(requiredItem, that.requiredItem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, event, dressCode, eventTheme, requiredItem);
  }
}