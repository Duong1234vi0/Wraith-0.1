/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Iterator;
import java.util.List;
import lightning.product.B_4088_l;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.N_81_X;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.X_1924_A;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_4152_b;
import lightning.product.PistonMath;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.BlockEntityType;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.w_1454_v;
import lightning.product.x_268_Y;
import lightning.product.y_1539_W;

public class N_295_T
extends i_2154_H
implements X_1924_A {
    private K_4074_S n_1700_B;
    private b_257_Y J_1907_R;
    private boolean R_4764_Y;
    private boolean G_564_y;
    private static final ThreadLocal<b_257_Y> P_1922_E = ThreadLocal.withInitial(() -> null);
    private float u_1723_Y;
    private float v_4262_N;
    private long w_1484_f;
    private int t_148_a;

    public N_295_T() {
        super(BlockEntityType.s_956_w);
    }

    public N_295_T(K_4074_S pistonStateIn, b_257_Y pistonFacingIn, boolean extendingIn, boolean shouldHeadBeRenderedIn) {
        this();
        this.n_1700_B = pistonStateIn;
        this.J_1907_R = pistonFacingIn;
        this.R_4764_Y = extendingIn;
        this.G_564_y = shouldHeadBeRenderedIn;
    }

    @Override
    public U_2912_j H_() {
        return this.n_1700_B(new U_2912_j());
    }

    public boolean v_4262_N() {
        return this.R_4764_Y;
    }

    public b_257_Y w_1484_f() {
        return this.J_1907_R;
    }

    public boolean s_956_w() {
        return this.G_564_y;
    }

    public float n_1700_B(float ticks) {
        if (ticks > 1.0f) {
            ticks = 1.0f;
        }
        return u_530_F.v_4262_N(ticks, this.v_4262_N, this.u_1723_Y);
    }

    public float J_1907_R(float ticks) {
        return (float)this.J_1907_R.t_148_a() * this.P_1922_E(this.n_1700_B(ticks));
    }

    public float R_4764_Y(float ticks) {
        return (float)this.J_1907_R.s_956_w() * this.P_1922_E(this.n_1700_B(ticks));
    }

    public float G_564_y(float ticks) {
        return (float)this.J_1907_R.u_2550_I() * this.P_1922_E(this.n_1700_B(ticks));
    }

    private float P_1922_E(float p_184320_1_) {
        return this.R_4764_Y ? p_184320_1_ - 1.0f : 1.0f - p_184320_1_;
    }

    private K_4074_S Q_4569_t() {
        return !this.v_4262_N() && this.s_956_w() && this.n_1700_B.J_1907_R() instanceof h_4152_b ? (K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.S_980_j.multiplayerClientSuggestionProvider().n_1700_B(N_81_X.Q_4569_t, this.u_1723_Y > 0.25f)).n_1700_B(N_81_X.h_1847_R, this.n_1700_B.n_1700_B(a_3742_W.RealmsDefaultUncaughtExceptionHandler) ? y_1539_W.J_1907_R : y_1539_W.n_1700_B)).n_1700_B(N_81_X.P_4830_p, this.n_1700_B.R_4764_Y(h_4152_b.P_4830_p)) : this.n_1700_B;
    }

    private void u_1723_Y(float p_184322_1_) {
        I_4817_s axisalignedbb;
        List<N_4263_v> list;
        b_257_Y direction = this.u_2550_I();
        double d0 = p_184322_1_ - this.u_1723_Y;
        s_1395_c voxelshape = this.Q_4569_t().u_2550_I(this.u_2550_I, this.x_607_J());
        if (!voxelshape.J_1907_R() && !(list = this.u_2550_I.n_1700_B((N_4263_v)null, PistonMath.n_1700_B(axisalignedbb = this.n_1700_B(voxelshape.n_1700_B()), direction, d0).union(axisalignedbb))).isEmpty()) {
            List<I_4817_s> list1 = voxelshape.G_564_y();
            boolean flag = this.n_1700_B.n_1700_B(a_3742_W.g_4841_c);
            Iterator<N_4263_v> iterator = list.iterator();
            while (true) {
                I_4817_s axisalignedbb3;
                I_4817_s axisalignedbb2;
                I_4817_s axisalignedbb1;
                if (!iterator.hasNext()) {
                    return;
                }
                N_4263_v entity = iterator.next();
                if (entity.h_() == w_1454_v.G_564_y) continue;
                if (flag) {
                    if (entity instanceof B_4088_l) continue;
                    e_2866_D vector3d = entity.I_4348_c();
                    double d1 = vector3d.J_1907_R;
                    double d2 = vector3d.R_4764_Y;
                    double d3 = vector3d.G_564_y;
                    switch (direction.h_1847_R()) {
                        case n_1700_B: {
                            d1 = direction.t_148_a();
                            break;
                        }
                        case J_1907_R: {
                            d2 = direction.s_956_w();
                            break;
                        }
                        case R_4764_Y: {
                            d3 = direction.u_2550_I();
                        }
                    }
                    entity.h_1847_R(d1, d2, d3);
                }
                double d4 = 0.0;
                Iterator<I_4817_s> iterator2 = list1.iterator();
                while (!(!iterator2.hasNext() || (axisalignedbb1 = PistonMath.n_1700_B(this.n_1700_B(axisalignedbb2 = iterator2.next()), direction, d0)).intersects(axisalignedbb3 = entity.i_601_W()) && (d4 = Math.max(d4, N_295_T.n_1700_B(axisalignedbb1, direction, axisalignedbb3))) >= d0)) {
                }
                if (d4 <= 0.0) continue;
                d4 = Math.min(d4, d0) + 0.01;
                N_295_T.n_1700_B(direction, entity, d4, direction);
                if (this.R_4764_Y || !this.G_564_y) continue;
                this.n_1700_B(entity, direction, d0);
            }
        }
    }

    private static void n_1700_B(b_257_Y p_227022_0_, N_4263_v p_227022_1_, double p_227022_2_, b_257_Y p_227022_4_) {
        P_1922_E.set(p_227022_0_);
        p_227022_1_.n_1700_B(L_461_d.R_4764_Y, new e_2866_D(p_227022_2_ * (double)p_227022_4_.t_148_a(), p_227022_2_ * (double)p_227022_4_.s_956_w(), p_227022_2_ * (double)p_227022_4_.u_2550_I()));
        P_1922_E.set(null);
    }

    private void v_4262_N(float p_227024_1_) {
        b_257_Y direction;
        if (this.t_1786_h() && (direction = this.u_2550_I()).h_1847_R().G_564_y()) {
            double d0 = this.n_1700_B.u_2550_I(this.u_2550_I, this.M_588_G).R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
            I_4817_s axisalignedbb = this.n_1700_B(new I_4817_s(0.0, d0, 0.0, 1.0, 1.5000000999999998, 1.0));
            double d1 = p_227024_1_ - this.u_1723_Y;
            for (N_4263_v entity : this.u_2550_I.J_1907_R((N_4263_v)null, axisalignedbb, p_227023_1_ -> N_295_T.n_1700_B(axisalignedbb, p_227023_1_))) {
                N_295_T.n_1700_B(direction, entity, d1, direction);
            }
        }
    }

    private static boolean n_1700_B(I_4817_s p_227021_0_, N_4263_v p_227021_1_) {
        return p_227021_1_.h_() == w_1454_v.n_1700_B && p_227021_1_.M_1641_O() && p_227021_1_.O_3598_v() >= p_227021_0_.minX && p_227021_1_.O_3598_v() <= p_227021_0_.maxX && p_227021_1_.l_2647_k() >= p_227021_0_.minZ && p_227021_1_.l_2647_k() <= p_227021_0_.maxZ;
    }

    private boolean t_1786_h() {
        return this.n_1700_B.n_1700_B(a_3742_W.B_1335_M);
    }

    public b_257_Y u_2550_I() {
        return this.R_4764_Y ? this.J_1907_R : this.J_1907_R.u_1723_Y();
    }

    private static double n_1700_B(I_4817_s p_190612_0_, b_257_Y p_190612_1_, I_4817_s facing) {
        switch (p_190612_1_) {
            case u_1723_Y: {
                return p_190612_0_.maxX - facing.minX;
            }
            case P_1922_E: {
                return facing.maxX - p_190612_0_.minX;
            }
            default: {
                return p_190612_0_.maxY - facing.minY;
            }
            case n_1700_B: {
                return facing.maxY - p_190612_0_.minY;
            }
            case G_564_y: {
                return p_190612_0_.maxZ - facing.minZ;
            }
            case R_4764_Y: 
        }
        return facing.maxZ - p_190612_0_.minZ;
    }

    private I_4817_s n_1700_B(I_4817_s p_190607_1_) {
        double d0 = this.P_1922_E(this.u_1723_Y);
        return p_190607_1_.offset((double)this.M_588_G.getX() + d0 * (double)this.J_1907_R.t_148_a(), (double)this.M_588_G.getY() + d0 * (double)this.J_1907_R.s_956_w(), (double)this.M_588_G.getZ() + d0 * (double)this.J_1907_R.u_2550_I());
    }

    private void n_1700_B(N_4263_v p_190605_1_, b_257_Y p_190605_2_, double p_190605_3_) {
        double d1;
        b_257_Y direction;
        double d0;
        I_4817_s axisalignedbb1;
        I_4817_s axisalignedbb = p_190605_1_.i_601_W();
        if (axisalignedbb.intersects(axisalignedbb1 = x_268_Y.J_1907_R().n_1700_B().offset(this.M_588_G)) && Math.abs((d0 = N_295_T.n_1700_B(axisalignedbb1, direction = p_190605_2_.u_1723_Y(), axisalignedbb) + 0.01) - (d1 = N_295_T.n_1700_B(axisalignedbb1, direction, axisalignedbb.intersect(axisalignedbb1)) + 0.01)) < 0.01) {
            d0 = Math.min(d0, p_190605_3_) + 0.01;
            N_295_T.n_1700_B(p_190605_2_, p_190605_1_, d0, direction);
        }
    }

    public K_4074_S M_588_G() {
        return this.n_1700_B;
    }

    public void P_4830_p() {
        if (this.u_2550_I != null && (this.v_4262_N < 1.0f || this.u_2550_I.Y_259_p)) {
            this.v_4262_N = this.u_1723_Y = 1.0f;
            this.u_2550_I.t_1786_h(this.M_588_G);
            this.I_();
            if (this.u_2550_I.getBlockState(this.M_588_G).n_1700_B(a_3742_W.O_2151_c)) {
                K_4074_S blockstate = this.G_564_y ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : T_2915_h.J_1907_R(this.n_1700_B, this.u_2550_I, this.M_588_G);
                this.u_2550_I.n_1700_B(this.M_588_G, blockstate, 3);
                this.u_2550_I.n_1700_B(this.M_588_G, blockstate.J_1907_R(), this.M_588_G);
            }
        }
    }

    @Override
    public void P_1922_E() {
        this.w_1484_f = this.u_2550_I.X_933_l();
        this.v_4262_N = this.u_1723_Y;
        if (this.v_4262_N >= 1.0f) {
            if (this.u_2550_I.Y_259_p && this.t_148_a < 5) {
                ++this.t_148_a;
            } else {
                this.u_2550_I.t_1786_h(this.M_588_G);
                this.I_();
                if (this.n_1700_B != null && this.u_2550_I.getBlockState(this.M_588_G).n_1700_B(a_3742_W.O_2151_c)) {
                    K_4074_S blockstate = T_2915_h.J_1907_R(this.n_1700_B, this.u_2550_I, this.M_588_G);
                    if (blockstate.v_4262_N()) {
                        this.u_2550_I.n_1700_B(this.M_588_G, this.n_1700_B, 84);
                        T_2915_h.n_1700_B(this.n_1700_B, blockstate, this.u_2550_I, this.M_588_G, 3);
                    } else {
                        if (blockstate.J_1907_R(BlockStateProperties.A_4115_X) && blockstate.R_4764_Y(BlockStateProperties.A_4115_X).booleanValue()) {
                            blockstate = (K_4074_S)blockstate.n_1700_B(BlockStateProperties.A_4115_X, false);
                        }
                        this.u_2550_I.n_1700_B(this.M_588_G, blockstate, 67);
                        this.u_2550_I.n_1700_B(this.M_588_G, blockstate.J_1907_R(), this.M_588_G);
                    }
                }
            }
        } else {
            float f = this.u_1723_Y + 0.5f;
            this.u_1723_Y(f);
            this.v_4262_N(f);
            this.u_1723_Y = f;
            if (this.u_1723_Y >= 1.0f) {
                this.u_1723_Y = 1.0f;
            }
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.n_1700_B = n_3832_I.R_4764_Y(nbt.M_182_A("blockState"));
        this.J_1907_R = b_257_Y.n_1700_B(nbt.w_1484_f("facing"));
        this.v_4262_N = this.u_1723_Y = nbt.s_956_w("progress");
        this.R_4764_Y = nbt.t_1786_h("extending");
        this.G_564_y = nbt.t_1786_h("source");
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("blockState", n_3832_I.n_1700_B(this.n_1700_B));
        compound.J_1907_R("facing", this.J_1907_R.R_4764_Y());
        compound.n_1700_B("progress", this.v_4262_N);
        compound.n_1700_B("extending", this.R_4764_Y);
        compound.n_1700_B("source", this.G_564_y);
        return compound;
    }

    public s_1395_c n_1700_B(BlockGetter p_195508_1_, c_1514_x p_195508_2_) {
        s_1395_c voxelshape = !this.R_4764_Y && this.G_564_y ? ((K_4074_S)this.n_1700_B.n_1700_B(h_4152_b.h_1847_R, true)).u_2550_I(p_195508_1_, p_195508_2_) : x_268_Y.n_1700_B();
        b_257_Y direction = P_1922_E.get();
        if ((double)this.u_1723_Y < 1.0 && direction == this.u_2550_I()) {
            return voxelshape;
        }
        K_4074_S blockstate = this.s_956_w() ? (K_4074_S)((K_4074_S)a_3742_W.S_980_j.multiplayerClientSuggestionProvider().n_1700_B(N_81_X.P_4830_p, this.J_1907_R)).n_1700_B(N_81_X.Q_4569_t, this.R_4764_Y != 1.0f - this.u_1723_Y < 0.25f) : this.n_1700_B;
        float f = this.P_1922_E(this.u_1723_Y);
        double d0 = (float)this.J_1907_R.t_148_a() * f;
        double d1 = (float)this.J_1907_R.s_956_w() * f;
        double d2 = (float)this.J_1907_R.u_2550_I() * f;
        return x_268_Y.n_1700_B(voxelshape, blockstate.u_2550_I(p_195508_1_, p_195508_2_).n_1700_B(d0, d1, d2));
    }

    public long h_1847_R() {
        return this.w_1484_f;
    }

    @Override
    public double t_148_a() {
        return 68.0;
    }
}


