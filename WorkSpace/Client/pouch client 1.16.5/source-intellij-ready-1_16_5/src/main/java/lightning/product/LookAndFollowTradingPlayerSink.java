/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.E_4668_a;
import lightning.product.WalkTarget;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3913_L;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class LookAndFollowTradingPlayerSink
extends Behavior<L_2225_p> {
    private final float n_1700_B;

    public LookAndFollowTradingPlayerSink(float speedIn) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y)), Integer.MAX_VALUE);
        this.n_1700_B = speedIn;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        a_3913_L playerentity = owner.n_1700_B();
        return owner.RealmsLongRunningMcoTaskScreen() && playerentity != null && !owner.RowButton() && !owner.Ops && owner.G_564_y((N_4263_v)playerentity) <= 16.0 && playerentity.H_1873_g != null;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.n_1700_B(worldIn, entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        this.n_1700_B(entityIn);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        E_4668_a<L_2225_p> brain = entityIn.y_1945_D();
        brain.J_1907_R(MemoryModuleType.P_4830_p);
        brain.J_1907_R(MemoryModuleType.h_1847_R);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        this.n_1700_B(owner);
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    private void n_1700_B(L_2225_p owner) {
        E_4668_a<L_2225_p> brain = owner.y_1945_D();
        brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new o_4722_d(owner.n_1700_B(), false), this.n_1700_B, 2));
        brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(owner.n_1700_B(), true));
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.R_4764_Y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


