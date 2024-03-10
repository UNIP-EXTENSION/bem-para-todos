package br.ong.bemparatodos.bemparatodos.record.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EventThemeRecord(

   UUID id,

   @NotBlank(message = "Theme cannot be blank")
   @Size(max = 255, message = "Theme must have up to {max} characters")
   String theme
) {
}