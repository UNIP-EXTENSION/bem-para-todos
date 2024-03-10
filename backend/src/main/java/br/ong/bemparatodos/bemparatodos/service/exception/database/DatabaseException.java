package br.ong.bemparatodos.bemparatodos.service.exception.database;

public class DatabaseException extends RuntimeException {

  public DatabaseException(String message) {
    super(message);
  }

  public DatabaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public DatabaseException(Throwable cause) {
    super(cause);
  }
}
