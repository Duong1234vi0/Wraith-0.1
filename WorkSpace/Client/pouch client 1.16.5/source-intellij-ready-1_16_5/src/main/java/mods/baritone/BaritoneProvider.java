/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.MinecraftClient;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.IBaritoneProvider;
import mods.baritone.api.api.java.baritone.api.cache.IWorldScanner;
import mods.baritone.api.api.java.baritone.api.command.ICommandSystem;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematicSystem;
import mods.baritone.cache.FasterWorldScanner;
import mods.baritone.command.CommandSystem;
import mods.baritone.command.ExampleBaritoneControl;
import mods.baritone.utils.schematic.SchematicSystem;

public final class BaritoneProvider
implements IBaritoneProvider {
    private final List<IBaritone> all = new CopyOnWriteArrayList<IBaritone>();
    private final List<IBaritone> allView = Collections.unmodifiableList(this.all);

    public BaritoneProvider() {
        Baritone primary = (Baritone)this.createBaritone(MinecraftClient.A_4115_X());
        primary.registerBehavior(ExampleBaritoneControl::new);
    }

    @Override
    public IBaritone getPrimaryBaritone() {
        return this.all.get(0);
    }

    @Override
    public List<IBaritone> getAllBaritones() {
        return this.allView;
    }

    @Override
    public synchronized IBaritone createBaritone(MinecraftClient minecraft) {
        IBaritone baritone = this.getBaritoneForMinecraft(minecraft);
        if (baritone == null) {
            baritone = new Baritone(minecraft);
            this.all.add(baritone);
        }
        return baritone;
    }

    @Override
    public synchronized boolean destroyBaritone(IBaritone baritone) {
        return baritone != this.getPrimaryBaritone() && this.all.remove(baritone);
    }

    @Override
    public IWorldScanner getWorldScanner() {
        return FasterWorldScanner.INSTANCE;
    }

    @Override
    public ICommandSystem getCommandSystem() {
        return CommandSystem.INSTANCE;
    }

    @Override
    public ISchematicSystem getSchematicSystem() {
        return SchematicSystem.INSTANCE;
    }
}


