package br.ong.bemparatodos.bemparatodos.dto;

import java.time.Instant;
import java.util.UUID;

public record FileRecord(

   UUID id,
   String fileName,
   String fileType,
   Long fileSize,
   String description,
   Instant uploadDate

) {}
