package br.ong.bemparatodos.bemparatodos.util.token;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static java.util.Objects.isNull;

@Slf4j
public final class TokenUtils {

  public static String encode(final String input) {
    if (isNull(input) || input.isEmpty()) {
      throw new IllegalArgumentException("Input cannot be null or empty");
    }

    try {
      final byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
      String encodedString = new String(encodedBytes, StandardCharsets.UTF_8);
      return encodedString;
    } catch (Exception e) {
      throw new RuntimeException("Error while encoding string", e);
    }
  }
}
