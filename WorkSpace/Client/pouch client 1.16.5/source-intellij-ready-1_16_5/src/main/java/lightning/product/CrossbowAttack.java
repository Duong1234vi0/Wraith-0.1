/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.H_2333_J;
import lightning.product.M_4954_p;
import lightning.product.S_50_d;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.RangedAttackMob;
import lightning.product.o_4722_d;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class CrossbowAttack<E extends Z_530_i, T extends r_4811_B>
extends Behavior<E> {
    private int n_1700_B;
    private n_1700_B R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.n_1700_B;

    public CrossbowAttack() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B)), 1200);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        r_4811_B livingentity = CrossbowAttack.n_1700_B(owner);
        return ((r_4811_B)owner).n_1700_B(Items.V_2454_J) && a_3236_r.R_4764_Y(owner, livingentity) && a_3236_r.n_1700_B(owner, livingentity, 0);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        return ((r_4811_B)entityIn).y_1945_D().n_1700_B(MemoryModuleType.Q_4569_t) && this.n_1700_B(worldIn, entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, E owner, long gameTime) {
        r_4811_B livingentity = CrossbowAttack.n_1700_B(owner);
        this.J_1907_R((Z_530_i)owner, livingentity);
        this.n_1700_B(owner, livingentity);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        if (((r_4811_B)entityIn).Y_601_j()) {
            ((r_4811_B)entityIn).Y_259_p();
        }
        if (((r_4811_B)entityIn).n_1700_B(Items.V_2454_J)) {
            ((M_4954_p)entityIn).J_1907_R(false);
            Z_1630_j.n_1700_B(((r_4811_B)entityIn).B_2580_P(), false);
        }
    }

    private void n_1700_B(E p_233888_1_, r_4811_B p_233888_2_) {
        if (this.R_4764_Y == lightning.product.CrossbowAttack$n_1700_B.n_1700_B) {
            ((r_4811_B)p_233888_1_).J_1907_R(H_2333_J.n_1700_B(p_233888_1_, Items.V_2454_J));
            this.R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.J_1907_R;
            ((M_4954_p)p_233888_1_).J_1907_R(true);
        } else if (this.R_4764_Y == lightning.product.CrossbowAttack$n_1700_B.J_1907_R) {
            Z_1993_T itemstack;
            int i;
            if (!((r_4811_B)p_233888_1_).Y_601_j()) {
                this.R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.n_1700_B;
            }
            if ((i = ((r_4811_B)p_233888_1_).g_1031_K()) >= Z_1630_j.v_4262_N(itemstack = ((r_4811_B)p_233888_1_).B_2580_P())) {
                ((r_4811_B)p_233888_1_).g_134_G();
                this.R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.R_4764_Y;
                this.n_1700_B = 20 + ((r_4811_B)p_233888_1_).M_3508_C().nextInt(20);
                ((M_4954_p)p_233888_1_).J_1907_R(false);
            }
        } else if (this.R_4764_Y == lightning.product.CrossbowAttack$n_1700_B.R_4764_Y) {
            --this.n_1700_B;
            if (this.n_1700_B == 0) {
                this.R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.G_564_y;
            }
        } else if (this.R_4764_Y == lightning.product.CrossbowAttack$n_1700_B.G_564_y) {
            ((RangedAttackMob)p_233888_1_).J_1907_R(p_233888_2_, 1.0f);
            Z_1993_T itemstack1 = ((r_4811_B)p_233888_1_).R_4764_Y(H_2333_J.n_1700_B(p_233888_1_, Items.V_2454_J));
            Z_1630_j.n_1700_B(itemstack1, false);
            this.R_4764_Y = lightning.product.CrossbowAttack$n_1700_B.n_1700_B;
        }
    }

    private void J_1907_R(Z_530_i p_233889_1_, r_4811_B p_233889_2_) {
        p_233889_1_.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(p_233889_2_, true));
    }

    private static r_4811_B n_1700_B(r_4811_B p_233887_0_) {
        return p_233887_0_.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.R_4764_Y(e_3591_l2, (E)((Z_530_i)r_4811_B2), l);
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (E)((Z_530_i)r_4811_B2), l);
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.CrossbowAttack$n_1700_B.n_1700_B();
        }
    }
}


