package br.ong.bemparatodos.bemparatodos.service.exception.resource;


public class ResourceInvalidException extends ResourceException {

  public ResourceInvalidException(String message) {
    super(message);
  }

  public ResourceInvalidException(String message, Throwable cause) {
    super(message, cause);
  }


  public ResourceInvalidException(Throwable cause) {
    super(cause);
  }
}
