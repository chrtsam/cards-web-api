package com.chrtsam.cards.api.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author Chris
 */
public enum Status {
    TODO("To Do"),
    INPROGRESS("In Progress"),
    DONE("Done");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Optional<Status> getFromValue(String value) {
        return Optional.ofNullable(value)
                .flatMap(dv -> Arrays.stream(Status.values())
                .filter(ev -> dv.equals(ev.value))
                .findFirst());
    }
}
