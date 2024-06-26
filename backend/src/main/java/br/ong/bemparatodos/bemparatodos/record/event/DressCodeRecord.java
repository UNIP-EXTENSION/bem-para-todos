package br.ong.bemparatodos.bemparatodos.record.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DressCodeRecord(
   UUID id,

   @NotBlank(message = "Description cannot be blank")
   @Size(max = 255, message = "Description must have up to {max} characters")
   String description
) {
}