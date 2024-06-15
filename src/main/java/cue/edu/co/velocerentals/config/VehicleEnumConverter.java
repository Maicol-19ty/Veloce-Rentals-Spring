package cue.edu.co.velocerentals.config;

import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VehicleEnumConverter implements AttributeConverter<VehicleStatus, String> {
    // Public class for converting VehicleStatus enum to a String for database storage
    @Override
    public String convertToDatabaseColumn(VehicleStatus attribute) {
        return attribute == null ? null : attribute.getStatus();
    }

    @Override
    public VehicleStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : VehicleStatus.fromString(dbData);
    }
}
