package br.ong.bemparatodos.bemparatodos.service.exception.resource;

public class FileConvertException extends ResourceException {
  public FileConvertException(String message) {
    super(message);
  }

  public FileConvertException(String message, Throwable cause) {
    super(message, cause);
  }

  public FileConvertException(Throwable cause) {
    super(cause);
  }
}
