package com.chrtsam.cards.api.model;

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Chris
 */
public enum Role {
    ADMIN("Admin"),
    MEMBER("Member");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Optional<Role> getFromValue(String value) {
        return Optional.ofNullable(value)
                .flatMap(dv -> Arrays.stream(Role.values())
                .filter(ev -> dv.equals(ev.value))
                .findFirst());
    }
}
