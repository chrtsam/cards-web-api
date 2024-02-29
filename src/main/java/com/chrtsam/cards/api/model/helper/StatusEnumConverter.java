package com.chrtsam.cards.api.model.helper;

import com.chrtsam.cards.api.model.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Chris
 */
@Converter(autoApply = true)
public class StatusEnumConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return null == status ? null : status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String databaseValue) {
        return Status.getFromValue(databaseValue)
                .orElse(null);
    }
}
