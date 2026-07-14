/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.DispenseItemBehavior;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_3065_y;
import lightning.product.K_4074_S;
import lightning.product.Container;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.BlockSourceImpl;
import lightning.product.U_1266_O;
import lightning.product.Position;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.j_3341_s;
import lightning.product.l_3848_Y;
import lightning.product.m_3054_I;
import lightning.product.DirectionalBlock;
import lightning.product.DropperBlockEntity;
import lightning.product.PositionImpl;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;
import lightning.product.BlockSource;

public class S_3458_C
extends BaseEntityBlock {
    public static final DirectionProperty P_4830_p = DirectionalBlock.P_4830_p;
    public static final U_1266_O h_1847_R = BlockStateProperties.c_3005_b;
    private static final Map<q_1613_l, DispenseItemBehavior> Q_4569_t = (Map)j_3341_s.n_1700_B(new Object2ObjectOpenHashMap(), (T behaviour) -> behaviour.defaultReturnValue((Object)new DefaultDispenseItemBehavior()));

    public static void n_1700_B(q_1803_e itemIn, DispenseItemBehavior behavior) {
        Q_4569_t.put(itemIn.u_1723_Y(), behavior);
    }

    protected S_3458_C(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof l_3848_Y) {
            player.n_1700_B((l_3848_Y)tileentity);
            if (tileentity instanceof DropperBlockEntity) {
                player.J_1907_R(Stats.D_4792_h);
            } else {
                player.J_1907_R(Stats.l_1233_K);
            }
        }
        return m_3054_I.J_1907_R;
    }

    protected void n_1700_B(e_3591_l worldIn, c_1514_x pos) {
        BlockSourceImpl proxyblocksource = new BlockSourceImpl(worldIn, pos);
        l_3848_Y dispensertileentity = (l_3848_Y)proxyblocksource.u_1723_Y();
        int i = dispensertileentity.v_4262_N();
        if (i < 0) {
            worldIn.R_4764_Y(1001, pos, 0);
        } else {
            Z_1993_T itemstack = dispensertileentity.s_956_w(i);
            DispenseItemBehavior idispenseitembehavior = this.n_1700_B(itemstack);
            if (idispenseitembehavior != DispenseItemBehavior.n_1700_B) {
                dispensertileentity.J_1907_R(i, idispenseitembehavior.dispense(proxyblocksource, itemstack));
            }
        }
    }

    protected DispenseItemBehavior n_1700_B(Z_1993_T stack) {
        return Q_4569_t.get(stack.J_1907_R());
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag = worldIn.Y_601_j(pos) || worldIn.Y_601_j(pos.up());
        boolean flag1 = state.R_4764_Y(h_1847_R);
        if (flag && !flag1) {
            worldIn.u_2550_I().n_1700_B(pos, this, 4);
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, true), 4);
        } else if (!flag && flag1) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, false), 4);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        this.n_1700_B(worldIn, pos);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new l_3848_Y();
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.R_4764_Y().u_1723_Y());
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (stack.Y_601_j() && (tileentity = worldIn.getTileEntity(pos)) instanceof l_3848_Y) {
            ((l_3848_Y)tileentity).n_1700_B(stack.multiplayerClientSuggestionProvider());
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            i_2154_H tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof l_3848_Y) {
                K_3065_y.n_1700_B(worldIn, pos, (Container)((l_3848_Y)tileentity));
                worldIn.R_4764_Y(pos, this);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    public static Position n_1700_B(BlockSource coords) {
        b_257_Y direction = coords.P_1922_E().R_4764_Y(P_4830_p);
        double d0 = coords.n_1700_B() + 0.7 * (double)direction.t_148_a();
        double d1 = coords.J_1907_R() + 0.7 * (double)direction.s_956_w();
        double d2 = coords.R_4764_Y() + 0.7 * (double)direction.u_2550_I();
        return new PositionImpl(d0, d1, d2);
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return a_2900_S.n_1700_B(worldIn.getTileEntity(pos));
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }
}


