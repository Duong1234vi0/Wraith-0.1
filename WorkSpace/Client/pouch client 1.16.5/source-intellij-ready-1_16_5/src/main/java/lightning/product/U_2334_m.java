/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_2711_h;
import lightning.product.w_801_N;

public class U_2334_m {
    private final b_4507_u n_1700_B;
    private final c_1514_x J_1907_R;
    private final g_2711_h R_4764_Y;
    private K_4074_S G_564_y;
    private final boolean P_1922_E;
    private final List<c_1514_x> u_1723_Y = Lists.newArrayList();

    public U_2334_m(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        this.n_1700_B = worldIn;
        this.J_1907_R = pos;
        this.G_564_y = state;
        this.R_4764_Y = (g_2711_h)state.J_1907_R();
        w_801_N railshape = state.R_4764_Y(this.R_4764_Y.t_148_a());
        this.P_1922_E = this.R_4764_Y.J_1907_R();
        this.n_1700_B(railshape);
    }

    public List<c_1514_x> n_1700_B() {
        return this.u_1723_Y;
    }

    private void n_1700_B(w_801_N shape) {
        this.u_1723_Y.clear();
        switch (shape) {
            case n_1700_B: {
                this.u_1723_Y.add(this.J_1907_R.north());
                this.u_1723_Y.add(this.J_1907_R.south());
                break;
            }
            case J_1907_R: {
                this.u_1723_Y.add(this.J_1907_R.west());
                this.u_1723_Y.add(this.J_1907_R.east());
                break;
            }
            case R_4764_Y: {
                this.u_1723_Y.add(this.J_1907_R.west());
                this.u_1723_Y.add(this.J_1907_R.east().up());
                break;
            }
            case G_564_y: {
                this.u_1723_Y.add(this.J_1907_R.west().up());
                this.u_1723_Y.add(this.J_1907_R.east());
                break;
            }
            case P_1922_E: {
                this.u_1723_Y.add(this.J_1907_R.north().up());
                this.u_1723_Y.add(this.J_1907_R.south());
                break;
            }
            case u_1723_Y: {
                this.u_1723_Y.add(this.J_1907_R.north());
                this.u_1723_Y.add(this.J_1907_R.south().up());
                break;
            }
            case v_4262_N: {
                this.u_1723_Y.add(this.J_1907_R.east());
                this.u_1723_Y.add(this.J_1907_R.south());
                break;
            }
            case w_1484_f: {
                this.u_1723_Y.add(this.J_1907_R.west());
                this.u_1723_Y.add(this.J_1907_R.south());
                break;
            }
            case t_148_a: {
                this.u_1723_Y.add(this.J_1907_R.west());
                this.u_1723_Y.add(this.J_1907_R.north());
                break;
            }
            case s_956_w: {
                this.u_1723_Y.add(this.J_1907_R.east());
                this.u_1723_Y.add(this.J_1907_R.north());
            }
        }
    }

    private void G_564_y() {
        for (int i = 0; i < this.u_1723_Y.size(); ++i) {
            U_2334_m railstate = this.J_1907_R(this.u_1723_Y.get(i));
            if (railstate != null && railstate.n_1700_B(this)) {
                this.u_1723_Y.set(i, railstate.J_1907_R);
                continue;
            }
            this.u_1723_Y.remove(i--);
        }
    }

    private boolean n_1700_B(c_1514_x pos) {
        return g_2711_h.n_1700_B(this.n_1700_B, pos) || g_2711_h.n_1700_B(this.n_1700_B, pos.up()) || g_2711_h.n_1700_B(this.n_1700_B, pos.down());
    }

    @Nullable
    private U_2334_m J_1907_R(c_1514_x pos) {
        K_4074_S blockstate = this.n_1700_B.getBlockState(pos);
        if (g_2711_h.v_4262_N(blockstate)) {
            return new U_2334_m(this.n_1700_B, pos, blockstate);
        }
        c_1514_x lvt_2_1_ = pos.up();
        blockstate = this.n_1700_B.getBlockState(lvt_2_1_);
        if (g_2711_h.v_4262_N(blockstate)) {
            return new U_2334_m(this.n_1700_B, lvt_2_1_, blockstate);
        }
        lvt_2_1_ = pos.down();
        blockstate = this.n_1700_B.getBlockState(lvt_2_1_);
        return g_2711_h.v_4262_N(blockstate) ? new U_2334_m(this.n_1700_B, lvt_2_1_, blockstate) : null;
    }

    private boolean n_1700_B(U_2334_m state) {
        return this.R_4764_Y(state.J_1907_R);
    }

    private boolean R_4764_Y(c_1514_x pos) {
        for (int i = 0; i < this.u_1723_Y.size(); ++i) {
            c_1514_x blockpos = this.u_1723_Y.get(i);
            if (blockpos.getX() != pos.getX() || blockpos.getZ() != pos.getZ()) continue;
            return true;
        }
        return false;
    }

    protected int J_1907_R() {
        int i = 0;
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            if (!this.n_1700_B(this.J_1907_R.offset(direction))) continue;
            ++i;
        }
        return i;
    }

    private boolean J_1907_R(U_2334_m state) {
        return this.n_1700_B(state) || this.u_1723_Y.size() != 2;
    }

    private void R_4764_Y(U_2334_m state) {
        this.u_1723_Y.add(state.J_1907_R);
        c_1514_x blockpos = this.J_1907_R.north();
        c_1514_x blockpos1 = this.J_1907_R.south();
        c_1514_x blockpos2 = this.J_1907_R.west();
        c_1514_x blockpos3 = this.J_1907_R.east();
        boolean flag = this.R_4764_Y(blockpos);
        boolean flag1 = this.R_4764_Y(blockpos1);
        boolean flag2 = this.R_4764_Y(blockpos2);
        boolean flag3 = this.R_4764_Y(blockpos3);
        w_801_N railshape = null;
        if (flag || flag1) {
            railshape = w_801_N.n_1700_B;
        }
        if (flag2 || flag3) {
            railshape = w_801_N.J_1907_R;
        }
        if (!this.P_1922_E) {
            if (flag1 && flag3 && !flag && !flag2) {
                railshape = w_801_N.v_4262_N;
            }
            if (flag1 && flag2 && !flag && !flag3) {
                railshape = w_801_N.w_1484_f;
            }
            if (flag && flag2 && !flag1 && !flag3) {
                railshape = w_801_N.t_148_a;
            }
            if (flag && flag3 && !flag1 && !flag2) {
                railshape = w_801_N.s_956_w;
            }
        }
        if (railshape == w_801_N.n_1700_B) {
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos.up())) {
                railshape = w_801_N.P_1922_E;
            }
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos1.up())) {
                railshape = w_801_N.u_1723_Y;
            }
        }
        if (railshape == w_801_N.J_1907_R) {
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos3.up())) {
                railshape = w_801_N.R_4764_Y;
            }
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos2.up())) {
                railshape = w_801_N.G_564_y;
            }
        }
        if (railshape == null) {
            railshape = w_801_N.n_1700_B;
        }
        this.G_564_y = (K_4074_S)this.G_564_y.n_1700_B(this.R_4764_Y.t_148_a(), railshape);
        this.n_1700_B.n_1700_B(this.J_1907_R, this.G_564_y, 3);
    }

    private boolean G_564_y(c_1514_x pos) {
        U_2334_m railstate = this.J_1907_R(pos);
        if (railstate == null) {
            return false;
        }
        railstate.G_564_y();
        return railstate.J_1907_R(this);
    }

    public U_2334_m n_1700_B(boolean powered, boolean placeBlock, w_801_N shape) {
        boolean flag9;
        boolean flag5;
        c_1514_x blockpos = this.J_1907_R.north();
        c_1514_x blockpos1 = this.J_1907_R.south();
        c_1514_x blockpos2 = this.J_1907_R.west();
        c_1514_x blockpos3 = this.J_1907_R.east();
        boolean flag = this.G_564_y(blockpos);
        boolean flag1 = this.G_564_y(blockpos1);
        boolean flag2 = this.G_564_y(blockpos2);
        boolean flag3 = this.G_564_y(blockpos3);
        w_801_N railshape = null;
        boolean flag4 = flag || flag1;
        boolean bl = flag5 = flag2 || flag3;
        if (flag4 && !flag5) {
            railshape = w_801_N.n_1700_B;
        }
        if (flag5 && !flag4) {
            railshape = w_801_N.J_1907_R;
        }
        boolean flag6 = flag1 && flag3;
        boolean flag7 = flag1 && flag2;
        boolean flag8 = flag && flag3;
        boolean bl2 = flag9 = flag && flag2;
        if (!this.P_1922_E) {
            if (flag6 && !flag && !flag2) {
                railshape = w_801_N.v_4262_N;
            }
            if (flag7 && !flag && !flag3) {
                railshape = w_801_N.w_1484_f;
            }
            if (flag9 && !flag1 && !flag3) {
                railshape = w_801_N.t_148_a;
            }
            if (flag8 && !flag1 && !flag2) {
                railshape = w_801_N.s_956_w;
            }
        }
        if (railshape == null) {
            if (flag4 && flag5) {
                railshape = shape;
            } else if (flag4) {
                railshape = w_801_N.n_1700_B;
            } else if (flag5) {
                railshape = w_801_N.J_1907_R;
            }
            if (!this.P_1922_E) {
                if (powered) {
                    if (flag6) {
                        railshape = w_801_N.v_4262_N;
                    }
                    if (flag7) {
                        railshape = w_801_N.w_1484_f;
                    }
                    if (flag8) {
                        railshape = w_801_N.s_956_w;
                    }
                    if (flag9) {
                        railshape = w_801_N.t_148_a;
                    }
                } else {
                    if (flag9) {
                        railshape = w_801_N.t_148_a;
                    }
                    if (flag8) {
                        railshape = w_801_N.s_956_w;
                    }
                    if (flag7) {
                        railshape = w_801_N.w_1484_f;
                    }
                    if (flag6) {
                        railshape = w_801_N.v_4262_N;
                    }
                }
            }
        }
        if (railshape == w_801_N.n_1700_B) {
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos.up())) {
                railshape = w_801_N.P_1922_E;
            }
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos1.up())) {
                railshape = w_801_N.u_1723_Y;
            }
        }
        if (railshape == w_801_N.J_1907_R) {
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos3.up())) {
                railshape = w_801_N.R_4764_Y;
            }
            if (g_2711_h.n_1700_B(this.n_1700_B, blockpos2.up())) {
                railshape = w_801_N.G_564_y;
            }
        }
        if (railshape == null) {
            railshape = shape;
        }
        this.n_1700_B(railshape);
        this.G_564_y = (K_4074_S)this.G_564_y.n_1700_B(this.R_4764_Y.t_148_a(), railshape);
        if (placeBlock || this.n_1700_B.getBlockState(this.J_1907_R) != this.G_564_y) {
            this.n_1700_B.n_1700_B(this.J_1907_R, this.G_564_y, 3);
            for (int i = 0; i < this.u_1723_Y.size(); ++i) {
                U_2334_m railstate = this.J_1907_R(this.u_1723_Y.get(i));
                if (railstate == null) continue;
                railstate.G_564_y();
                if (!railstate.J_1907_R(this)) continue;
                railstate.R_4764_Y(this);
            }
        }
        return this;
    }

    public K_4074_S R_4764_Y() {
        return this.G_564_y;
    }
}

