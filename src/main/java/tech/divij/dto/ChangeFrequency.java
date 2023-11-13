package tech.divij.dto;

public enum ChangeFrequency {
  ALWAYS("always"),
  HOURLY("hourly"),
  DAILY("daily"),
  WEEKLY("weekly"),
  MONTHLY("monthly"),
  YEARLY("yearly"),
  NEVER("never");

  private final String value;

  ChangeFrequency(String value) {
    this.value = value;
  }

  public static ChangeFrequency fromValue(String value) {
    for (ChangeFrequency frequency : values()) {
      if (frequency.name().equalsIgnoreCase(value)) {
        return frequency;
      }
    }
    throw new IllegalArgumentException("Invalid change frequency value: " + value);
  }

  public String getValue() {
    return value;
  }
}
