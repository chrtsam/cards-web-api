package com.chrtsam.cards.api.model.helper;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Chris
 */
public class Condition {

    public static final String EQUALS = "eq";
    public static final String EQUALS_IGNORE_CASE = "like";
    public static final String GREATER_THAN = "gt";
    public static final String LESS_THAN = "lt";

    private String key;
    private Object value;
    @Schema(description = "Currently supported operatotions are. equals[eq], contains[like] for string text values, greater than [gt], lower than [lt] for date values")
    private String operator;

    public Condition() {
    }

    public Condition(String key, String operator, Object value) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public Condition(String key, String operator) {
        this.key = key;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
