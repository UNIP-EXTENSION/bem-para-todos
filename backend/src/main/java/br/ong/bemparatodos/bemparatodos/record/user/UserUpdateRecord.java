package br.ong.bemparatodos.bemparatodos.record.user;

public record UserUpdateRecord(
    String firstName,
    String lastName,
    String email,
    String password
) {
}
