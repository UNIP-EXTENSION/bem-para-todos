package br.ong.bemparatodos.bemparatodos.record.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CountryRecord(
   @NotNull(message = "ID cannot be null")
   Long id,

   @NotBlank(message = "Name cannot be blank")
   @Size(max = 60, message = "Name must have up to {max} characters")
   String name,

   @NotBlank(message = "Portuguese name cannot be blank")
   @Size(max = 60, message = "Portuguese name must have up to {max} characters")
   String namePortuguese,

   @NotBlank(message = "Code cannot be blank")
   @Size(max = 2, message = "Code must have up to {max} characters")
   String code
) {}
