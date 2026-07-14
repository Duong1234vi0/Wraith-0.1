/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Projectile;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.F_1241_B;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.PrimedTnt;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class V_883_W
extends T_2915_h {
    public static final U_1266_O P_4830_p = BlockStateProperties.H_2857_Y;

    public V_883_W(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, false));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!oldState.n_1700_B(state.J_1907_R()) && worldIn.Y_601_j(pos)) {
            V_883_W.n_1700_B(worldIn, pos);
            worldIn.n_1700_B(pos, false);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        if (worldIn.Y_601_j(pos)) {
            V_883_W.n_1700_B(worldIn, pos);
            worldIn.n_1700_B(pos, false);
        }
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!worldIn.v_4276_D() && !player.G_624_v() && state.R_4764_Y(P_4830_p).booleanValue()) {
            V_883_W.n_1700_B(worldIn, pos);
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, F_1241_B explosionIn) {
        if (!worldIn.Y_259_p) {
            PrimedTnt tntentity = new PrimedTnt(worldIn, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, explosionIn.G_564_y());
            tntentity.n_1700_B((short)(worldIn.w_1457_N.nextInt(tntentity.v_4262_N() / 4) + tntentity.v_4262_N() / 8));
            worldIn.a_(tntentity);
        }
    }

    public static void n_1700_B(b_4507_u world, c_1514_x worldIn) {
        V_883_W.n_1700_B(world, worldIn, (r_4811_B)null);
    }

    private static void n_1700_B(b_4507_u worldIn, c_1514_x pos, @Nullable r_4811_B entityIn) {
        if (!worldIn.Y_259_p) {
            PrimedTnt tntentity = new PrimedTnt(worldIn, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, entityIn);
            worldIn.a_(tntentity);
            worldIn.n_1700_B((a_3913_L)null, tntentity.O_3598_v(), tntentity.X_2960_b(), tntentity.l_2647_k(), SoundEvents.S_3458_C, D_38_f.P_1922_E, 1.0f, 1.0f);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        q_1613_l item = itemstack.J_1907_R();
        if (item != Items.S_1165_y && item != Items.CraftingTableBlock) {
            return super.n_1700_B(state, worldIn, pos, player, handIn, hit);
        }
        V_883_W.n_1700_B(worldIn, pos, player);
        worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 11);
        if (!player.G_624_v()) {
            if (item == Items.S_1165_y) {
                itemstack.n_1700_B(1, player, (T player1) -> player1.G_564_y(handIn));
            } else {
                itemstack.v_4262_N(1);
            }
        }
        return m_3054_I.n_1700_B(worldIn.Y_259_p);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
        if (!worldIn.Y_259_p) {
            N_4263_v entity = projectile.Y_601_j();
            if (projectile.RealmsPersistence()) {
                c_1514_x blockpos = hit.n_1700_B();
                V_883_W.n_1700_B(worldIn, blockpos, entity instanceof r_4811_B ? (r_4811_B)entity : null);
                worldIn.n_1700_B(blockpos, false);
            }
        }
    }

    @Override
    public boolean n_1700_B(F_1241_B explosionIn) {
        return false;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


