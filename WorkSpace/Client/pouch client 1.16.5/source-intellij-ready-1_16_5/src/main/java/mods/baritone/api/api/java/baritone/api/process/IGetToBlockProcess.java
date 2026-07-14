/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import lightning.product.T_2915_h;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;

public interface IGetToBlockProcess
extends IBaritoneProcess {
    public void getToBlock(BlockOptionalMeta var1);

    default public void getToBlock(T_2915_h block) {
        this.getToBlock(new BlockOptionalMeta(block));
    }

    public boolean blacklistClosest();
}

