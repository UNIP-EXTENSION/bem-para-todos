package br.ong.bemparatodos.bemparatodos.record.user;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

public record UserInsertRecord(
   UUID id,

   @NotBlank(message = "First name must not be blank")
   String firstName,

   @NotBlank(message = "Last name must not be blank")
   String lastName,

   @NotBlank(message = "Email must not be blank")
   String email,

   @NotBlank(message = "Password must not be blank")
   String password,

   Set<RoleRecord> roles
) {
}
