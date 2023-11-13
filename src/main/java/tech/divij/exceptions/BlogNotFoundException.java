package tech.divij.exceptions;

public class BlogNotFoundException extends RuntimeException {

  public BlogNotFoundException(String message) {
    super(message);
  }
}
