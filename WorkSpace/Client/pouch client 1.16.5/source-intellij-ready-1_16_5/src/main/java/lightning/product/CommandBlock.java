/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.H_1468_N;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1368_k;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.d_742_e;
import lightning.product.e_3591_l;
import lightning.product.DirectionProperty;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.DirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandBlock
extends BaseEntityBlock {
    private static final Logger Q_4569_t = LogManager.getLogger();
    public static final DirectionProperty P_4830_p = DirectionalBlock.P_4830_p;
    public static final U_1266_O h_1847_R = BlockStateProperties.R_4764_Y;

    public CommandBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, false));
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        T_1368_k commandblocktileentity = new T_1368_k();
        commandblocktileentity.J_1907_R(this == a_3742_W.ItemsCooldown);
        return commandblocktileentity;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        i_2154_H tileentity;
        if (!worldIn.Y_259_p && (tileentity = worldIn.getTileEntity(pos)) instanceof T_1368_k) {
            T_1368_k commandblocktileentity = (T_1368_k)tileentity;
            boolean flag = worldIn.Y_601_j(pos);
            boolean flag1 = commandblocktileentity.v_4262_N();
            commandblocktileentity.n_1700_B(flag);
            if (!flag1 && !commandblocktileentity.w_1484_f() && commandblocktileentity.h_1847_R() != T_1368_k.n_1700_B.n_1700_B && flag) {
                commandblocktileentity.M_588_G();
                worldIn.u_2550_I().n_1700_B(pos, this, 1);
            }
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof T_1368_k) {
            T_1368_k commandblocktileentity = (T_1368_k)tileentity;
            d_742_e commandblocklogic = commandblocktileentity.P_1922_E();
            boolean flag = !H_1468_N.J_1907_R(commandblocklogic.w_1484_f());
            T_1368_k.n_1700_B commandblocktileentity$mode = commandblocktileentity.h_1847_R();
            boolean flag1 = commandblocktileentity.u_2550_I();
            if (commandblocktileentity$mode == T_1368_k.n_1700_B.J_1907_R) {
                commandblocktileentity.M_588_G();
                if (flag1) {
                    this.n_1700_B(state, (b_4507_u)worldIn, pos, commandblocklogic, flag);
                } else if (commandblocktileentity.Q_4569_t()) {
                    commandblocklogic.n_1700_B(0);
                }
                if (commandblocktileentity.v_4262_N() || commandblocktileentity.w_1484_f()) {
                    worldIn.Q_2552_b().n_1700_B(pos, this, 1);
                }
            } else if (commandblocktileentity$mode == T_1368_k.n_1700_B.R_4764_Y) {
                if (flag1) {
                    this.n_1700_B(state, (b_4507_u)worldIn, pos, commandblocklogic, flag);
                } else if (commandblocktileentity.Q_4569_t()) {
                    commandblocklogic.n_1700_B(0);
                }
            }
            worldIn.R_4764_Y(pos, this);
        }
    }

    private void n_1700_B(K_4074_S state, b_4507_u world, c_1514_x pos, d_742_e logic, boolean canTrigger) {
        if (canTrigger) {
            logic.n_1700_B(world);
        } else {
            logic.n_1700_B(0);
        }
        CommandBlock.n_1700_B(world, pos, state.R_4764_Y(P_4830_p));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof T_1368_k && player.ModuleManager()) {
            player.n_1700_B((T_1368_k)tileentity);
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        return tileentity instanceof T_1368_k ? ((T_1368_k)tileentity).P_1922_E().u_1723_Y() : 0;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof T_1368_k) {
            T_1368_k commandblocktileentity = (T_1368_k)tileentity;
            d_742_e commandblocklogic = commandblocktileentity.P_1922_E();
            if (stack.Y_601_j()) {
                commandblocklogic.n_1700_B(stack.multiplayerClientSuggestionProvider());
            }
            if (!worldIn.Y_259_p) {
                if (stack.J_1907_R("BlockEntityTag") == null) {
                    commandblocklogic.n_1700_B(worldIn.H_1990_U().J_1907_R(A_2352_Z.h_1847_R));
                    commandblocktileentity.J_1907_R(this == a_3742_W.ItemsCooldown);
                }
                if (commandblocktileentity.h_1847_R() == T_1368_k.n_1700_B.n_1700_B) {
                    boolean flag = worldIn.Y_601_j(pos);
                    commandblocktileentity.n_1700_B(flag);
                }
            }
        }
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

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, context.R_4764_Y().u_1723_Y());
    }

    private static void n_1700_B(b_4507_u world, c_1514_x pos, b_257_Y direction) {
        c_1514_x.n_1700_B blockpos$mutable = pos.toMutable();
        A_2352_Z gamerules = world.H_1990_U();
        int i = gamerules.R_4764_Y(A_2352_Z.Q_2552_b);
        while (i-- > 0) {
            T_1368_k commandblocktileentity;
            i_2154_H tileentity;
            blockpos$mutable.n_1700_B(direction);
            K_4074_S blockstate = world.getBlockState(blockpos$mutable);
            T_2915_h block = blockstate.J_1907_R();
            if (!blockstate.n_1700_B(a_3742_W.ItemsCooldown) || !((tileentity = world.getTileEntity(blockpos$mutable)) instanceof T_1368_k) || (commandblocktileentity = (T_1368_k)tileentity).h_1847_R() != T_1368_k.n_1700_B.n_1700_B) break;
            if (commandblocktileentity.v_4262_N() || commandblocktileentity.w_1484_f()) {
                d_742_e commandblocklogic = commandblocktileentity.P_1922_E();
                if (commandblocktileentity.M_588_G()) {
                    if (!commandblocklogic.n_1700_B(world)) break;
                    world.R_4764_Y((c_1514_x)blockpos$mutable, block);
                } else if (commandblocktileentity.Q_4569_t()) {
                    commandblocklogic.n_1700_B(0);
                }
            }
            direction = blockstate.R_4764_Y(P_4830_p);
        }
        if (i <= 0) {
            int j = Math.max(gamerules.R_4764_Y(A_2352_Z.Q_2552_b), 0);
            Q_4569_t.warn("Command Block chain tried to execute more than {} steps!", (Object)j);
        }
    }
}



