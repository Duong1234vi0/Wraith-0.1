package de.maxhenkel.configbuilder.entry;

public interface ConfigEntry<T> {
    T get();

    ConfigEntry<T> set(Object value);

    ConfigEntry<T> save();

    String getKey();

    T getDefault();
}
