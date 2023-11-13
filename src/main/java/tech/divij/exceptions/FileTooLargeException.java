package tech.divij.exceptions;

public class FileTooLargeException extends RuntimeException {

  public FileTooLargeException() {
    super("File is too large.");
  }
}
