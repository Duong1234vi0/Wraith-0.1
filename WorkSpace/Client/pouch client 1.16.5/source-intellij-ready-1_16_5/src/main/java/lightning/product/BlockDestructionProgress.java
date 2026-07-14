/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1514_x;

public class BlockDestructionProgress
implements Comparable<BlockDestructionProgress> {
    private final int n_1700_B;
    private final c_1514_x J_1907_R;
    private int R_4764_Y;
    private int G_564_y;

    public BlockDestructionProgress(int miningPlayerEntIdIn, c_1514_x positionIn) {
        this.n_1700_B = miningPlayerEntIdIn;
        this.J_1907_R = positionIn;
    }

    public c_1514_x n_1700_B() {
        return this.J_1907_R;
    }

    public void n_1700_B(int damage) {
        if (damage > 10) {
            damage = 10;
        }
        this.R_4764_Y = damage;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public void J_1907_R(int createdAtCloudUpdateTickIn) {
        this.G_564_y = createdAtCloudUpdateTickIn;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            BlockDestructionProgress destroyblockprogress = (BlockDestructionProgress)p_equals_1_;
            return this.n_1700_B == destroyblockprogress.n_1700_B;
        }
        return false;
    }

    public int hashCode() {
        return Integer.hashCode(this.n_1700_B);
    }

    public int n_1700_B(BlockDestructionProgress p_compareTo_1_) {
        return this.R_4764_Y != p_compareTo_1_.R_4764_Y ? Integer.compare(this.R_4764_Y, p_compareTo_1_.R_4764_Y) : Integer.compare(this.n_1700_B, p_compareTo_1_.n_1700_B);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((BlockDestructionProgress)object);
    }
}


