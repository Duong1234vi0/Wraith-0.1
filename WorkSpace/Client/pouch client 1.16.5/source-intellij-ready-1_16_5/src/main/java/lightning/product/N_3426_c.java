/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.c_1514_x;
import lightning.product.j_2368_m;

public class N_3426_c
extends j_2368_m {
    private final c_1514_x n_1700_B = null;
    private final c_1514_x J_1907_R = null;
    private final long R_4764_Y = 0L;

    @Override
    public String getMessage() {
        String s = this.n_1700_B.getX() + "," + this.n_1700_B.getY() + "," + this.n_1700_B.getZ() + " (relative: " + this.J_1907_R.getX() + "," + this.J_1907_R.getY() + "," + this.J_1907_R.getZ() + ")";
        return super.getMessage() + " at " + s + " (t=" + this.R_4764_Y + ")";
    }

    @Nullable
    public String n_1700_B() {
        return super.getMessage() + " here";
    }

    @Nullable
    public c_1514_x J_1907_R() {
        return this.n_1700_B;
    }

    private N_3426_c() {
        super("Synthetic constructor added by MCP, do not call");
        throw new RuntimeException("Synthetic constructor added by MCP, do not call");
    }
}

