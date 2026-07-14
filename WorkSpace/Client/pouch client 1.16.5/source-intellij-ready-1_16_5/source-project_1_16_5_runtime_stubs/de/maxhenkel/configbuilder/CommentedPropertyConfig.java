package de.maxhenkel.configbuilder;

import java.nio.file.Path;
import java.util.Map;

public class CommentedPropertyConfig {
    protected final CommentedProperties properties;
    protected Path path;

    public CommentedPropertyConfig(CommentedProperties properties) {
        this.properties = properties;
    }

    public void reload() {
    }

    public void save() {
    }

    public void saveSync() {
    }

    public Map<String, String> getEntries() {
        return properties.entries();
    }
}
