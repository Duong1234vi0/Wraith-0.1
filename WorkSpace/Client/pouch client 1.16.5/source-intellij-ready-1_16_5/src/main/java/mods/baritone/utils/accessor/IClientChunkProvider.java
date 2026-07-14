/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.utils.accessor;

import lightning.product.r_4399_U;
import mods.baritone.utils.accessor.IChunkArray;

public interface IClientChunkProvider {
    public r_4399_U createThreadSafeCopy();

    public IChunkArray extractReferenceArray();
}

