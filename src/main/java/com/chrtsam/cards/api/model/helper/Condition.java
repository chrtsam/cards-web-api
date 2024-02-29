package com.chrtsam.cards.api.model.helper;

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
    private String operator;

    public Condition(String key, String operator, Object value) {
        this.key = key;
        this.value = value;
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
