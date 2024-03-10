package br.ong.bemparatodos.bemparatodos.record.event;

import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventFileRecord(
   UUID id,

   @NotNull(message = "Event cannot be null")
   EventRecord event,

   @NotNull(message = "File cannot be null")
   FileRecord file
) {
}