package br.ong.bemparatodos.bemparatodos.service.exception.resource;

public class ResourceException extends RuntimeException {

  public ResourceException(String message) {
    super(message);
  }

  public ResourceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceException(Throwable cause) {
    super(cause);
  }

}
