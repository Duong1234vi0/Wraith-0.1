/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.configbuilder.CommentedProperties
 *  de.maxhenkel.configbuilder.CommentedPropertyConfig
 */
package mods.voicechat.config;

import de.maxhenkel.configbuilder.CommentedProperties;
import de.maxhenkel.configbuilder.CommentedPropertyConfig;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public class VolumeConfig
extends CommentedPropertyConfig {
    private final Map<UUID, Double> volumes;
    private final Map<String, Double> categoryVolumes;

    public VolumeConfig(Path path) {
        super(new CommentedProperties(false));
        this.path = path;
        this.reload();
        this.properties.setHeaderComments(Collections.singletonList(String.format("%s volume config", CommonCompatibilityManager.INSTANCE.getModName())));
        Map entries = this.getEntries();
        this.volumes = new HashMap<UUID, Double>();
        this.categoryVolumes = new HashMap<String, Double>();
        for (Map.Entry entry : entries.entrySet()) {
            this.properties.setComments((String)entry.getKey(), Collections.emptyList());
            try {
                double volume = Double.parseDouble((String)entry.getValue());
                try {
                    this.volumes.put(UUID.fromString((String)entry.getKey()), volume);
                }
                catch (IllegalArgumentException e) {
                    this.categoryVolumes.put((String)entry.getKey(), volume);
                }
            }
            catch (NumberFormatException e) {
                Voicechat.LOGGER.warn("Invalid volume value '{}' for '{}'", entry.getValue(), entry.getKey());
                this.properties.remove(entry.getKey());
            }
        }
        this.saveSync();
    }

    public double getPlayerVolume(UUID uuid, double def) {
        Double volume = this.volumes.get(uuid);
        if (volume == null) {
            return def;
        }
        return volume;
    }

    public double getPlayerVolume(UUID playerID) {
        return this.getPlayerVolume(playerID, 1.0);
    }

    public double setPlayerVolume(UUID uuid, double value) {
        this.volumes.put(uuid, value);
        this.properties.set(uuid.toString(), String.valueOf(value), new String[0]);
        return value;
    }

    public boolean contains(UUID uuid) {
        return this.volumes.containsKey(uuid);
    }

    public void save() {
        super.save();
        VoicechatClient.USERNAME_CACHE.saveAsync();
    }

    public Map<UUID, Double> getPlayerVolumes() {
        return this.volumes;
    }

    public double getCategoryVolume(String category, double def) {
        Double volume = this.categoryVolumes.get(category);
        if (volume == null) {
            return def;
        }
        return volume;
    }

    public double getCategoryVolume(String category) {
        return this.getCategoryVolume(category, 1.0);
    }

    public double setCategoryVolume(String category, double value) {
        this.categoryVolumes.put(category, value);
        this.properties.set(category, String.valueOf(value), new String[0]);
        return value;
    }
}

