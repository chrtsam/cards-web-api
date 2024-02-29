package com.chrtsam.cards.api.model.helper;

import com.chrtsam.cards.api.model.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Chris
 */
@Converter(autoApply = true)
public class RoleEnumConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return null == role ? null : role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String databaseValue) {
        return Role.getFromValue(databaseValue)
                .orElse(null);
    }
}
