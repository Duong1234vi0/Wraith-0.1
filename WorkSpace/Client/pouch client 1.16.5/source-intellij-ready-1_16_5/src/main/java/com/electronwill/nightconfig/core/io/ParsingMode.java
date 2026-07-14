/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.io;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.IncompatibleIntermediaryLevelException;
import com.electronwill.nightconfig.core.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public enum ParsingMode {
    REPLACE(Config::clear, Config::set, Map::put),
    MERGE(c -> {}, (cfg, path, value) -> {
        try {
            return cfg.set(path, value);
        }
        catch (IncompatibleIntermediaryLevelException ex) {
            for (int i = path.size(); i > 0; --i) {
                List<String> prefix = path.subList(0, i);
                cfg.remove(prefix);
                try {
                    return cfg.set(path, value);
                }
                catch (IncompatibleIntermediaryLevelException incompatibleIntermediaryLevelException) {
                    continue;
                }
            }
            return null;
        }
    }, Map::put),
    ADD(c -> {}, (cfg, path, value) -> {
        try {
            cfg.add(path, value);
        }
        catch (IncompatibleIntermediaryLevelException incompatibleIntermediaryLevelException) {
            // empty catch block
        }
        return null;
    }, Map::putIfAbsent);

    private final Consumer<? super Config> preparationAction;
    private final PutAction putAction;
    private final MapPutAction mapPutAction;

    private ParsingMode(Consumer<? super Config> preparationAction, PutAction putAction, MapPutAction mapPutAction) {
        this.preparationAction = preparationAction;
        this.putAction = putAction;
        this.mapPutAction = mapPutAction;
    }

    public void prepareParsing(Config config) {
        this.preparationAction.accept(config);
    }

    public Object put(Config config, List<String> key, Object value) {
        return this.putAction.put(config, key, value);
    }

    public Object put(Config config, String key, Object value) {
        return this.putAction.put(config, key, value);
    }

    public Object put(Map<String, Object> map, String key, Object value) {
        return this.mapPutAction.put(map, key, value);
    }

    @FunctionalInterface
    private static interface PutAction {
        public Object put(Config var1, List<String> var2, Object var3);

        default public Object put(Config config, String key, Object value) {
            return this.put(config, StringUtils.split(key, '.'), value);
        }
    }

    @FunctionalInterface
    private static interface MapPutAction {
        public Object put(Map<String, Object> var1, String var2, Object var3);
    }
}

