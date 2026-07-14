/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import lightning.product.K_4074_S;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;
import mods.baritone.api.api.java.baritone.api.schematic.MaskSchematic;

public class ShellSchematic
extends MaskSchematic {
    public ShellSchematic(ISchematic schematic) {
        super(schematic);
    }

    @Override
    protected boolean partOfMask(int x, int y, int z, K_4074_S currentState) {
        return x == 0 || y == 0 || z == 0 || x == this.widthX() - 1 || y == this.heightY() - 1 || z == this.lengthZ() - 1;
    }
}

