/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.util.List;
import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.AbstractSchematic;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;
import mods.baritone.api.api.java.baritone.api.schematic.mask.Mask;

public abstract class MaskSchematic
extends AbstractSchematic {
    private final ISchematic schematic;

    public MaskSchematic(ISchematic schematic) {
        super(schematic.widthX(), schematic.heightY(), schematic.lengthZ());
        this.schematic = schematic;
    }

    protected abstract boolean partOfMask(int var1, int var2, int var3, K_4074_S var4);

    @Override
    public boolean inSchematic(int x, int y, int z, K_4074_S currentState) {
        return this.schematic.inSchematic(x, y, z, currentState) && this.partOfMask(x, y, z, currentState);
    }

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        return this.schematic.desiredState(x, y, z, current, approxPlaceable);
    }

    public static MaskSchematic create(ISchematic schematic, final Mask function) {
        return new MaskSchematic(schematic){

            @Override
            protected boolean partOfMask(int x, int y, int z, K_4074_S currentState) {
                return function.partOfMask(x, y, z, currentState);
            }
        };
    }
}

