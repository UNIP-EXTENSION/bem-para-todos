package br.ong.bemparatodos.bemparatodos.providers.exceptions;

public class ValidateException extends RuntimeException {

  public ValidateException() {
  }

  public ValidateException(String message) {
    super(message);
  }

  public ValidateException(String message, Throwable cause) {
    super(message, cause);
  }
}
