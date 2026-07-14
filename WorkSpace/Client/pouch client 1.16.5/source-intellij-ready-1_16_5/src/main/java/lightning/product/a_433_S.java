/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.NonNullList;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.V_4572_l;
import lightning.product.W_3491_f;
import lightning.product.X_1924_A;
import lightning.product.Y_3462_U;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.e_2866_D;
import lightning.product.e_933_M;
import lightning.product.ContainerHelper;
import lightning.product.WorldlyContainer;
import lightning.product.BlockEntityType;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.w_1454_v;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.x_353_w;

public class a_433_S
extends V_4572_l
implements X_1924_A,
WorldlyContainer {
    private static final int[] n_1700_B = IntStream.range(0, 27).toArray();
    private NonNullList<Z_1993_T> J_1907_R = NonNullList.n_1700_B(27, Z_1993_T.J_1907_R);
    private int R_4764_Y;
    private n_1700_B u_1723_Y = lightning.product.a_433_S$n_1700_B.n_1700_B;
    private float v_4262_N;
    private float w_1484_f;
    @Nullable
    private e_933_M t_148_a;
    private boolean s_956_w;

    public a_433_S(@Nullable e_933_M colorIn) {
        super(BlockEntityType.C_2741_M);
        this.t_148_a = colorIn;
    }

    public a_433_S() {
        this((e_933_M)null);
        this.s_956_w = true;
    }

    @Override
    public void P_1922_E() {
        this.v_4262_N();
        if (this.u_1723_Y == lightning.product.a_433_S$n_1700_B.J_1907_R || this.u_1723_Y == lightning.product.a_433_S$n_1700_B.G_564_y) {
            this.M_588_G();
        }
    }

    protected void v_4262_N() {
        this.w_1484_f = this.v_4262_N;
        switch (this.u_1723_Y.ordinal()) {
            case 0: {
                this.v_4262_N = 0.0f;
                break;
            }
            case 1: {
                this.v_4262_N += 0.1f;
                if (!(this.v_4262_N >= 1.0f)) break;
                this.M_588_G();
                this.u_1723_Y = lightning.product.a_433_S$n_1700_B.R_4764_Y;
                this.v_4262_N = 1.0f;
                this.P_4830_p();
                break;
            }
            case 3: {
                this.v_4262_N -= 0.1f;
                if (!(this.v_4262_N <= 0.0f)) break;
                this.u_1723_Y = lightning.product.a_433_S$n_1700_B.n_1700_B;
                this.v_4262_N = 0.0f;
                this.P_4830_p();
                break;
            }
            case 2: {
                this.v_4262_N = 1.0f;
            }
        }
    }

    public n_1700_B w_1484_f() {
        return this.u_1723_Y;
    }

    public I_4817_s n_1700_B(K_4074_S state) {
        return this.J_1907_R(state.R_4764_Y(Y_3462_U.P_4830_p));
    }

    public I_4817_s J_1907_R(b_257_Y direction) {
        float f = this.n_1700_B(1.0f);
        return x_268_Y.J_1907_R().n_1700_B().expand(0.5f * f * (float)direction.t_148_a(), 0.5f * f * (float)direction.s_956_w(), 0.5f * f * (float)direction.u_2550_I());
    }

    private I_4817_s R_4764_Y(b_257_Y directionIn) {
        b_257_Y direction = directionIn.u_1723_Y();
        return this.J_1907_R(directionIn).contract(direction.t_148_a(), direction.s_956_w(), direction.u_2550_I());
    }

    private void M_588_G() {
        b_257_Y direction;
        I_4817_s axisalignedbb;
        List<N_4263_v> list;
        K_4074_S blockstate = this.u_2550_I.getBlockState(this.x_607_J());
        if (blockstate.J_1907_R() instanceof Y_3462_U && !(list = this.u_2550_I.n_1700_B((N_4263_v)null, axisalignedbb = this.R_4764_Y(direction = blockstate.R_4764_Y(Y_3462_U.P_4830_p)).offset(this.M_588_G))).isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                N_4263_v entity = list.get(i);
                if (entity.h_() == w_1454_v.G_564_y) continue;
                double d0 = 0.0;
                double d1 = 0.0;
                double d2 = 0.0;
                I_4817_s axisalignedbb1 = entity.i_601_W();
                switch (direction.h_1847_R()) {
                    case n_1700_B: {
                        d0 = direction.P_1922_E() == b_257_Y.J_1907_R.n_1700_B ? axisalignedbb.maxX - axisalignedbb1.minX : axisalignedbb1.maxX - axisalignedbb.minX;
                        d0 += 0.01;
                        break;
                    }
                    case J_1907_R: {
                        d1 = direction.P_1922_E() == b_257_Y.J_1907_R.n_1700_B ? axisalignedbb.maxY - axisalignedbb1.minY : axisalignedbb1.maxY - axisalignedbb.minY;
                        d1 += 0.01;
                        break;
                    }
                    case R_4764_Y: {
                        d2 = direction.P_1922_E() == b_257_Y.J_1907_R.n_1700_B ? axisalignedbb.maxZ - axisalignedbb1.minZ : axisalignedbb1.maxZ - axisalignedbb.minZ;
                        d2 += 0.01;
                    }
                }
                entity.n_1700_B(L_461_d.G_564_y, new e_2866_D(d0 * (double)direction.t_148_a(), d1 * (double)direction.s_956_w(), d2 * (double)direction.u_2550_I()));
            }
        }
    }

    @Override
    public int Y_259_p() {
        return this.J_1907_R.size();
    }

    @Override
    public boolean a_(int id, int type) {
        if (id == 1) {
            this.R_4764_Y = type;
            if (type == 0) {
                this.u_1723_Y = lightning.product.a_433_S$n_1700_B.G_564_y;
                this.P_4830_p();
            }
            if (type == 1) {
                this.u_1723_Y = lightning.product.a_433_S$n_1700_B.J_1907_R;
                this.P_4830_p();
            }
            return true;
        }
        return super.a_(id, type);
    }

    private void P_4830_p() {
        this.e_4240_b().n_1700_B((LevelAccessor)this.c_3005_b(), this.x_607_J(), 3);
    }

    @Override
    public void b_(a_3913_L player) {
        if (!player.d_2461_k()) {
            if (this.R_4764_Y < 0) {
                this.R_4764_Y = 0;
            }
            ++this.R_4764_Y;
            this.u_2550_I.n_1700_B(this.M_588_G, this.e_4240_b().J_1907_R(), 1, this.R_4764_Y);
            if (this.R_4764_Y == 1) {
                this.u_2550_I.n_1700_B((a_3913_L)null, this.M_588_G, SoundEvents.B_1335_M, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
            }
        }
    }

    @Override
    public void J_1907_R(a_3913_L player) {
        if (!player.d_2461_k()) {
            --this.R_4764_Y;
            this.u_2550_I.n_1700_B(this.M_588_G, this.e_4240_b().J_1907_R(), 1, this.R_4764_Y);
            if (this.R_4764_Y <= 0) {
                this.u_2550_I.n_1700_B((a_3913_L)null, this.M_588_G, SoundEvents.t_4057_p, D_38_f.P_1922_E, 0.5f, this.u_2550_I.w_1457_N.nextFloat() * 0.1f + 0.9f);
            }
        }
    }

    @Override
    protected x_282_a F_() {
        return new F_2904_S("container.shulkerBox");
    }

    @Override
    public void n_1700_B(K_4074_S state, U_2912_j nbt) {
        super.n_1700_B(state, nbt);
        this.G_564_y(nbt);
    }

    @Override
    public U_2912_j n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        return this.P_1922_E(compound);
    }

    public void G_564_y(U_2912_j compound) {
        this.J_1907_R = NonNullList.n_1700_B(this.Y_259_p(), Z_1993_T.J_1907_R);
        if (!this.J_1907_R(compound) && compound.R_4764_Y("Items", 9)) {
            ContainerHelper.J_1907_R(compound, this.J_1907_R);
        }
    }

    public U_2912_j P_1922_E(U_2912_j compound) {
        if (!this.R_4764_Y(compound)) {
            ContainerHelper.n_1700_B(compound, this.J_1907_R, false);
        }
        return compound;
    }

    @Override
    protected NonNullList<Z_1993_T> L_() {
        return this.J_1907_R;
    }

    @Override
    protected void n_1700_B(NonNullList<Z_1993_T> itemsIn) {
        this.J_1907_R = itemsIn;
    }

    @Override
    public int[] n_1700_B(b_257_Y side) {
        return n_1700_B;
    }

    @Override
    public boolean n_1700_B(int index, Z_1993_T itemStackIn, @Nullable b_257_Y direction) {
        return !(T_2915_h.n_1700_B(itemStackIn.J_1907_R()) instanceof Y_3462_U);
    }

    @Override
    public boolean J_1907_R(int index, Z_1993_T stack, b_257_Y direction) {
        return true;
    }

    public float n_1700_B(float p_190585_1_) {
        return u_530_F.v_4262_N(p_190585_1_, this.w_1484_f, this.v_4262_N);
    }

    @Nullable
    public e_933_M s_956_w() {
        if (this.s_956_w) {
            this.t_148_a = Y_3462_U.n_1700_B(this.e_4240_b().J_1907_R());
            this.s_956_w = false;
        }
        return this.t_148_a;
    }

    @Override
    protected a_2900_S n_1700_B(int id, W_3491_f player) {
        return new x_353_w(id, player, this);
    }

    public boolean u_2550_I() {
        return this.u_1723_Y == lightning.product.a_433_S$n_1700_B.n_1700_B;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.a_433_S$n_1700_B.n_1700_B();
        }
    }
}


