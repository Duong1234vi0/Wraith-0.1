/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.i_4702_v;
import lightning.product.m_689_s;
import lightning.product.s_1395_c;
import lightning.product.LightChunkGetter;
import lightning.product.x_268_Y;
import org.apache.commons.lang3.mutable.MutableInt;

public final class C_2693_g
extends i_4702_v<m_689_s.n_1700_B, m_689_s> {
    private static final b_257_Y[] P_1922_E = b_257_Y.values();
    private final c_1514_x.n_1700_B u_1723_Y = new c_1514_x.n_1700_B();

    public C_2693_g(LightChunkGetter p_i51301_1_) {
        super(p_i51301_1_, K_4719_o.J_1907_R, new m_689_s(p_i51301_1_));
    }

    private int G_564_y(long worldPos) {
        int i = c_1514_x.unpackX(worldPos);
        int j = c_1514_x.unpackY(worldPos);
        int k = c_1514_x.unpackZ(worldPos);
        BlockGetter iblockreader = this.n_1700_B.G_564_y(i >> 4, k >> 4);
        return iblockreader != null ? iblockreader.R_4764_Y(this.u_1723_Y.n_1700_B(i, j, k)) : 0;
    }

    @Override
    protected int J_1907_R(long startPos, long endPos, int startLevel) {
        s_1395_c voxelshape1;
        int k;
        int j;
        if (endPos == Long.MAX_VALUE) {
            return 15;
        }
        if (startPos == Long.MAX_VALUE) {
            return startLevel + 15 - this.G_564_y(endPos);
        }
        if (startLevel >= 15) {
            return startLevel;
        }
        int i = Integer.signum(c_1514_x.unpackX(endPos) - c_1514_x.unpackX(startPos));
        b_257_Y direction = b_257_Y.n_1700_B(i, j = Integer.signum(c_1514_x.unpackY(endPos) - c_1514_x.unpackY(startPos)), k = Integer.signum(c_1514_x.unpackZ(endPos) - c_1514_x.unpackZ(startPos)));
        if (direction == null) {
            return 15;
        }
        MutableInt mutableint = new MutableInt();
        K_4074_S blockstate = this.n_1700_B(endPos, mutableint);
        if (mutableint.getValue() >= 15) {
            return 15;
        }
        K_4074_S blockstate1 = this.n_1700_B(startPos, null);
        s_1395_c voxelshape = this.n_1700_B(blockstate1, startPos, direction);
        return x_268_Y.J_1907_R(voxelshape, voxelshape1 = this.n_1700_B(blockstate, endPos, direction.u_1723_Y())) ? 15 : startLevel + Math.max(1, mutableint.getValue());
    }

    @Override
    protected void n_1700_B(long pos, int level, boolean isDecreasing) {
        long i = SectionPos.P_1922_E(pos);
        for (b_257_Y direction : P_1922_E) {
            long j = c_1514_x.offset(pos, direction);
            long k = SectionPos.P_1922_E(j);
            if (i != k && !((m_689_s)this.R_4764_Y).v_4262_N(k)) continue;
            this.J_1907_R(pos, j, level, isDecreasing);
        }
    }

    @Override
    protected int n_1700_B(long pos, long excludedSourcePos, int level) {
        int i = level;
        if (Long.MAX_VALUE != excludedSourcePos) {
            int j = this.J_1907_R(Long.MAX_VALUE, pos, 0);
            if (level > j) {
                i = j;
            }
            if (i == 0) {
                return i;
            }
        }
        long j1 = SectionPos.P_1922_E(pos);
        DataLayer nibblearray = ((m_689_s)this.R_4764_Y).n_1700_B(j1, true);
        for (b_257_Y direction : P_1922_E) {
            long l;
            DataLayer nibblearray1;
            long k = c_1514_x.offset(pos, direction);
            if (k == excludedSourcePos || (nibblearray1 = j1 == (l = SectionPos.P_1922_E(k)) ? nibblearray : ((m_689_s)this.R_4764_Y).n_1700_B(l, true)) == null) continue;
            int i1 = this.J_1907_R(k, pos, this.n_1700_B(nibblearray1, k));
            if (i > i1) {
                i = i1;
            }
            if (i != 0) continue;
            return i;
        }
        return i;
    }

    @Override
    public void n_1700_B(c_1514_x p_215623_1_, int p_215623_2_) {
        ((m_689_s)this.R_4764_Y).P_1922_E();
        this.n_1700_B(Long.MAX_VALUE, p_215623_1_.toLong(), 15 - p_215623_2_, true);
    }
}


