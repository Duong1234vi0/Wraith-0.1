/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.BitSet;
import lightning.product.IndexMerger;
import lightning.product.DiscreteVoxelShape;
import lightning.product.b_257_Y;
import lightning.product.BooleanOp;

public final class BitSetDiscreteVoxelShape
extends DiscreteVoxelShape {
    private final BitSet G_564_y;
    private int P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private int s_956_w;

    public BitSetDiscreteVoxelShape(int xSizeIn, int ySizeIn, int zSizeIn) {
        this(xSizeIn, ySizeIn, zSizeIn, xSizeIn, ySizeIn, zSizeIn, 0, 0, 0);
    }

    public BitSetDiscreteVoxelShape(int xSizeIn, int ySizeIn, int zSizeIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        super(xSizeIn, ySizeIn, zSizeIn);
        this.G_564_y = new BitSet(xSizeIn * ySizeIn * zSizeIn);
        this.P_1922_E = minX;
        this.u_1723_Y = minY;
        this.v_4262_N = minZ;
        this.w_1484_f = maxX;
        this.t_148_a = maxY;
        this.s_956_w = maxZ;
    }

    public BitSetDiscreteVoxelShape(DiscreteVoxelShape shapePart) {
        super(shapePart.n_1700_B, shapePart.J_1907_R, shapePart.R_4764_Y);
        if (shapePart instanceof BitSetDiscreteVoxelShape) {
            this.G_564_y = (BitSet)((BitSetDiscreteVoxelShape)shapePart).G_564_y.clone();
        } else {
            this.G_564_y = new BitSet(this.n_1700_B * this.J_1907_R * this.R_4764_Y);
            for (int i = 0; i < this.n_1700_B; ++i) {
                for (int j = 0; j < this.J_1907_R; ++j) {
                    for (int k = 0; k < this.R_4764_Y; ++k) {
                        if (!shapePart.J_1907_R(i, j, k)) continue;
                        this.G_564_y.set(this.n_1700_B(i, j, k));
                    }
                }
            }
        }
        this.P_1922_E = shapePart.n_1700_B(b_257_Y.n_1700_B.n_1700_B);
        this.u_1723_Y = shapePart.n_1700_B(b_257_Y.n_1700_B.J_1907_R);
        this.v_4262_N = shapePart.n_1700_B(b_257_Y.n_1700_B.R_4764_Y);
        this.w_1484_f = shapePart.J_1907_R(b_257_Y.n_1700_B.n_1700_B);
        this.t_148_a = shapePart.J_1907_R(b_257_Y.n_1700_B.J_1907_R);
        this.s_956_w = shapePart.J_1907_R(b_257_Y.n_1700_B.R_4764_Y);
    }

    protected int n_1700_B(int x, int y, int z) {
        return (x * this.J_1907_R + y) * this.R_4764_Y + z;
    }

    @Override
    public boolean J_1907_R(int x, int y, int z) {
        return this.G_564_y.get(this.n_1700_B(x, y, z));
    }

    @Override
    public void n_1700_B(int x, int y, int z, boolean expandBounds, boolean filled) {
        this.G_564_y.set(this.n_1700_B(x, y, z), filled);
        if (expandBounds && filled) {
            this.P_1922_E = Math.min(this.P_1922_E, x);
            this.u_1723_Y = Math.min(this.u_1723_Y, y);
            this.v_4262_N = Math.min(this.v_4262_N, z);
            this.w_1484_f = Math.max(this.w_1484_f, x + 1);
            this.t_148_a = Math.max(this.t_148_a, y + 1);
            this.s_956_w = Math.max(this.s_956_w, z + 1);
        }
    }

    @Override
    public boolean n_1700_B() {
        return this.G_564_y.isEmpty();
    }

    @Override
    public int n_1700_B(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.P_1922_E, this.u_1723_Y, this.v_4262_N);
    }

    @Override
    public int J_1907_R(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Override
    protected boolean n_1700_B(int fromZ, int toZ, int x, int y) {
        if (x >= 0 && y >= 0 && fromZ >= 0) {
            if (x < this.n_1700_B && y < this.J_1907_R && toZ <= this.R_4764_Y) {
                return this.G_564_y.nextClearBit(this.n_1700_B(x, y, fromZ)) >= this.n_1700_B(x, y, toZ);
            }
            return false;
        }
        return false;
    }

    @Override
    protected void n_1700_B(int fromZ, int toZ, int x, int y, boolean filled) {
        this.G_564_y.set(this.n_1700_B(x, y, fromZ), this.n_1700_B(x, y, toZ), filled);
    }

    static BitSetDiscreteVoxelShape n_1700_B(DiscreteVoxelShape first, DiscreteVoxelShape second, IndexMerger xMerger, IndexMerger yMerger, IndexMerger zMerger, BooleanOp op) {
        BitSetDiscreteVoxelShape bitsetvoxelshapepart = new BitSetDiscreteVoxelShape(xMerger.n_1700_B().size() - 1, yMerger.n_1700_B().size() - 1, zMerger.n_1700_B().size() - 1);
        int[] aint = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        xMerger.n_1700_B((int p_199628_7_, int p_199628_8_, int p_199628_9_) -> {
            boolean[] aboolean = new boolean[]{false};
            boolean flag = yMerger.n_1700_B((int p_199627_10_, int p_199627_11_, int p_199627_12_) -> {
                boolean[] aboolean1 = new boolean[]{false};
                boolean flag1 = zMerger.n_1700_B((int p_199629_12_, int p_199629_13_, int p_199629_14_) -> {
                    boolean flag2 = op.apply(first.R_4764_Y(p_199628_7_, p_199627_10_, p_199629_12_), second.R_4764_Y(p_199628_8_, p_199627_11_, p_199629_13_));
                    if (flag2) {
                        bitsetvoxelshapepart.G_564_y.set(bitsetvoxelshapepart.n_1700_B(p_199628_9_, p_199627_12_, p_199629_14_));
                        aint[2] = Math.min(aint[2], p_199629_14_);
                        aint[5] = Math.max(aint[5], p_199629_14_);
                        aboolean1[0] = true;
                    }
                    return true;
                });
                if (aboolean1[0]) {
                    aint[1] = Math.min(aint[1], p_199627_12_);
                    aint[4] = Math.max(aint[4], p_199627_12_);
                    aboolean[0] = true;
                }
                return flag1;
            });
            if (aboolean[0]) {
                aint[0] = Math.min(aint[0], p_199628_9_);
                aint[3] = Math.max(aint[3], p_199628_9_);
            }
            return flag;
        });
        bitsetvoxelshapepart.P_1922_E = aint[0];
        bitsetvoxelshapepart.u_1723_Y = aint[1];
        bitsetvoxelshapepart.v_4262_N = aint[2];
        bitsetvoxelshapepart.w_1484_f = aint[3] + 1;
        bitsetvoxelshapepart.t_148_a = aint[4] + 1;
        bitsetvoxelshapepart.s_956_w = aint[5] + 1;
        return bitsetvoxelshapepart;
    }
}


