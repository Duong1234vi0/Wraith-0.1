/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2530_r;
import lightning.product.TridentItem;
import lightning.product.J_4485_t;
import lightning.product.BowItem;
import lightning.product.O_4882_g;
import lightning.product.R_2515_i;
import lightning.product.T_2915_h;
import lightning.product.Z_1630_j;
import lightning.product.DiggerItem;
import lightning.product.e_1174_E;
import lightning.product.q_1613_l;
import lightning.product.SwordItem;

public abstract sealed class j_123_i
extends Enum<j_123_i> {
    public static final /* enum */ j_123_i n_1700_B = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof R_2515_i;
        }
    };
    public static final /* enum */ j_123_i J_1907_R = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof R_2515_i && ((R_2515_i)itemIn).R_4764_Y() == e_1174_E.R_4764_Y;
        }
    };
    public static final /* enum */ j_123_i R_4764_Y = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof R_2515_i && ((R_2515_i)itemIn).R_4764_Y() == e_1174_E.G_564_y;
        }
    };
    public static final /* enum */ j_123_i G_564_y = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof R_2515_i && ((R_2515_i)itemIn).R_4764_Y() == e_1174_E.P_1922_E;
        }
    };
    public static final /* enum */ j_123_i P_1922_E = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof R_2515_i && ((R_2515_i)itemIn).R_4764_Y() == e_1174_E.u_1723_Y;
        }
    };
    public static final /* enum */ j_123_i u_1723_Y = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof SwordItem;
        }
    };
    public static final /* enum */ j_123_i v_4262_N = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof DiggerItem;
        }
    };
    public static final /* enum */ j_123_i w_1484_f = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof O_4882_g;
        }
    };
    public static final /* enum */ j_123_i t_148_a = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof TridentItem;
        }
    };
    public static final /* enum */ j_123_i s_956_w = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn.P_4830_p();
        }
    };
    public static final /* enum */ j_123_i u_2550_I = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof BowItem;
        }
    };
    public static final /* enum */ j_123_i M_588_G = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof D_2530_r || T_2915_h.n_1700_B(itemIn) instanceof D_2530_r;
        }
    };
    public static final /* enum */ j_123_i P_4830_p = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof Z_1630_j;
        }
    };
    public static final /* enum */ j_123_i h_1847_R = new j_123_i(){

        @Override
        public boolean n_1700_B(q_1613_l itemIn) {
            return itemIn instanceof J_4485_t || T_2915_h.n_1700_B(itemIn) instanceof J_4485_t || s_956_w.n_1700_B(itemIn);
        }
    };
    private static final /* synthetic */ j_123_i[] Q_4569_t;

    public static j_123_i[] values() {
        return (j_123_i[])Q_4569_t.clone();
    }

    public static j_123_i valueOf(String name) {
        return Enum.valueOf(j_123_i.class, name);
    }

    public abstract boolean n_1700_B(q_1613_l var1);

    private static /* synthetic */ j_123_i[] n_1700_B() {
        return new j_123_i[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R};
    }

    static {
        Q_4569_t = j_123_i.n_1700_B();
    }
}


