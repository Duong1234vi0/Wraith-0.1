/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.process;

import java.util.stream.Stream;
import lightning.product.T_2915_h;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMetaLookup;

public interface IMineProcess
extends IBaritoneProcess {
    public void mineByName(int var1, String ... var2);

    public void mine(int var1, BlockOptionalMetaLookup var2);

    default public void mine(BlockOptionalMetaLookup filter) {
        this.mine(0, filter);
    }

    default public void mineByName(String ... blocks) {
        this.mineByName(0, blocks);
    }

    default public void mine(int quantity, BlockOptionalMeta ... boms) {
        this.mine(quantity, new BlockOptionalMetaLookup(boms));
    }

    default public void mine(BlockOptionalMeta ... boms) {
        this.mine(0, boms);
    }

    default public void mine(int quantity, T_2915_h ... blocks) {
        this.mine(quantity, new BlockOptionalMetaLookup((BlockOptionalMeta[])Stream.of(blocks).map(BlockOptionalMeta::new).toArray(BlockOptionalMeta[]::new)));
    }

    default public void mine(T_2915_h ... blocks) {
        this.mine(0, blocks);
    }

    default public void cancel() {
        this.onLostControl();
    }
}

