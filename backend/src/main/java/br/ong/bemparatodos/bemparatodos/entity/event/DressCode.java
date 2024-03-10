package br.ong.bemparatodos.bemparatodos.entity.event;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_dress_code")
public class DressCode {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "description", nullable = false,  unique = true)
  private String description;

  public DressCode() {
  }

  public DressCode(UUID id, String description) {
    this.id = id;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DressCode dressCode)) return false;
    return Objects.equals(id, dressCode.id) && Objects.equals(description, dressCode.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }
}
