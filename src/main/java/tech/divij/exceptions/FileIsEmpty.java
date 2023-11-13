package tech.divij.exceptions;

public class FileIsEmpty extends RuntimeException {

  public FileIsEmpty() {
    super("The file is empty.");
  }
}
