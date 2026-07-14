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
import java.util.Locale;
import java.util.Map;
import mods.voicechat.Voicechat;
import mods.voicechat.VoicechatClient;
import mods.voicechat.intercompatibility.CommonCompatibilityManager;

public abstract class VolumeConfigBase<T>
extends CommentedPropertyConfig {
    protected final Map<T, Double> volumes;

    public VolumeConfigBase(Path path) {
        super(new CommentedProperties(false));
        this.path = path;
        this.reload();
        this.properties.setHeaderComments(Collections.singletonList(String.format("%s %s volume config", CommonCompatibilityManager.INSTANCE.getModName(), this.getConfigName())));
        Map entries = this.getEntries();
        this.volumes = new HashMap<T, Double>();
        for (Map.Entry entry : entries.entrySet()) {
            this.properties.setComments((String)entry.getKey(), Collections.emptyList());
            try {
                double volume = Double.parseDouble((String)entry.getValue());
                try {
                    this.volumes.put(this.mapKey((String)entry.getKey()), volume);
                }
                catch (Exception e) {
                    Voicechat.LOGGER.warn("Invalid volume key '{}'", entry.getKey());
                }
            }
            catch (NumberFormatException e) {
                Voicechat.LOGGER.warn("Invalid volume value '{}' for '{}'", entry.getValue(), entry.getKey());
                this.properties.remove(entry.getKey());
            }
        }
        this.saveSync();
    }

    protected abstract String getConfigName();

    protected abstract T mapKey(String var1) throws Exception;

    protected abstract String serializeKey(T var1);

    protected Double getDefaultValue() {
        return 1.0;
    }

    public double getVolume(T key, double def) {
        Double volume = this.volumes.get(key);
        if (volume == null) {
            return def;
        }
        return volume;
    }

    public double getVolume(T key) {
        return this.getVolume(key, this.getDefaultValue());
    }

    public double setVolume(T key, double value, String ... comments) {
        this.volumes.put(key, value);
        this.properties.set(this.serializeKey(key), String.format(Locale.ROOT, "%.3f", value), comments);
        return value;
    }

    public boolean contains(T key) {
        return this.volumes.containsKey(key);
    }

    public void save() {
        super.save();
        VoicechatClient.USERNAME_CACHE.saveAsync();
    }

    public Map<T, Double> getVolumes() {
        return this.volumes;
    }
}

