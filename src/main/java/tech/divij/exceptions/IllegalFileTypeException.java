package tech.divij.exceptions;

public class IllegalFileTypeException extends RuntimeException {

  public IllegalFileTypeException() {
    super();
  }

  public IllegalFileTypeException(String s) {
    super(s);
  }
}
