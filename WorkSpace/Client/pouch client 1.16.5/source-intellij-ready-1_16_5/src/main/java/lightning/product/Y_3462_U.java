/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.A_4919_q;
import lightning.product.LootContextParams;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.MutableComponent;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.O_2369_F;
import lightning.product.NonNullList;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.ShulkerSharedHelper;
import lightning.product.a_2900_S;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_433_S;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.e_933_M;
import lightning.product.g_2336_b;
import lightning.product.g_3316_o;
import lightning.product.i_2154_H;
import lightning.product.ContainerHelper;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.DirectionalBlock;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.v_3760_Q;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;

public class Y_3462_U
extends BaseEntityBlock {
    public static final e_563_h<b_257_Y> P_4830_p = DirectionalBlock.P_4830_p;
    public static final g_2336_b h_1847_R = new g_2336_b("contents");
    @Nullable
    private final e_933_M Q_4569_t;

    public Y_3462_U(@Nullable e_933_M color, q_4293_E.P_1922_E properties) {
        super(properties);
        this.Q_4569_t = color;
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.J_1907_R));
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new a_433_S(this.Q_4569_t);
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.J_1907_R;
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        if (player.d_2461_k()) {
            return m_3054_I.J_1907_R;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof a_433_S) {
            boolean flag;
            a_433_S shulkerboxtileentity = (a_433_S)tileentity;
            if (shulkerboxtileentity.w_1484_f() == a_433_S.n_1700_B.n_1700_B) {
                b_257_Y direction = state.R_4764_Y(P_4830_p);
                flag = worldIn.J_1907_R(ShulkerSharedHelper.n_1700_B(pos, direction));
            } else {
                flag = true;
            }
            if (flag) {
                player.n_1700_B(shulkerboxtileentity);
                player.J_1907_R(Stats.e_1992_r);
                A_4919_q.n_1700_B(player, true);
            }
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.getFace());
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, a_3913_L player) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof a_433_S) {
            a_433_S shulkerboxtileentity = (a_433_S)tileentity;
            if (!worldIn.Y_259_p && player.G_624_v() && !shulkerboxtileentity.Q_2552_b()) {
                Z_1993_T itemstack = Y_3462_U.J_1907_R(this.J_1907_R());
                U_2912_j compoundnbt = shulkerboxtileentity.P_1922_E(new U_2912_j());
                if (!compoundnbt.u_1723_Y()) {
                    itemstack.n_1700_B("BlockEntityTag", compoundnbt);
                }
                if (shulkerboxtileentity.t_3452_g()) {
                    itemstack.n_1700_B(shulkerboxtileentity.k_2302_P());
                }
                n_1494_c itementity = new n_1494_c(worldIn, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemstack);
                itementity.t_148_a();
                worldIn.a_(itementity);
            } else {
                shulkerboxtileentity.G_564_y(player);
            }
        }
        super.n_1700_B(worldIn, pos, state, player);
    }

    @Override
    public List<Z_1993_T> n_1700_B(K_4074_S state, q_1704_m.n_1700_B builder) {
        i_2154_H tileentity = builder.J_1907_R(LootContextParams.w_1484_f);
        if (tileentity instanceof a_433_S) {
            a_433_S shulkerboxtileentity = (a_433_S)tileentity;
            builder = builder.n_1700_B(h_1847_R, (q_1704_m context, Consumer<Z_1993_T> stackConsumer) -> {
                for (int i = 0; i < shulkerboxtileentity.Y_259_p(); ++i) {
                    stackConsumer.accept(shulkerboxtileentity.s_956_w(i));
                }
            });
        }
        return super.n_1700_B(state, builder);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof a_433_S) {
            ((a_433_S)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof a_433_S) {
                worldIn.R_4764_Y(pos, state.J_1907_R());
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable BlockGetter worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        super.n_1700_B(stack, worldIn, tooltip, flagIn);
        U_2912_j compoundnbt = stack.J_1907_R("BlockEntityTag");
        if (compoundnbt != null) {
            if (compoundnbt.R_4764_Y("LootTable", 8)) {
                tooltip.add(new U_2871_b("???????"));
            }
            if (compoundnbt.R_4764_Y("Items", 9)) {
                NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B(27, Z_1993_T.J_1907_R);
                ContainerHelper.J_1907_R(compoundnbt, nonnulllist);
                int i = 0;
                int j = 0;
                for (Z_1993_T itemstack : nonnulllist) {
                    if (itemstack.n_1700_B()) continue;
                    ++j;
                    if (i > 4) continue;
                    ++i;
                    MutableComponent iformattabletextcomponent = itemstack.multiplayerClientSuggestionProvider().P_1922_E();
                    iformattabletextcomponent.n_1700_B(" x").n_1700_B(String.valueOf(itemstack.t_4043_B()));
                    tooltip.add(iformattabletextcomponent);
                }
                if (j - i > 0) {
                    tooltip.add(new F_2904_S("container.shulkerBox.more", j - i).n_1700_B(D_4024_W.Y_259_p));
                }
            }
        }
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof a_433_S ? x_268_Y.n_1700_B(((a_433_S)tileentity).n_1700_B(state)) : x_268_Y.J_1907_R();
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return a_2900_S.J_1907_R((Container)((Object)worldIn.getTileEntity(pos)));
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        Z_1993_T itemstack = super.n_1700_B(worldIn, pos, state);
        a_433_S shulkerboxtileentity = (a_433_S)worldIn.getTileEntity(pos);
        U_2912_j compoundnbt = shulkerboxtileentity.P_1922_E(new U_2912_j());
        if (!compoundnbt.u_1723_Y()) {
            itemstack.n_1700_B("BlockEntityTag", compoundnbt);
        }
        return itemstack;
    }

    @Nullable
    public static e_933_M J_1907_R(q_1613_l itemIn) {
        return Y_3462_U.n_1700_B(T_2915_h.n_1700_B(itemIn));
    }

    @Nullable
    public static e_933_M n_1700_B(T_2915_h blockIn) {
        return blockIn instanceof Y_3462_U ? ((Y_3462_U)blockIn).J_1907_R() : null;
    }

    public static T_2915_h n_1700_B(@Nullable e_933_M colorIn) {
        if (colorIn == null) {
            return a_3742_W.k_1052_R;
        }
        switch (colorIn) {
            case n_1700_B: {
                return a_3742_W.Ambience;
            }
            case J_1907_R: {
                return a_3742_W.x_555_z;
            }
            case R_4764_Y: {
                return a_3742_W.AnomalyESP;
            }
            case G_564_y: {
                return a_3742_W.ArmorDurability;
            }
            case P_1922_E: {
                return a_3742_W.Arrows;
            }
            case u_1723_Y: {
                return a_3742_W.AspectRatio;
            }
            case v_4262_N: {
                return a_3742_W.BlockESP;
            }
            case w_1484_f: {
                return a_3742_W.BlockOverlay;
            }
            case t_148_a: {
                return a_3742_W.Chams;
            }
            case s_956_w: {
                return a_3742_W.ChatBubbles;
            }
            default: {
                return a_3742_W.Cosmetics;
            }
            case M_588_G: {
                return a_3742_W.Crosshair;
            }
            case P_4830_p: {
                return a_3742_W.CrystalESP;
            }
            case h_1847_R: {
                return a_3742_W.DistantAlpha;
            }
            case Q_4569_t: {
                return a_3742_W.Emotions;
            }
            case M_182_A: 
        }
        return a_3742_W.EntityESP;
    }

    @Nullable
    public e_933_M J_1907_R() {
        return this.Q_4569_t;
    }

    public static Z_1993_T J_1907_R(@Nullable e_933_M colorIn) {
        return new Z_1993_T(Y_3462_U.n_1700_B(colorIn));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }
}



