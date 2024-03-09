package br.ong.bemparatodos.bemparatodos.entity.event;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "tb_dress_code")
public class DressCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "description", nullable = false)
  private String description;

  public DressCode() {
  }

  public DressCode(Long id, String description) {
    this.id = id;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
