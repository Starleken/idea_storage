package ru.leafall.accountservice.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.leafall.accountservice.entity.Role;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class RoleSetConverter implements AttributeConverter<Set<Role>, String> {
    @Override
    public String convertToDatabaseColumn(Set<Role> roles) {
        return roles == null ? null : roles.stream()
                .map(Role::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Role> convertToEntityAttribute(String dbData) {
        return dbData == null ? null
                : Arrays.stream(dbData.split(",")).map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
