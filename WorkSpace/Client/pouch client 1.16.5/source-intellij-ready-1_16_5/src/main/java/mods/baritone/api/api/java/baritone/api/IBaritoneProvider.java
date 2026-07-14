/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api;

import java.util.List;
import java.util.Objects;
import lightning.product.V_772_m;
import lightning.product.MinecraftClient;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.cache.IWorldScanner;
import mods.baritone.api.api.java.baritone.api.command.ICommandSystem;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematicSystem;

public interface IBaritoneProvider {
    public IBaritone getPrimaryBaritone();

    public List<IBaritone> getAllBaritones();

    default public IBaritone getBaritoneForPlayer(V_772_m player) {
        for (IBaritone baritone : this.getAllBaritones()) {
            if (!Objects.equals(player, baritone.getPlayerContext().player())) continue;
            return baritone;
        }
        return null;
    }

    default public IBaritone getBaritoneForMinecraft(MinecraftClient minecraft) {
        for (IBaritone baritone : this.getAllBaritones()) {
            if (!Objects.equals(minecraft, baritone.getPlayerContext().minecraft())) continue;
            return baritone;
        }
        return null;
    }

    public IBaritone createBaritone(MinecraftClient var1);

    public boolean destroyBaritone(IBaritone var1);

    public IWorldScanner getWorldScanner();

    public ICommandSystem getCommandSystem();

    public ISchematicSystem getSchematicSystem();
}


