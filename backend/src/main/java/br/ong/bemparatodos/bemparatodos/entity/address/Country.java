package br.ong.bemparatodos.bemparatodos.entity.address;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_country")
public class Country {

  @Id
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "name_portuguese")
  private String namePortuguese;

  @Column(name = "code", length = 2, unique = true)
  private String code;

  public Country() {
  }

  public Country(Long id, String name, String namePortuguese, String code) {
    this.id = id;
    this.name = name;
    this.namePortuguese = namePortuguese;
    this.code = code;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNamePortuguese() {
    return namePortuguese;
  }

  public void setNamePortuguese(String namePortuguese) {
    this.namePortuguese = namePortuguese;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Country country)) return false;
    return Objects.equals(id, country.id) && Objects.equals(name, country.name) && Objects.equals(namePortuguese, country.namePortuguese) && Objects.equals(code, country.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, namePortuguese, code);
  }
}