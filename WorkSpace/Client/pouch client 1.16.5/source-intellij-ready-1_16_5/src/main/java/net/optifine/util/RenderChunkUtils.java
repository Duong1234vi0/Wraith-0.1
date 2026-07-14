/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.P_3550_Z;
import lightning.product.u_530_F;
import lightning.product.z_4547_I;

public class RenderChunkUtils {
    public static int getCountBlocks(z_4547_I.n_1700_B renderChunk) {
        P_3550_Z[] achunksection = renderChunk.P_4830_p().getSections();
        if (achunksection == null) {
            return 0;
        }
        int i = renderChunk.P_1922_E().getY() >> 4;
        P_3550_Z chunksection = achunksection[i];
        return chunksection == null ? (short)0 : chunksection.u_2550_I();
    }

    public static double getRelativeBufferSize(z_4547_I.n_1700_B renderChunk) {
        int i = RenderChunkUtils.getCountBlocks(renderChunk);
        return RenderChunkUtils.getRelativeBufferSize(i);
    }

    public static double getRelativeBufferSize(int blockCount) {
        double d0 = (double)blockCount / 4096.0;
        double d1 = (d0 *= 0.995) * 2.0 - 1.0;
        d1 = u_530_F.n_1700_B(d1, -1.0, 1.0);
        return u_530_F.n_1700_B(1.0 - d1 * d1);
    }
}

