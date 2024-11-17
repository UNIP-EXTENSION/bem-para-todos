package br.ong.bemparatodos.bemparatodos.providers.exceptions;

public class JWTValidateException extends ValidateException {

  public JWTValidateException() {
  }

  public JWTValidateException(String message) {
    super(message);
  }

  public JWTValidateException(String message, Throwable cause) {
    super(message, cause);
  }
}
