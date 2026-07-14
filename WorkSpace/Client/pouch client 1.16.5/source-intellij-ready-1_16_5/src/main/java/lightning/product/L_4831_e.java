/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import lightning.product.G_1698_X;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.DataLayer;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.i_4702_v;
import lightning.product.s_1395_c;
import lightning.product.LightChunkGetter;
import lightning.product.x_268_Y;
import org.apache.commons.lang3.mutable.MutableInt;

public final class L_4831_e
extends i_4702_v<G_1698_X.n_1700_B, G_1698_X> {
    private static final b_257_Y[] P_1922_E = b_257_Y.values();
    private static final b_257_Y[] u_1723_Y = new b_257_Y[]{b_257_Y.R_4764_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E, b_257_Y.u_1723_Y};

    public L_4831_e(LightChunkGetter p_i51289_1_) {
        super(p_i51289_1_, K_4719_o.n_1700_B, new G_1698_X(p_i51289_1_));
    }

    @Override
    protected int J_1907_R(long startPos, long endPos, int startLevel) {
        if (endPos == Long.MAX_VALUE) {
            return 15;
        }
        if (startPos == Long.MAX_VALUE) {
            if (!((G_1698_X)this.R_4764_Y).P_4830_p(endPos)) {
                return 15;
            }
            startLevel = 0;
        }
        if (startLevel >= 15) {
            return startLevel;
        }
        MutableInt mutableint = new MutableInt();
        K_4074_S blockstate = this.n_1700_B(endPos, mutableint);
        if (mutableint.getValue() >= 15) {
            return 15;
        }
        int i = c_1514_x.unpackX(startPos);
        int j = c_1514_x.unpackY(startPos);
        int k = c_1514_x.unpackZ(startPos);
        int l = c_1514_x.unpackX(endPos);
        int i1 = c_1514_x.unpackY(endPos);
        int j1 = c_1514_x.unpackZ(endPos);
        boolean flag = i == l && k == j1;
        int k1 = Integer.signum(l - i);
        int l1 = Integer.signum(i1 - j);
        int i2 = Integer.signum(j1 - k);
        b_257_Y direction = startPos == Long.MAX_VALUE ? b_257_Y.n_1700_B : b_257_Y.n_1700_B(k1, l1, i2);
        K_4074_S blockstate1 = this.n_1700_B(startPos, null);
        if (direction != null) {
            s_1395_c voxelshape1;
            s_1395_c voxelshape = this.n_1700_B(blockstate1, startPos, direction);
            if (x_268_Y.J_1907_R(voxelshape, voxelshape1 = this.n_1700_B(blockstate, endPos, direction.u_1723_Y()))) {
                return 15;
            }
        } else {
            s_1395_c voxelshape3 = this.n_1700_B(blockstate1, startPos, b_257_Y.n_1700_B);
            if (x_268_Y.J_1907_R(voxelshape3, x_268_Y.n_1700_B())) {
                return 15;
            }
            int j2 = flag ? -1 : 0;
            b_257_Y direction1 = b_257_Y.n_1700_B(k1, j2, i2);
            if (direction1 == null) {
                return 15;
            }
            s_1395_c voxelshape2 = this.n_1700_B(blockstate, endPos, direction1.u_1723_Y());
            if (x_268_Y.J_1907_R(x_268_Y.n_1700_B(), voxelshape2)) {
                return 15;
            }
        }
        boolean flag1 = startPos == Long.MAX_VALUE || flag && j > i1;
        return flag1 && startLevel == 0 && mutableint.getValue() == 0 ? 0 : startLevel + Math.max(1, mutableint.getValue());
    }

    @Override
    protected void n_1700_B(long pos, int level, boolean isDecreasing) {
        long l1;
        long i2;
        int i1;
        long i = SectionPos.P_1922_E(pos);
        int j = c_1514_x.unpackY(pos);
        int k = SectionPos.J_1907_R(j);
        int l = SectionPos.n_1700_B(j);
        if (k != 0) {
            i1 = 0;
        } else {
            int j1 = 0;
            while (!((G_1698_X)this.R_4764_Y).v_4262_N(SectionPos.n_1700_B(i, 0, -j1 - 1, 0)) && ((G_1698_X)this.R_4764_Y).J_1907_R(l - j1 - 1)) {
                ++j1;
            }
            i1 = j1;
        }
        long i3 = c_1514_x.offset(pos, 0, -1 - i1 * 16, 0);
        long k1 = SectionPos.P_1922_E(i3);
        if (i == k1 || ((G_1698_X)this.R_4764_Y).v_4262_N(k1)) {
            this.J_1907_R(pos, i3, level, isDecreasing);
        }
        if (i == (i2 = SectionPos.P_1922_E(l1 = c_1514_x.offset(pos, b_257_Y.J_1907_R))) || ((G_1698_X)this.R_4764_Y).v_4262_N(i2)) {
            this.J_1907_R(pos, l1, level, isDecreasing);
        }
        block1: for (b_257_Y direction : u_1723_Y) {
            int j2 = 0;
            do {
                long k2;
                long l2;
                if (i == (l2 = SectionPos.P_1922_E(k2 = c_1514_x.offset(pos, direction.t_148_a(), -j2, direction.u_2550_I())))) {
                    this.J_1907_R(pos, k2, level, isDecreasing);
                    continue block1;
                }
                if (!((G_1698_X)this.R_4764_Y).v_4262_N(l2)) continue;
                this.J_1907_R(pos, k2, level, isDecreasing);
            } while (++j2 <= i1 * 16);
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
        DataLayer nibblearray = ((G_1698_X)this.R_4764_Y).n_1700_B(j1, true);
        for (b_257_Y direction : P_1922_E) {
            int i1;
            long k = c_1514_x.offset(pos, direction);
            long l = SectionPos.P_1922_E(k);
            DataLayer nibblearray1 = j1 == l ? nibblearray : ((G_1698_X)this.R_4764_Y).n_1700_B(l, true);
            if (nibblearray1 != null) {
                if (k == excludedSourcePos) continue;
                int k1 = this.J_1907_R(k, pos, this.n_1700_B(nibblearray1, k));
                if (i > k1) {
                    i = k1;
                }
                if (i != 0) continue;
                return i;
            }
            if (direction == b_257_Y.n_1700_B) continue;
            k = c_1514_x.atSectionBottomY(k);
            while (!((G_1698_X)this.R_4764_Y).v_4262_N(l) && !((G_1698_X)this.R_4764_Y).h_1847_R(l)) {
                l = SectionPos.n_1700_B(l, b_257_Y.J_1907_R);
                k = c_1514_x.offset(k, 0, 16, 0);
            }
            DataLayer nibblearray2 = ((G_1698_X)this.R_4764_Y).n_1700_B(l, true);
            if (k == excludedSourcePos) continue;
            if (nibblearray2 != null) {
                i1 = this.J_1907_R(k, pos, this.n_1700_B(nibblearray2, k));
            } else {
                int n = i1 = ((G_1698_X)this.R_4764_Y).Q_4569_t(l) ? 0 : 15;
            }
            if (i > i1) {
                i = i1;
            }
            if (i != 0) continue;
            return i;
        }
        return i;
    }

    @Override
    protected void u_1723_Y(long worldPos) {
        ((G_1698_X)this.R_4764_Y).P_1922_E();
        long i = SectionPos.P_1922_E(worldPos);
        if (((G_1698_X)this.R_4764_Y).v_4262_N(i)) {
            super.u_1723_Y(worldPos);
        } else {
            worldPos = c_1514_x.atSectionBottomY(worldPos);
            while (!((G_1698_X)this.R_4764_Y).v_4262_N(i) && !((G_1698_X)this.R_4764_Y).h_1847_R(i)) {
                i = SectionPos.n_1700_B(i, b_257_Y.J_1907_R);
                worldPos = c_1514_x.offset(worldPos, 0, 16, 0);
            }
            if (((G_1698_X)this.R_4764_Y).v_4262_N(i)) {
                super.u_1723_Y(worldPos);
            }
        }
    }

    @Override
    public String J_1907_R(long sectionPosIn) {
        return super.J_1907_R(sectionPosIn) + (((G_1698_X)this.R_4764_Y).h_1847_R(sectionPosIn) ? "*" : "");
    }
}


