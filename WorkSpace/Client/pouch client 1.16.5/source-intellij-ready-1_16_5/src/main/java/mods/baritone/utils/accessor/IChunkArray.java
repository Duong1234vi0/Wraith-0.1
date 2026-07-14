/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.accessor;

import java.util.concurrent.atomic.AtomicReferenceArray;
import lightning.product.H_1748_a;

public interface IChunkArray {
    public void copyFrom(IChunkArray var1);

    public AtomicReferenceArray<H_1748_a> getChunks();

    public int centerX();

    public int centerZ();

    public int viewDistance();
}

