/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.U_3554_Q;
import lightning.product.HalfTransparentBlock;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.X_426_i;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_1462_f;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.PrimedTnt;
import lightning.product.y_4319_k;

public class HoneyBlock
extends HalfTransparentBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);

    public HoneyBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    private static boolean R_4764_Y(N_4263_v entity) {
        return entity instanceof r_4811_B || entity instanceof y_4319_k || entity instanceof PrimedTnt || entity instanceof g_1462_f;
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn, float fallDistance) {
        entityIn.n_1700_B(SoundEvents.AttackAura, 1.0f, 1.0f);
        if (!worldIn.Y_259_p) {
            worldIn.n_1700_B(entityIn, (byte)54);
        }
        if (entityIn.R_4764_Y(fallDistance, 0.2f)) {
            entityIn.n_1700_B(this.u_1723_Y.v_4262_N(), this.u_1723_Y.n_1700_B() * 0.5f, this.u_1723_Y.J_1907_R() * 0.75f);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (this.n_1700_B(pos, entityIn)) {
            this.n_1700_B(entityIn, pos);
            this.G_564_y(entityIn);
            this.n_1700_B(worldIn, entityIn);
        }
        super.n_1700_B(state, worldIn, pos, entityIn);
    }

    private boolean n_1700_B(c_1514_x pos, N_4263_v entity) {
        if (entity.M_1641_O()) {
            return false;
        }
        if (entity.X_2960_b() > (double)pos.getY() + 0.9375 - 1.0E-7) {
            return false;
        }
        if (entity.I_4348_c().R_4764_Y >= -0.08) {
            return false;
        }
        double d0 = Math.abs((double)pos.getX() + 0.5 - entity.O_3598_v());
        double d1 = Math.abs((double)pos.getZ() + 0.5 - entity.l_2647_k());
        double d2 = 0.4375 + (double)(entity.C_415_h() / 2.0f);
        return d0 + 1.0E-7 > d2 || d1 + 1.0E-7 > d2;
    }

    private void n_1700_B(N_4263_v entity, c_1514_x pos) {
        if (entity instanceof B_4088_l && entity.O_508_d.X_933_l() % 20L == 0L) {
            U_3554_Q.z_1737_N.n_1700_B((B_4088_l)entity, entity.O_508_d.getBlockState(pos));
        }
    }

    private void G_564_y(N_4263_v entity) {
        e_2866_D vector3d = entity.I_4348_c();
        if (vector3d.R_4764_Y < -0.13) {
            double d0 = -0.05 / vector3d.R_4764_Y;
            entity.v_4262_N(new e_2866_D(vector3d.J_1907_R * d0, -0.05, vector3d.G_564_y * d0));
        } else {
            entity.v_4262_N(new e_2866_D(vector3d.J_1907_R, -0.05, vector3d.G_564_y));
        }
        entity.U_1241_n = 0.0f;
    }

    private void n_1700_B(b_4507_u world, N_4263_v entity) {
        if (HoneyBlock.R_4764_Y(entity)) {
            if (world.w_1457_N.nextInt(5) == 0) {
                entity.n_1700_B(SoundEvents.AttackAura, 1.0f, 1.0f);
            }
            if (!world.Y_259_p && world.w_1457_N.nextInt(5) == 0) {
                world.n_1700_B(entity, (byte)53);
            }
        }
    }

    public static void n_1700_B(N_4263_v entity) {
        HoneyBlock.n_1700_B(entity, 5);
    }

    public static void J_1907_R(N_4263_v entity) {
        HoneyBlock.n_1700_B(entity, 10);
    }

    private static void n_1700_B(N_4263_v entity, int particleCount) {
        if (entity.O_508_d.Y_259_p) {
            K_4074_S blockstate = a_3742_W.B_1335_M.multiplayerClientSuggestionProvider();
            for (int i = 0; i < particleCount; ++i) {
                entity.O_508_d.n_1700_B(new X_426_i(ParticleTypes.G_564_y, blockstate), entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), 0.0, 0.0, 0.0);
            }
        }
    }
}



