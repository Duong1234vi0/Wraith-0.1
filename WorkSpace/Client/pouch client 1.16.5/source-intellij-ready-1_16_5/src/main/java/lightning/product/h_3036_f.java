/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.K_4074_S;
import lightning.product.Stats;
import lightning.product.SoundEvent;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_3316_o;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.LevelAccessor;
import lightning.product.x_282_a;
import lightning.product.JukeboxBlock;

public class h_3036_f
extends q_1613_l {
    private static final Map<SoundEvent, h_3036_f> n_1700_B = Maps.newHashMap();
    private final int J_1907_R;
    private final SoundEvent R_4764_Y;

    protected h_3036_f(int comparatorValueIn, SoundEvent soundIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.J_1907_R = comparatorValueIn;
        this.R_4764_Y = soundIn;
        n_1700_B.put(this.R_4764_Y, this);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (blockstate.n_1700_B(a_3742_W.r_2478_U) && !blockstate.R_4764_Y(JukeboxBlock.P_4830_p).booleanValue()) {
            Z_1993_T itemstack = context.getItem();
            if (!world.Y_259_p) {
                ((JukeboxBlock)a_3742_W.r_2478_U).n_1700_B((LevelAccessor)world, blockpos, blockstate, itemstack);
                world.n_1700_B((a_3913_L)null, 1010, blockpos, q_1613_l.n_1700_B(this));
                itemstack.v_4262_N(1);
                a_3913_L playerentity = context.getPlayer();
                if (playerentity != null) {
                    playerentity.J_1907_R(Stats.p_178_J);
                }
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public int v_4262_N() {
        return this.J_1907_R;
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        tooltip.add(this.w_1484_f().n_1700_B(D_4024_W.w_1484_f));
    }

    public MutableComponent w_1484_f() {
        return new F_2904_S(this.J_1907_R() + ".desc");
    }

    @Nullable
    public static h_3036_f n_1700_B(SoundEvent soundIn) {
        return n_1700_B.get(soundIn);
    }

    public SoundEvent t_148_a() {
        return this.R_4764_Y;
    }
}


