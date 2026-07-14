/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.LevelData;
import lightning.product.c_1514_x;

public interface WritableLevelData
extends LevelData {
    public void n_1700_B(int var1);

    public void J_1907_R(int var1);

    public void R_4764_Y(int var1);

    public void n_1700_B(float var1);

    default public void n_1700_B(c_1514_x spawnPoint, float angle) {
        this.n_1700_B(spawnPoint.getX());
        this.J_1907_R(spawnPoint.getY());
        this.R_4764_Y(spawnPoint.getZ());
        this.n_1700_B(angle);
    }
}


