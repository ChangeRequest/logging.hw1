package com.github.didva.strore.model;

public class Property {

    private String key;
    private String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }
}
