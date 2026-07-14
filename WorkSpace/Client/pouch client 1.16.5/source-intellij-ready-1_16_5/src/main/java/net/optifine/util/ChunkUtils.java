/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.H_1748_a;
import net.optifine.ChunkOF;

public class ChunkUtils {
    public static boolean hasEntities(H_1748_a chunk) {
        if (chunk instanceof ChunkOF) {
            ChunkOF chunkof = (ChunkOF)chunk;
            return chunkof.hasEntities();
        }
        return true;
    }

    public static boolean isLoaded(H_1748_a chunk) {
        if (chunk instanceof ChunkOF) {
            ChunkOF chunkof = (ChunkOF)chunk;
            return chunkof.isLoaded();
        }
        return false;
    }
}

