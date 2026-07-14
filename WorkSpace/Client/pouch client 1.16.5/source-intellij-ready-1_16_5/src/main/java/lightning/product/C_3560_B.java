/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.Q_1649_j;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1869_W;
import lightning.product.Nameable;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.t_3286_u;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.ContainerLevelAccess;
import lightning.product.x_282_a;

public class C_3560_B
extends BaseEntityBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    protected C_3560_B(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public boolean J_1907_R(K_4074_S state) {
        return true;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        super.n_1700_B(stateIn, worldIn, pos, rand);
        for (int i = -2; i <= 2; ++i) {
            block1: for (int j = -2; j <= 2; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }
                if (rand.nextInt(16) != 0) continue;
                for (int k = 0; k <= 1; ++k) {
                    c_1514_x blockpos = pos.add(i, k, j);
                    if (!worldIn.getBlockState(blockpos).n_1700_B(a_3742_W.UploadTokenCache)) continue;
                    if (!worldIn.u_1723_Y(pos.add(i / 2, 0, j / 2))) continue block1;
                    worldIn.n_1700_B(ParticleTypes.w_1457_N, (double)pos.getX() + 0.5, (double)pos.getY() + 2.0, (double)pos.getZ() + 0.5, (double)((float)i + rand.nextFloat()) - 0.5, (double)((float)k - rand.nextFloat() - 1.0f), (double)((float)j + rand.nextFloat()) - 0.5);
                }
            }
        }
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new c_1869_W();
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        player.n_1700_B(state.J_1907_R(worldIn, pos));
        return m_3054_I.J_1907_R;
    }

    @Override
    @Nullable
    public t_3286_u n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof c_1869_W) {
            x_282_a itextcomponent = ((Nameable)((Object)tileentity)).c_();
            return new SimpleMenuProvider((id, inventory, player) -> new Q_1649_j(id, inventory, ContainerLevelAccess.n_1700_B(worldIn, pos)), itextcomponent);
        }
        return null;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof c_1869_W) {
            ((c_1869_W)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


