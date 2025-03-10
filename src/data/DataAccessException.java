package data;

public class DataAccessException extends Exception {

  public DataAccessException(Throwable cause) {
    super(cause);
  }

  public DataAccessException(String message) {
    super(message);
  }
}
