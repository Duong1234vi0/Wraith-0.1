package de.maxhenkel.configbuilder.entry;

public class EnumConfigEntry<T extends Enum<T>> extends AbstractConfigEntry<T> {
    public EnumConfigEntry(String key, T value) {
        super(key, value);
    }
}
