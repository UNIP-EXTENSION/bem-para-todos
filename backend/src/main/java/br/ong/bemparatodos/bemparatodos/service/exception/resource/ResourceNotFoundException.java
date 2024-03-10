package br.ong.bemparatodos.bemparatodos.service.exception.resource;


public class ResourceNotFoundException extends ResourceException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }


  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
