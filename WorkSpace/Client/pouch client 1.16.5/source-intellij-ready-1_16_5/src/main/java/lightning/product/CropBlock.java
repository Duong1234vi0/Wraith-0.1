/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BonemealableBlock;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.X_1275_n;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_1803_e;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.y_2012_u;

public class CropBlock
extends BushBlock
implements BonemealableBlock {
    public static final g_88_D h_1847_R = BlockStateProperties.i_1637_u;
    private static final s_1395_c[] P_4830_p = new s_1395_c[]{T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    protected CropBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(this.J_1907_R(), 0));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p[state.R_4764_Y(this.J_1907_R())];
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return state.n_1700_B(a_3742_W.Z_735_d);
    }

    public g_88_D J_1907_R() {
        return h_1847_R;
    }

    public int t_148_a() {
        return 7;
    }

    protected int w_1484_f(K_4074_S state) {
        return state.R_4764_Y(this.J_1907_R());
    }

    public K_4074_S J_1907_R(int age) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(this.J_1907_R(), age);
    }

    public boolean t_148_a(K_4074_S state) {
        return state.R_4764_Y(this.J_1907_R()) >= this.t_148_a();
    }

    @Override
    public boolean a_(K_4074_S state) {
        return !this.t_148_a(state);
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        float f;
        int i;
        if (worldIn.n_1700_B(pos, 0) >= 9 && (i = this.w_1484_f(state)) < this.t_148_a() && random.nextInt((int)(25.0f / (f = CropBlock.n_1700_B(this, worldIn, pos))) + 1) == 0) {
            worldIn.n_1700_B(pos, this.J_1907_R(i + 1), 2);
        }
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state) {
        int j;
        int i = this.w_1484_f(state) + this.n_1700_B(worldIn);
        if (i > (j = this.t_148_a())) {
            i = j;
        }
        worldIn.n_1700_B(pos, this.J_1907_R(i), 2);
    }

    protected int n_1700_B(b_4507_u worldIn) {
        return u_530_F.n_1700_B(worldIn.w_1457_N, 2, 5);
    }

    protected static float n_1700_B(T_2915_h blockIn, BlockGetter worldIn, c_1514_x pos) {
        boolean flag1;
        float f = 1.0f;
        c_1514_x blockpos = pos.down();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0f;
                K_4074_S blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.n_1700_B(a_3742_W.Z_735_d)) {
                    f1 = 1.0f;
                    if (blockstate.R_4764_Y(y_2012_u.P_4830_p) > 0) {
                        f1 = 3.0f;
                    }
                }
                if (i != 0 || j != 0) {
                    f1 /= 4.0f;
                }
                f += f1;
            }
        }
        c_1514_x blockpos1 = pos.north();
        c_1514_x blockpos2 = pos.south();
        c_1514_x blockpos3 = pos.west();
        c_1514_x blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).J_1907_R() || blockIn == worldIn.getBlockState(blockpos4).J_1907_R();
        boolean bl = flag1 = blockIn == worldIn.getBlockState(blockpos1).J_1907_R() || blockIn == worldIn.getBlockState(blockpos2).J_1907_R();
        if (flag && flag1) {
            f /= 2.0f;
        } else {
            boolean flag2;
            boolean bl2 = flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).J_1907_R() || blockIn == worldIn.getBlockState(blockpos4.north()).J_1907_R() || blockIn == worldIn.getBlockState(blockpos4.south()).J_1907_R() || blockIn == worldIn.getBlockState(blockpos3.south()).J_1907_R();
            if (flag2) {
                f /= 2.0f;
            }
        }
        return f;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return (worldIn.n_1700_B(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && super.n_1700_B(state, worldIn, pos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (entityIn instanceof X_1275_n && worldIn.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
            worldIn.n_1700_B(pos, true, entityIn);
        }
        super.n_1700_B(state, worldIn, pos, entityIn);
    }

    protected q_1803_e s_956_w() {
        return Items.G_4691_Q;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return new Z_1993_T(this.s_956_w());
    }

    @Override
    public boolean n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state, boolean isClient) {
        return !this.t_148_a(state);
    }

    @Override
    public boolean n_1700_B(b_4507_u worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        return true;
    }

    @Override
    public void n_1700_B(e_3591_l worldIn, Random rand, c_1514_x pos, K_4074_S state) {
        this.n_1700_B((b_4507_u)worldIn, pos, state);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{h_1847_R});
    }
}


