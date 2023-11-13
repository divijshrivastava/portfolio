package tech.divij.mapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import tech.divij.dto.ChangeFrequency;

@Converter(autoApply = true)
public class ChangeFrequencyConverter implements AttributeConverter<ChangeFrequency, String> {

  @Override
  public String convertToDatabaseColumn(ChangeFrequency changeFrequency) {
    return changeFrequency.getValue();
  }

  @Override
  public ChangeFrequency convertToEntityAttribute(String dbData) {
    return ChangeFrequency.fromValue(dbData);
  }
}
