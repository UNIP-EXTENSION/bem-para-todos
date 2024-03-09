package br.ong.bemparatodos.bemparatodos.record;

import br.ong.bemparatodos.bemparatodos.entity.address.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AddressRecord(

   @NotNull(message = "The ID cannot be null")
   UUID id,

   @NotNull(message = "Country cannot be null")
   Country country,

   @NotBlank(message = "City cannot be blank")
   @Size(max = 100, message = "City name must be less than or equal to 100 characters")
   String city,

   @NotBlank(message = "Region cannot be blank")
   @Size(max = 100, message = "Region name must be less than or equal to 100 characters")
   String region,

   @NotBlank(message = "Postal code cannot be blank")
   @Size(max = 20, message = "Postal code must be less than or equal to 20 characters")
   String postalCode,

   @NotBlank(message = "Address line 1 cannot be blank")
   String line1,

   @NotBlank(message = "Address line 2 cannot be blank")
   String line2,

   String line3, // optional, thus no constraint

   String additionalInfo // optional, thus no constraint

) {
}
