/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.h_4152_b;
import lightning.product.w_1454_v;

public class M_1398_d {
    private final b_4507_u n_1700_B;
    private final c_1514_x J_1907_R;
    private final boolean R_4764_Y;
    private final c_1514_x G_564_y;
    private final b_257_Y P_1922_E;
    private final List<c_1514_x> u_1723_Y = Lists.newArrayList();
    private final List<c_1514_x> v_4262_N = Lists.newArrayList();
    private final b_257_Y w_1484_f;

    public M_1398_d(b_4507_u worldIn, c_1514_x posIn, b_257_Y pistonFacing, boolean extending) {
        this.n_1700_B = worldIn;
        this.J_1907_R = posIn;
        this.w_1484_f = pistonFacing;
        this.R_4764_Y = extending;
        if (extending) {
            this.P_1922_E = pistonFacing;
            this.G_564_y = posIn.offset(pistonFacing);
        } else {
            this.P_1922_E = pistonFacing.u_1723_Y();
            this.G_564_y = posIn.offset(pistonFacing, 2);
        }
    }

    public boolean n_1700_B() {
        this.u_1723_Y.clear();
        this.v_4262_N.clear();
        K_4074_S blockstate = this.n_1700_B.getBlockState(this.G_564_y);
        if (!h_4152_b.n_1700_B(blockstate, this.n_1700_B, this.G_564_y, this.P_1922_E, false, this.w_1484_f)) {
            if (this.R_4764_Y && blockstate.u_2550_I() == w_1454_v.J_1907_R) {
                this.v_4262_N.add(this.G_564_y);
                return true;
            }
            return false;
        }
        if (!this.n_1700_B(this.G_564_y, this.P_1922_E)) {
            return false;
        }
        for (int i = 0; i < this.u_1723_Y.size(); ++i) {
            c_1514_x blockpos = this.u_1723_Y.get(i);
            if (!M_1398_d.n_1700_B(this.n_1700_B.getBlockState(blockpos).J_1907_R()) || this.n_1700_B(blockpos)) continue;
            return false;
        }
        return true;
    }

    private static boolean n_1700_B(T_2915_h p_227029_0_) {
        return p_227029_0_ == a_3742_W.g_4841_c || p_227029_0_ == a_3742_W.B_1335_M;
    }

    private static boolean n_1700_B(T_2915_h p_227030_0_, T_2915_h p_227030_1_) {
        if (p_227030_0_ == a_3742_W.B_1335_M && p_227030_1_ == a_3742_W.g_4841_c) {
            return false;
        }
        if (p_227030_0_ == a_3742_W.g_4841_c && p_227030_1_ == a_3742_W.B_1335_M) {
            return false;
        }
        return M_1398_d.n_1700_B(p_227030_0_) || M_1398_d.n_1700_B(p_227030_1_);
    }

    private boolean n_1700_B(c_1514_x origin, b_257_Y facingIn) {
        K_4074_S blockstate = this.n_1700_B.getBlockState(origin);
        T_2915_h block = blockstate.J_1907_R();
        if (blockstate.v_4262_N()) {
            return true;
        }
        if (!h_4152_b.n_1700_B(blockstate, this.n_1700_B, origin, this.P_1922_E, false, facingIn)) {
            return true;
        }
        if (origin.equals(this.J_1907_R)) {
            return true;
        }
        if (this.u_1723_Y.contains(origin)) {
            return true;
        }
        int i = 1;
        if (i + this.u_1723_Y.size() > 12) {
            return false;
        }
        while (M_1398_d.n_1700_B(block)) {
            c_1514_x blockpos = origin.offset(this.P_1922_E.u_1723_Y(), i);
            T_2915_h block1 = block;
            blockstate = this.n_1700_B.getBlockState(blockpos);
            block = blockstate.J_1907_R();
            if (blockstate.v_4262_N() || !M_1398_d.n_1700_B(block1, block) || !h_4152_b.n_1700_B(blockstate, this.n_1700_B, blockpos, this.P_1922_E, false, this.P_1922_E.u_1723_Y()) || blockpos.equals(this.J_1907_R)) break;
            if (++i + this.u_1723_Y.size() <= 12) continue;
            return false;
        }
        int l = 0;
        for (int i1 = i - 1; i1 >= 0; --i1) {
            this.u_1723_Y.add(origin.offset(this.P_1922_E.u_1723_Y(), i1));
            ++l;
        }
        int j1 = 1;
        while (true) {
            c_1514_x blockpos1;
            int j;
            if ((j = this.u_1723_Y.indexOf(blockpos1 = origin.offset(this.P_1922_E, j1))) > -1) {
                this.n_1700_B(l, j);
                for (int k = 0; k <= j + l; ++k) {
                    c_1514_x blockpos2 = this.u_1723_Y.get(k);
                    if (!M_1398_d.n_1700_B(this.n_1700_B.getBlockState(blockpos2).J_1907_R()) || this.n_1700_B(blockpos2)) continue;
                    return false;
                }
                return true;
            }
            blockstate = this.n_1700_B.getBlockState(blockpos1);
            if (blockstate.v_4262_N()) {
                return true;
            }
            if (!h_4152_b.n_1700_B(blockstate, this.n_1700_B, blockpos1, this.P_1922_E, true, this.P_1922_E) || blockpos1.equals(this.J_1907_R)) {
                return false;
            }
            if (blockstate.u_2550_I() == w_1454_v.J_1907_R) {
                this.v_4262_N.add(blockpos1);
                return true;
            }
            if (this.u_1723_Y.size() >= 12) {
                return false;
            }
            this.u_1723_Y.add(blockpos1);
            ++l;
            ++j1;
        }
    }

    private void n_1700_B(int offsets, int index) {
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        ArrayList list2 = Lists.newArrayList();
        list.addAll(this.u_1723_Y.subList(0, index));
        list1.addAll(this.u_1723_Y.subList(this.u_1723_Y.size() - offsets, this.u_1723_Y.size()));
        list2.addAll(this.u_1723_Y.subList(index, this.u_1723_Y.size() - offsets));
        this.u_1723_Y.clear();
        this.u_1723_Y.addAll(list);
        this.u_1723_Y.addAll(list1);
        this.u_1723_Y.addAll(list2);
    }

    private boolean n_1700_B(c_1514_x fromPos) {
        K_4074_S blockstate = this.n_1700_B.getBlockState(fromPos);
        for (b_257_Y direction : b_257_Y.values()) {
            c_1514_x blockpos;
            K_4074_S blockstate1;
            if (direction.h_1847_R() == this.P_1922_E.h_1847_R() || !M_1398_d.n_1700_B((blockstate1 = this.n_1700_B.getBlockState(blockpos = fromPos.offset(direction))).J_1907_R(), blockstate.J_1907_R()) || this.n_1700_B(blockpos, direction)) continue;
            return false;
        }
        return true;
    }

    public List<c_1514_x> J_1907_R() {
        return this.u_1723_Y;
    }

    public List<c_1514_x> R_4764_Y() {
        return this.v_4262_N;
    }
}

