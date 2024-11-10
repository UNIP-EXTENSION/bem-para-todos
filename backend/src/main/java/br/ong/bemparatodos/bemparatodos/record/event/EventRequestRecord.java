package br.ong.bemparatodos.bemparatodos.record.event;

import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;

import java.util.UUID;

public record EventRequestRecord(
    UUID uuid,
    String name,
    FileRecord fileRecord
) {}
