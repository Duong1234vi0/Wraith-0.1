/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.NonNullList;
import lightning.product.S_1134_u;
import lightning.product.BlockPlaceContext;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvent;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.SoundType;
import lightning.product.g_3316_o;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.v_3760_Q;
import lightning.product.x_282_a;
import net.minecraft.server.G_564_y;

public class v_1669_V
extends q_1613_l {
    @Deprecated
    private final T_2915_h n_1700_B;

    public v_1669_V(T_2915_h blockIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.n_1700_B = blockIn;
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        m_3054_I actionresulttype = this.n_1700_B(new BlockPlaceContext(context));
        return !actionresulttype.n_1700_B() && this.Y_259_p() ? this.n_1700_B(context.getWorld(), context.getPlayer(), context.getHand()).n_1700_B() : actionresulttype;
    }

    public m_3054_I n_1700_B(BlockPlaceContext context) {
        if (!context.n_1700_B()) {
            return m_3054_I.G_564_y;
        }
        BlockPlaceContext blockitemusecontext = this.J_1907_R(context);
        if (blockitemusecontext == null) {
            return m_3054_I.G_564_y;
        }
        K_4074_S blockstate = this.R_4764_Y(blockitemusecontext);
        if (blockstate == null) {
            return m_3054_I.G_564_y;
        }
        if (!this.n_1700_B(blockitemusecontext, blockstate)) {
            return m_3054_I.G_564_y;
        }
        c_1514_x blockpos = blockitemusecontext.getPos();
        b_4507_u world = blockitemusecontext.getWorld();
        a_3913_L playerentity = blockitemusecontext.getPlayer();
        Z_1993_T itemstack = blockitemusecontext.getItem();
        K_4074_S blockstate1 = world.getBlockState(blockpos);
        T_2915_h block = blockstate1.J_1907_R();
        if (block == blockstate.J_1907_R()) {
            blockstate1 = this.n_1700_B(blockpos, world, itemstack, blockstate1);
            this.n_1700_B(blockpos, world, playerentity, itemstack, blockstate1);
            block.n_1700_B(world, blockpos, blockstate1, playerentity, itemstack);
            if (playerentity instanceof B_4088_l) {
                U_3554_Q.q_2307_F.n_1700_B((B_4088_l)playerentity, blockpos, itemstack);
            }
        }
        SoundType soundtype = blockstate1.Q_4569_t();
        world.n_1700_B(playerentity, blockpos, this.n_1700_B(blockstate1), D_38_f.P_1922_E, (soundtype.n_1700_B() + 1.0f) / 2.0f, soundtype.J_1907_R() * 0.8f);
        if (playerentity == null || !playerentity.C_415_h.G_564_y) {
            itemstack.v_4262_N(1);
        }
        return m_3054_I.n_1700_B(world.Y_259_p);
    }

    protected SoundEvent n_1700_B(K_4074_S state) {
        return state.Q_4569_t().P_1922_E();
    }

    @Nullable
    public BlockPlaceContext J_1907_R(BlockPlaceContext context) {
        return context;
    }

    protected boolean n_1700_B(c_1514_x pos, b_4507_u worldIn, @Nullable a_3913_L player, Z_1993_T stack, K_4074_S state) {
        return v_1669_V.n_1700_B(worldIn, player, pos, stack);
    }

    @Nullable
    protected K_4074_S R_4764_Y(BlockPlaceContext context) {
        K_4074_S blockstate = this.v_4262_N().n_1700_B(context);
        return blockstate != null && this.J_1907_R(context, blockstate) ? blockstate : null;
    }

    private K_4074_S n_1700_B(c_1514_x p_219985_1_, b_4507_u p_219985_2_, Z_1993_T p_219985_3_, K_4074_S p_219985_4_) {
        K_4074_S blockstate = p_219985_4_;
        U_2912_j compoundnbt = p_219985_3_.Q_4569_t();
        if (compoundnbt != null) {
            U_2912_j compoundnbt1 = compoundnbt.M_182_A("BlockStateTag");
            Y_1835_y<T_2915_h, K_4074_S> statecontainer = p_219985_4_.J_1907_R().t_1786_h();
            for (String s : compoundnbt1.G_564_y()) {
                v_3760_Q<?> property = statecontainer.n_1700_B(s);
                if (property == null) continue;
                String s1 = compoundnbt1.R_4764_Y(s).M_588_G();
                blockstate = v_1669_V.n_1700_B(blockstate, property, s1);
            }
        }
        if (blockstate != p_219985_4_) {
            p_219985_2_.n_1700_B(p_219985_1_, blockstate, 2);
        }
        return blockstate;
    }

    private static <T extends Comparable<T>> K_4074_S n_1700_B(K_4074_S p_219988_0_, v_3760_Q<T> p_219988_1_, String p_219988_2_) {
        return p_219988_1_.J_1907_R(p_219988_2_).map(p_219986_2_ -> (K_4074_S)p_219988_0_.n_1700_B(p_219988_1_, p_219986_2_)).orElse(p_219988_0_);
    }

    protected boolean J_1907_R(BlockPlaceContext p_195944_1_, K_4074_S p_195944_2_) {
        a_3913_L playerentity = p_195944_1_.getPlayer();
        CollisionContext iselectioncontext = playerentity == null ? CollisionContext.J_1907_R() : CollisionContext.n_1700_B(playerentity);
        return (!this.P_1922_E() || p_195944_2_.n_1700_B((T_1316_M)p_195944_1_.getWorld(), p_195944_1_.getPos())) && p_195944_1_.getWorld().n_1700_B(p_195944_2_, p_195944_1_.getPos(), iselectioncontext);
    }

    protected boolean P_1922_E() {
        return true;
    }

    protected boolean n_1700_B(BlockPlaceContext context, K_4074_S state) {
        return context.getWorld().n_1700_B(context.getPos(), state, 11);
    }

    public static boolean n_1700_B(b_4507_u worldIn, @Nullable a_3913_L player, c_1514_x pos, Z_1993_T stackIn) {
        i_2154_H tileentity;
        G_564_y minecraftserver = worldIn.T_2506_i();
        if (minecraftserver == null) {
            return false;
        }
        U_2912_j compoundnbt = stackIn.J_1907_R("BlockEntityTag");
        if (compoundnbt != null && (tileentity = worldIn.getTileEntity(pos)) != null) {
            if (!(worldIn.Y_259_p || !tileentity.K_() || player != null && player.ModuleManager())) {
                return false;
            }
            U_2912_j compoundnbt1 = tileentity.n_1700_B(new U_2912_j());
            U_2912_j compoundnbt2 = compoundnbt1.v_4262_N();
            compoundnbt1.n_1700_B(compoundnbt);
            compoundnbt1.J_1907_R("x", pos.getX());
            compoundnbt1.J_1907_R("y", pos.getY());
            compoundnbt1.J_1907_R("z", pos.getZ());
            if (!compoundnbt1.equals(compoundnbt2)) {
                tileentity.n_1700_B(worldIn.getBlockState(pos), compoundnbt1);
                tileentity.J_1907_R();
                return true;
            }
        }
        return false;
    }

    @Override
    public String J_1907_R() {
        return this.v_4262_N().P_4830_p();
    }

    @Override
    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        if (this.n_1700_B(group)) {
            this.v_4262_N().n_1700_B(group, items);
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        super.n_1700_B(stack, worldIn, tooltip, flagIn);
        this.v_4262_N().n_1700_B(stack, (BlockGetter)worldIn, tooltip, flagIn);
    }

    public T_2915_h v_4262_N() {
        return this.n_1700_B;
    }

    public void n_1700_B(Map<T_2915_h, q_1613_l> blockToItemMap, q_1613_l itemIn) {
        blockToItemMap.put(this.v_4262_N(), itemIn);
    }
}



