package de.maxhenkel.configbuilder.entry;

public abstract class AbstractConfigEntry<T> implements ConfigEntry<T> {
    protected final String key;
    protected final T defaultValue;
    protected T value;

    public AbstractConfigEntry(String key, T value) {
        this.key = key;
        this.defaultValue = value;
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ConfigEntry<T> set(Object value) {
        this.value = (T) value;
        return this;
    }

    @Override
    public ConfigEntry<T> save() {
        return this;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public T getDefault() {
        return defaultValue;
    }
}
