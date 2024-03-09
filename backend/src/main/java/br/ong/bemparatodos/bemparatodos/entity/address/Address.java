package br.ong.bemparatodos.bemparatodos.entity.address;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "country_id", nullable = false)
  private Country country;

  @Column(name = "city", length = 100, nullable = false)
  private String city;

  @Column(name = "region", length = 100, nullable = false)
  private String region;

  @Column(name = "postal_code", length = 20, nullable = false)
  private String postalCode;

  @Column(name = "line1", nullable = false)
  private String line1;

  @Column(name = "line2", nullable = false)
  private String line2;

  @Column(name = "line3")
  private String line3;

  @Column(name = "additional_info")
  private String additionalInfo;

  public Address() {
  }

  public Address(UUID id, Country country, String city, String region, String postalCode, String line1, String line2, String line3, String additionalInfo) {
    this.id = id;
    this.country = country;
    this.city = city;
    this.region = region;
    this.postalCode = postalCode;
    this.line1 = line1;
    this.line2 = line2;
    this.line3 = line3;
    this.additionalInfo = additionalInfo;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getLine3() {
    return line3;
  }

  public void setLine3(String line3) {
    this.line3 = line3;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Address address)) return false;
    return Objects.equals(id, address.id) && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(region, address.region) && Objects.equals(postalCode, address.postalCode) && Objects.equals(line1, address.line1) && Objects.equals(line2, address.line2) && Objects.equals(line3, address.line3) && Objects.equals(additionalInfo, address.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, country, city, region, postalCode, line1, line2, line3, additionalInfo);
  }
}
