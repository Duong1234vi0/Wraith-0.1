/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.E_4668_a;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;
import lightning.product.MerchantOffer;

public class ShowTradesToPlayer
extends Behavior<L_2225_p> {
    @Nullable
    private Z_1993_T n_1700_B;
    private final List<Z_1993_T> R_4764_Y = Lists.newArrayList();
    private int G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public ShowTradesToPlayer(int durationMinIn, int durationMaxIn) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.n_1700_B)), durationMinIn, durationMaxIn);
    }

    @Override
    public boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        E_4668_a<L_2225_p> brain = owner.y_1945_D();
        if (!brain.R_4764_Y(MemoryModuleType.t_1786_h).isPresent()) {
            return false;
        }
        r_4811_B livingentity = brain.R_4764_Y(MemoryModuleType.t_1786_h).get();
        return livingentity.f_4016_n() == t_5_h.g_4106_L && owner.RealmsLongRunningMcoTaskScreen() && livingentity.RealmsLongRunningMcoTaskScreen() && !owner.d_() && owner.G_564_y((N_4263_v)livingentity) <= 17.0;
    }

    @Override
    public boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return this.n_1700_B(worldIn, entityIn) && this.u_1723_Y > 0 && entityIn.y_1945_D().R_4764_Y(MemoryModuleType.t_1786_h).isPresent();
    }

    @Override
    public void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        super.G_564_y(worldIn, entityIn, gameTimeIn);
        this.R_4764_Y(entityIn);
        this.G_564_y = 0;
        this.P_1922_E = 0;
        this.u_1723_Y = 40;
    }

    @Override
    public void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        r_4811_B livingentity = this.R_4764_Y(owner);
        this.n_1700_B(livingentity, owner);
        if (!this.R_4764_Y.isEmpty()) {
            this.G_564_y(owner);
        } else {
            owner.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
            this.u_1723_Y = Math.min(this.u_1723_Y, 40);
        }
        --this.u_1723_Y;
    }

    @Override
    public void G_564_y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        super.J_1907_R(worldIn, entityIn, gameTimeIn);
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.t_1786_h);
        entityIn.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
        this.n_1700_B = null;
    }

    private void n_1700_B(r_4811_B p_220556_1_, L_2225_p p_220556_2_) {
        boolean flag = false;
        Z_1993_T itemstack = p_220556_1_.A_2714_y();
        if (this.n_1700_B == null || !Z_1993_T.R_4764_Y(this.n_1700_B, itemstack)) {
            this.n_1700_B = itemstack;
            flag = true;
            this.R_4764_Y.clear();
        }
        if (flag && !this.n_1700_B.n_1700_B()) {
            this.J_1907_R(p_220556_2_);
            if (!this.R_4764_Y.isEmpty()) {
                this.u_1723_Y = 900;
                this.n_1700_B(p_220556_2_);
            }
        }
    }

    private void n_1700_B(L_2225_p p_220558_1_) {
        p_220558_1_.n_1700_B(e_1174_E.n_1700_B, this.R_4764_Y.get(0));
    }

    private void J_1907_R(L_2225_p p_220555_1_) {
        for (MerchantOffer merchantoffer : p_220555_1_.J_1907_R()) {
            if (merchantoffer.M_182_A() || !this.n_1700_B(merchantoffer)) continue;
            this.R_4764_Y.add(merchantoffer.G_564_y());
        }
    }

    private boolean n_1700_B(MerchantOffer p_220554_1_) {
        return Z_1993_T.R_4764_Y(this.n_1700_B, p_220554_1_.J_1907_R()) || Z_1993_T.R_4764_Y(this.n_1700_B, p_220554_1_.R_4764_Y());
    }

    private r_4811_B R_4764_Y(L_2225_p p_220557_1_) {
        E_4668_a<L_2225_p> brain = p_220557_1_.y_1945_D();
        r_4811_B livingentity = brain.R_4764_Y(MemoryModuleType.t_1786_h).get();
        brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(livingentity, true));
        return livingentity;
    }

    private void G_564_y(L_2225_p p_220553_1_) {
        if (this.R_4764_Y.size() >= 2 && ++this.G_564_y >= 40) {
            ++this.P_1922_E;
            this.G_564_y = 0;
            if (this.P_1922_E > this.R_4764_Y.size() - 1) {
                this.P_1922_E = 0;
            }
            p_220553_1_.n_1700_B(e_1174_E.n_1700_B, this.R_4764_Y.get(this.P_1922_E));
        }
    }

    @Override
    public /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.G_564_y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    public /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


