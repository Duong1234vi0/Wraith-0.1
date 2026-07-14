/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.selection;

import lightning.product.I_4817_s;
import lightning.product.b_257_Y;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public interface ISelection {
    public BetterBlockPos pos1();

    public BetterBlockPos pos2();

    public BetterBlockPos min();

    public BetterBlockPos max();

    public z_3539_x size();

    public I_4817_s aabb();

    public ISelection expand(b_257_Y var1, int var2);

    public ISelection contract(b_257_Y var1, int var2);

    public ISelection shift(b_257_Y var1, int var2);
}

