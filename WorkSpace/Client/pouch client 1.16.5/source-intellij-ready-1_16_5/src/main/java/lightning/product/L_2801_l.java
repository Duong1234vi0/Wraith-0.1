/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.v_3760_Q;
import lightning.product.w_4067_V;

public class L_2801_l
extends w_4067_V {
    public static final U_1266_O M_182_A = BlockStateProperties.C_2741_M;
    private final n_1700_B t_1786_h;

    protected L_2801_l(n_1700_B sensitivityIn, q_4293_E.P_1922_E propertiesIn) {
        super(propertiesIn);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(M_182_A, false));
        this.t_1786_h = sensitivityIn;
    }

    @Override
    protected int v_4262_N(K_4074_S state) {
        return state.R_4764_Y(M_182_A) != false ? 15 : 0;
    }

    @Override
    protected K_4074_S n_1700_B(K_4074_S state, int strength) {
        return (K_4074_S)state.n_1700_B(M_182_A, strength > 0);
    }

    @Override
    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
        if (this.J_1907_R != Material.q_2307_F && this.J_1907_R != Material.Z_875_P) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.s_3084_y, D_38_f.P_1922_E, 0.3f, 0.6f);
        } else {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.StructureVoidBlock, D_38_f.P_1922_E, 0.3f, 0.8f);
        }
    }

    @Override
    protected void J_1907_R(LevelAccessor worldIn, c_1514_x pos) {
        if (this.J_1907_R != Material.q_2307_F && this.J_1907_R != Material.Z_875_P) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.CoralPlantBlock, D_38_f.P_1922_E, 0.3f, 0.5f);
        } else {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.StructureBlock, D_38_f.P_1922_E, 0.3f, 0.7f);
        }
    }

    @Override
    protected int J_1907_R(b_4507_u worldIn, c_1514_x pos) {
        List<N_4263_v> list;
        I_4817_s axisalignedbb = Q_4569_t.offset(pos);
        switch (this.t_1786_h.ordinal()) {
            case 0: {
                list = worldIn.n_1700_B((N_4263_v)null, axisalignedbb);
                break;
            }
            case 1: {
                list = worldIn.n_1700_B(r_4811_B.class, axisalignedbb);
                break;
            }
            default: {
                return 0;
            }
        }
        if (!list.isEmpty()) {
            for (N_4263_v entity : list) {
                if (entity.P_2947_S()) continue;
                return 15;
            }
        }
        return 0;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{M_182_A});
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] R_4764_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])R_4764_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.L_2801_l$n_1700_B.n_1700_B();
        }
    }
}


