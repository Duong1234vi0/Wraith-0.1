/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.schematic;

import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;

public interface ISchematic {
    default public boolean inSchematic(int x, int y, int z, K_4074_S currentState) {
        return x >= 0 && x < this.widthX() && y >= 0 && y < this.heightY() && z >= 0 && z < this.lengthZ();
    }

    default public int size(b_257_Y.n_1700_B axis) {
        switch (axis) {
            case n_1700_B: {
                return this.widthX();
            }
            case J_1907_R: {
                return this.heightY();
            }
            case R_4764_Y: {
                return this.lengthZ();
            }
        }
        throw new UnsupportedOperationException(String.valueOf(axis));
    }

    public K_4074_S desiredState(int var1, int var2, int var3, K_4074_S var4, List<K_4074_S> var5);

    default public void reset() {
    }

    public int widthX();

    public int heightY();

    public int lengthZ();
}

