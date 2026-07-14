/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.b_257_Y;
import lightning.product.c_1514_x;

public enum BlockDir {
    DOWN(b_257_Y.n_1700_B),
    UP(b_257_Y.J_1907_R),
    NORTH(b_257_Y.R_4764_Y),
    SOUTH(b_257_Y.G_564_y),
    WEST(b_257_Y.P_1922_E),
    EAST(b_257_Y.u_1723_Y),
    NORTH_WEST(b_257_Y.R_4764_Y, b_257_Y.P_1922_E),
    NORTH_EAST(b_257_Y.R_4764_Y, b_257_Y.u_1723_Y),
    SOUTH_WEST(b_257_Y.G_564_y, b_257_Y.P_1922_E),
    SOUTH_EAST(b_257_Y.G_564_y, b_257_Y.u_1723_Y),
    DOWN_NORTH(b_257_Y.n_1700_B, b_257_Y.R_4764_Y),
    DOWN_SOUTH(b_257_Y.n_1700_B, b_257_Y.G_564_y),
    UP_NORTH(b_257_Y.J_1907_R, b_257_Y.R_4764_Y),
    UP_SOUTH(b_257_Y.J_1907_R, b_257_Y.G_564_y),
    DOWN_WEST(b_257_Y.n_1700_B, b_257_Y.P_1922_E),
    DOWN_EAST(b_257_Y.n_1700_B, b_257_Y.u_1723_Y),
    UP_WEST(b_257_Y.J_1907_R, b_257_Y.P_1922_E),
    UP_EAST(b_257_Y.J_1907_R, b_257_Y.u_1723_Y);

    private b_257_Y facing1;
    private b_257_Y facing2;

    private BlockDir(b_257_Y facing1) {
        this.facing1 = facing1;
    }

    private BlockDir(b_257_Y facing1, b_257_Y facing2) {
        this.facing1 = facing1;
        this.facing2 = facing2;
    }

    public b_257_Y getFacing1() {
        return this.facing1;
    }

    public b_257_Y getFacing2() {
        return this.facing2;
    }

    c_1514_x offset(c_1514_x pos) {
        pos = pos.offset(this.facing1, 1);
        if (this.facing2 != null) {
            pos = pos.offset(this.facing2, 1);
        }
        return pos;
    }

    public int getOffsetX() {
        int i = this.facing1.t_148_a();
        if (this.facing2 != null) {
            i += this.facing2.t_148_a();
        }
        return i;
    }

    public int getOffsetY() {
        int i = this.facing1.s_956_w();
        if (this.facing2 != null) {
            i += this.facing2.s_956_w();
        }
        return i;
    }

    public int getOffsetZ() {
        int i = this.facing1.u_2550_I();
        if (this.facing2 != null) {
            i += this.facing2.u_2550_I();
        }
        return i;
    }

    public boolean isDouble() {
        return this.facing2 != null;
    }
}

