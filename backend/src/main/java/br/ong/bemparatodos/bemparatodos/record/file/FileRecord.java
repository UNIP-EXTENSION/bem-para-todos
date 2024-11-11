package br.ong.bemparatodos.bemparatodos.record.file;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;
import java.time.Instant;
import java.util.UUID;

public record FileRecord(

   UUID id,

   @NotBlank(message = "The file name cannot be blank")
   String fileName,

   @NotBlank(message = "The file type cannot be blank")
   String fileType,

   @NotNull(message = "The file size cannot be null")
   @Min(value = 1, message = "The value must be greater than or equal to 1")
   Long fileSize,

   String description,

   @NotNull(message = "The upload date cannot be null")
   Instant uploadDate,

   byte[] data,

   String urlFile
) {
}
