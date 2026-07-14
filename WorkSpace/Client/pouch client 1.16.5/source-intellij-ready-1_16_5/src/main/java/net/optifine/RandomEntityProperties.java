/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.ArrayList;
import java.util.Properties;
import lightning.product.g_2336_b;
import net.optifine.Config;
import net.optifine.IRandomEntity;
import net.optifine.RandomEntityRule;
import net.optifine.config.ConnectedParser;

public class RandomEntityProperties {
    public String name = null;
    public String basePath = null;
    public g_2336_b[] resourceLocations = null;
    public RandomEntityRule[] rules = null;

    public RandomEntityProperties(String path, g_2336_b[] variants) {
        ConnectedParser connectedparser = new ConnectedParser("RandomEntities");
        this.name = connectedparser.parseName(path);
        this.basePath = connectedparser.parseBasePath(path);
        this.resourceLocations = variants;
    }

    public RandomEntityProperties(Properties props, String path, g_2336_b baseResLoc) {
        ConnectedParser connectedparser = new ConnectedParser("RandomEntities");
        this.name = connectedparser.parseName(path);
        this.basePath = connectedparser.parseBasePath(path);
        this.rules = this.parseRules(props, path, baseResLoc, connectedparser);
    }

    public g_2336_b getTextureLocation(g_2336_b loc, IRandomEntity randomEntity) {
        if (this.rules != null) {
            for (int i = 0; i < this.rules.length; ++i) {
                RandomEntityRule randomentityrule = this.rules[i];
                if (!randomentityrule.matches(randomEntity)) continue;
                return randomentityrule.getTextureLocation(loc, randomEntity.getId());
            }
        }
        if (this.resourceLocations != null) {
            int j = randomEntity.getId();
            int k = j % this.resourceLocations.length;
            return this.resourceLocations[k];
        }
        return loc;
    }

    private RandomEntityRule[] parseRules(Properties props, String pathProps, g_2336_b baseResLoc, ConnectedParser cp) {
        ArrayList<RandomEntityRule> list = new ArrayList<RandomEntityRule>();
        int i = props.size();
        for (int j = 0; j < i; ++j) {
            RandomEntityRule randomentityrule;
            int k = j + 1;
            String s = props.getProperty("textures." + k);
            if (s == null) {
                s = props.getProperty("skins." + k);
            }
            if (s == null || !(randomentityrule = new RandomEntityRule(props, pathProps, baseResLoc, k, s, cp)).isValid(pathProps)) continue;
            list.add(randomentityrule);
        }
        return list.toArray(new RandomEntityRule[list.size()]);
    }

    public boolean isValid(String path) {
        if (this.resourceLocations == null && this.rules == null) {
            Config.warn("No skins specified: " + path);
            return false;
        }
        if (this.rules != null) {
            for (int i = 0; i < this.rules.length; ++i) {
                RandomEntityRule randomentityrule = this.rules[i];
                if (randomentityrule.isValid(path)) continue;
                return false;
            }
        }
        if (this.resourceLocations != null) {
            for (int j = 0; j < this.resourceLocations.length; ++j) {
                g_2336_b resourcelocation = this.resourceLocations[j];
                if (Config.hasResource(resourcelocation)) continue;
                Config.warn("Texture not found: " + resourcelocation.J_1907_R());
                return false;
            }
        }
        return true;
    }

    public boolean isDefault() {
        if (this.rules != null) {
            return false;
        }
        return this.resourceLocations == null;
    }
}

