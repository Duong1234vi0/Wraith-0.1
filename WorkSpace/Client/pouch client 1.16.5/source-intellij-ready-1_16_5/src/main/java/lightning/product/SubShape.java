/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DiscreteVoxelShape;
import lightning.product.b_257_Y;

public final class SubShape
extends DiscreteVoxelShape {
    private final DiscreteVoxelShape G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final int t_148_a;
    private final int s_956_w;

    protected SubShape(DiscreteVoxelShape partIn, int startXIn, int startYIn, int startZIn, int endXIn, int endYIn, int endZIn) {
        super(endXIn - startXIn, endYIn - startYIn, endZIn - startZIn);
        this.G_564_y = partIn;
        this.P_1922_E = startXIn;
        this.u_1723_Y = startYIn;
        this.v_4262_N = startZIn;
        this.w_1484_f = endXIn;
        this.t_148_a = endYIn;
        this.s_956_w = endZIn;
    }

    @Override
    public boolean J_1907_R(int x, int y, int z) {
        return this.G_564_y.J_1907_R(this.P_1922_E + x, this.u_1723_Y + y, this.v_4262_N + z);
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean expandBounds, boolean filled) {
        this.G_564_y.n_1700_B(this.P_1922_E + x, this.u_1723_Y + y, this.v_4262_N + z, expandBounds, filled);
    }

    @Override
    public int n_1700_B(b_257_Y.n_1700_B axis) {
        return Math.max(0, this.G_564_y.n_1700_B(axis) - axis.n_1700_B(this.P_1922_E, this.u_1723_Y, this.v_4262_N));
    }

    @Override
    public int J_1907_R(b_257_Y.n_1700_B axis) {
        return Math.min(axis.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w), this.G_564_y.J_1907_R(axis) - axis.n_1700_B(this.P_1922_E, this.u_1723_Y, this.v_4262_N));
    }
}


