package br.ong.bemparatodos.bemparatodos.record.user.auth.response;

import java.util.UUID;

public record UserInfoResponse(
    UUID uuid,
    String name,
    String email
) {
}
