/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.schematic;

import java.util.stream.Stream;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.schematic.ISchematic;
import mods.baritone.api.api.java.baritone.api.schematic.MaskSchematic;
import mods.baritone.api.api.java.baritone.api.selection.ISelection;

public class SelectionSchematic
extends MaskSchematic {
    private final ISelection[] selections;

    public SelectionSchematic(ISchematic schematic, z_3539_x origin, ISelection[] selections) {
        super(schematic);
        this.selections = (ISelection[])Stream.of(selections).map(sel -> sel.shift(b_257_Y.P_1922_E, origin.getX()).shift(b_257_Y.n_1700_B, origin.getY()).shift(b_257_Y.R_4764_Y, origin.getZ())).toArray(ISelection[]::new);
    }

    @Override
    protected boolean partOfMask(int x, int y, int z, K_4074_S currentState) {
        for (ISelection selection : this.selections) {
            if (x < selection.min().x || y < selection.min().y || z < selection.min().z || x > selection.max().x || y > selection.max().y || z > selection.max().z) continue;
            return true;
        }
        return false;
    }
}

