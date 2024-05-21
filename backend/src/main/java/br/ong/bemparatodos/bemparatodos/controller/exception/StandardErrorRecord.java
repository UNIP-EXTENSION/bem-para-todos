package br.ong.bemparatodos.bemparatodos.controller.exception;

import java.time.Instant;
import java.util.Collection;

public record StandardErrorRecord(
   Instant timestamp,
   Integer status,
   Collection<String> errors,
   String path

) {

}