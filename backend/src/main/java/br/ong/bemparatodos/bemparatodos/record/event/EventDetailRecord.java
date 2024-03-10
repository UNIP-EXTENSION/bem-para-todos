package br.ong.bemparatodos.bemparatodos.record.event;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventDetailRecord(
   UUID id,

   @NotNull(message = "Event cannot be null")
   EventRecord event,

   @NotNull(message = "Dress code cannot be null")
   DressCodeRecord dressCode,

   @NotNull(message = "Event theme cannot be null")
   EventThemeRecord eventTheme,

   String requiredItem
) {
}