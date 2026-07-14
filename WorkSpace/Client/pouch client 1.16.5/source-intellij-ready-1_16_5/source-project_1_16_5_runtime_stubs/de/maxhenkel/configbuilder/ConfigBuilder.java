package de.maxhenkel.configbuilder;

import de.maxhenkel.configbuilder.entry.ConfigEntry;
import de.maxhenkel.configbuilder.entry.BooleanConfigEntry;
import de.maxhenkel.configbuilder.entry.DoubleConfigEntry;
import de.maxhenkel.configbuilder.entry.EnumConfigEntry;
import de.maxhenkel.configbuilder.entry.IntegerConfigEntry;
import de.maxhenkel.configbuilder.entry.StringConfigEntry;
import java.nio.file.Path;
import java.util.function.Function;

public interface ConfigBuilder {
    static <T> Builder builder(Function<ConfigBuilder, T> factory) {
        return new Builder(factory);
    }

    ConfigBuilder header(String[] header);

    IntegerConfigEntry integerEntry(String key, Integer value, String[] comments);

    IntegerConfigEntry integerEntry(String key, Integer value, Integer min, Integer max, String[] comments);

    BooleanConfigEntry booleanEntry(String key, Boolean value, String[] comments);

    StringConfigEntry stringEntry(String key, String value, String[] comments);

    DoubleConfigEntry doubleEntry(String key, Double value, Double min, Double max, String[] comments);

    <T extends Enum<T>> EnumConfigEntry<T> enumEntry(String key, T value, String[] comments);

    class Builder implements ConfigBuilder {
        private final Function<ConfigBuilder, ?> factory;
        private Path path;

        private Builder(Function<ConfigBuilder, ?> factory) {
            this.factory = factory;
        }

        public Builder path(Path path) {
            this.path = path;
            return this;
        }

        @Override
        public Builder header(String[] header) {
            return this;
        }

        public Object build() {
            return factory.apply(this);
        }

        @Override
        public IntegerConfigEntry integerEntry(String key, Integer value, String[] comments) {
            return new IntegerConfigEntry(key, value);
        }

        @Override
        public IntegerConfigEntry integerEntry(String key, Integer value, Integer min, Integer max, String[] comments) {
            return new IntegerConfigEntry(key, value);
        }

        @Override
        public BooleanConfigEntry booleanEntry(String key, Boolean value, String[] comments) {
            return new BooleanConfigEntry(key, value);
        }

        @Override
        public StringConfigEntry stringEntry(String key, String value, String[] comments) {
            return new StringConfigEntry(key, value);
        }

        @Override
        public DoubleConfigEntry doubleEntry(String key, Double value, Double min, Double max, String[] comments) {
            return new DoubleConfigEntry(key, value);
        }

        @Override
        @SuppressWarnings({"rawtypes", "unchecked"})
        public <T extends Enum<T>> EnumConfigEntry<T> enumEntry(String key, T value, String[] comments) {
            return new EnumConfigEntry(key, value);
        }
    }
}
