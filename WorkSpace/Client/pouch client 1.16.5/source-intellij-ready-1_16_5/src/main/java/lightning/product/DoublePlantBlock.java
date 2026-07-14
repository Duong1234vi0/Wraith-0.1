/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BushBlock;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.g_3212_H;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;

public class DoublePlantBlock
extends BushBlock {
    public static final e_563_h<g_3212_H> P_4830_p = BlockStateProperties.T_3594_S;

    public DoublePlantBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, g_3212_H.J_1907_R));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        g_3212_H doubleblockhalf = stateIn.R_4764_Y(P_4830_p);
        if (facing.h_1847_R() != b_257_Y.n_1700_B.J_1907_R || doubleblockhalf == g_3212_H.J_1907_R != (facing == b_257_Y.J_1907_R) || facingState.n_1700_B(this) && facingState.R_4764_Y(P_4830_p) != doubleblockhalf) {
            return doubleblockhalf == g_3212_H.J_1907_R && facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    @Nullable
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        c_1514_x blockpos = context.getPos();
        return blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).n_1700_B(context) ? super.n_1700_B(context) : null;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        worldIn.n_1700_B(pos.up(), (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, g_3212_H.n_1700_B), 3);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        if (state.R_4764_Y(P_4830_p) != g_3212_H.n_1700_B) {
            return super.n_1700_B(state, worldIn, pos);
        }
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        return blockstate.n_1700_B(this) && blockstate.R_4764_Y(P_4830_p) == g_3212_H.J_1907_R;
    }

    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos, int flags) {
        worldIn.n_1700_B(pos, (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, g_3212_H.J_1907_R), flags);
        worldIn.n_1700_B(pos.up(), (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, g_3212_H.n_1700_B), flags);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        if (!worldIn.Y_259_p) {
            if (player.G_624_v()) {
                DoublePlantBlock.J_1907_R(worldIn, pos, state, player);
            } else {
                DoublePlantBlock.n_1700_B(state, worldIn, pos, (i_2154_H)null, (N_4263_v)player, player.A_2714_y());
            }
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, a_3913_L player, c_1514_x pos, K_4074_S state, @Nullable i_2154_H te, Z_1993_T stack) {
        super.n_1700_B(worldIn, player, pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), te, stack);
    }

    protected static void J_1907_R(b_4507_u world, c_1514_x pos, K_4074_S state, a_3913_L player) {
        c_1514_x blockpos;
        K_4074_S blockstate;
        g_3212_H doubleblockhalf = state.R_4764_Y(P_4830_p);
        if (doubleblockhalf == g_3212_H.n_1700_B && (blockstate = world.getBlockState(blockpos = pos.down())).J_1907_R() == state.J_1907_R() && blockstate.R_4764_Y(P_4830_p) == g_3212_H.J_1907_R) {
            world.n_1700_B(blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 35);
            world.n_1700_B(player, 2001, blockpos, T_2915_h.s_956_w(blockstate));
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public q_4293_E.G_564_y R_4764_Y() {
        return q_4293_E.G_564_y.J_1907_R;
    }

    @Override
    public long n_1700_B(K_4074_S state, c_1514_x pos) {
        return u_530_F.R_4764_Y(pos.getX(), pos.down(state.R_4764_Y(P_4830_p) == g_3212_H.J_1907_R ? 0 : 1).getY(), pos.getZ());
    }
}


