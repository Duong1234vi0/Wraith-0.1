package de.maxhenkel.configbuilder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommentedProperties {
    private final Map<String, String> values = new LinkedHashMap<>();

    public CommentedProperties(boolean ignored) {
    }

    public CommentedProperties setHeaderComments(List<String> comments) {
        return this;
    }

    public CommentedProperties setComments(String key, List<String> comments) {
        return this;
    }

    public CommentedProperties set(String key, String value, String... comments) {
        values.put(key, value);
        return this;
    }

    public String get(String key) {
        return values.get(key);
    }

    public CommentedProperties remove(Object key) {
        values.remove(String.valueOf(key));
        return this;
    }

    public Map<String, String> entries() {
        return Collections.unmodifiableMap(values);
    }
}
