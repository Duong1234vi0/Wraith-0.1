/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.util.Comparator;
import lightning.product.Y_1387_d;
import lightning.product.u_530_F;

public class ChunkPosComparator
implements Comparator<Y_1387_d> {
    private int chunkPosX;
    private int chunkPosZ;
    private double yawRad;
    private double pitchNorm;

    public ChunkPosComparator(int chunkPosX, int chunkPosZ, double yawRad, double pitchRad) {
        this.chunkPosX = chunkPosX;
        this.chunkPosZ = chunkPosZ;
        this.yawRad = yawRad;
        this.pitchNorm = 1.0 - u_530_F.n_1700_B(Math.abs(pitchRad) / 1.5707963267948966, 0.0, 1.0);
    }

    @Override
    public int compare(Y_1387_d cp1, Y_1387_d cp2) {
        int i = this.getDistSq(cp1);
        int j = this.getDistSq(cp2);
        return i - j;
    }

    private int getDistSq(Y_1387_d cp) {
        int i = cp.J_1907_R - this.chunkPosX;
        int j = cp.R_4764_Y - this.chunkPosZ;
        int k = i * i + j * j;
        double d0 = u_530_F.G_564_y((double)j, (double)i);
        double d1 = Math.abs(d0 - this.yawRad);
        if (d1 > Math.PI) {
            d1 = Math.PI * 2 - d1;
        }
        return (int)((double)k * 1000.0 * this.pitchNorm * d1 * d1);
    }
}

