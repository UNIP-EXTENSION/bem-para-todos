package br.ong.bemparatodos.bemparatodos.record.user.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record UserAuthResponse(

    @JsonProperty("access_token")
    String accessToken,

    @JsonProperty("expire_in")
    Long expireIn,

    @JsonProperty("roles")
    Set<String> roles,

    @JsonProperty("userInfo")
    UserInfoResponse userInfo
) {
}
