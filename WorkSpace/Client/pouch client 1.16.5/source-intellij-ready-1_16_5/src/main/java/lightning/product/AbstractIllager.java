/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.W_4304_a;
import lightning.product.b_4507_u;
import lightning.product.MobType;
import lightning.product.OpenDoorGoal;
import lightning.product.t_5_h;

public abstract class AbstractIllager
extends W_4304_a {
    protected AbstractIllager(t_5_h<? extends AbstractIllager> type, b_4507_u worldIn) {
        super((t_5_h<? extends W_4304_a>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
    }

    @Override
    public MobType F_2860_q() {
        return MobType.G_564_y;
    }

    public n_1700_B u_1723_Y() {
        return lightning.product.AbstractIllager$n_1700_B.n_1700_B;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f};
        }

        static {
            t_148_a = lightning.product.AbstractIllager$n_1700_B.n_1700_B();
        }
    }

    public class J_1907_R
    extends OpenDoorGoal {
        public J_1907_R(W_4304_a raider) {
            super(raider, false);
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && AbstractIllager.this.J_3635_s();
        }
    }
}


