package br.ong.bemparatodos.bemparatodos.record.event;

import br.ong.bemparatodos.bemparatodos.entity.file.File;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.record.address.AddressRecord;
import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

public record EventRecord(
   UUID id,

   @NotNull(message = "User ID cannot be null")
   User user,

   @NotNull(message = "Event details cannot be null")
   EventDetailRecord eventDetails,

   @NotNull(message = "Address cannot be null")
   AddressRecord address,

   @NotBlank(message = "Name cannot be blank")
   String name,

   String description,

   @NotNull(message = "Start date cannot be null")
   Instant startDate,

   @NotNull(message = "End date cannot be null")
   Instant endDate,

   Collection<FileRecord> files
) {
}