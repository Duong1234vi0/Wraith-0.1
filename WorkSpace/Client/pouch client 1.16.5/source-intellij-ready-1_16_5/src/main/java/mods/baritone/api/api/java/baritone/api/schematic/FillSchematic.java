/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.util.List;
import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.AbstractSchematic;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;

public class FillSchematic
extends AbstractSchematic {
    private final BlockOptionalMeta bom;

    public FillSchematic(int x, int y, int z, BlockOptionalMeta bom) {
        super(x, y, z);
        this.bom = bom;
    }

    public FillSchematic(int x, int y, int z, K_4074_S state) {
        this(x, y, z, new BlockOptionalMeta(state.J_1907_R()));
    }

    public BlockOptionalMeta getBom() {
        return this.bom;
    }

    @Override
    public K_4074_S desiredState(int x, int y, int z, K_4074_S current, List<K_4074_S> approxPlaceable) {
        if (this.bom.matches(current)) {
            return current;
        }
        for (K_4074_S placeable : approxPlaceable) {
            if (!this.bom.matches(placeable)) continue;
            return placeable;
        }
        return this.bom.getAnyBlockState();
    }
}

