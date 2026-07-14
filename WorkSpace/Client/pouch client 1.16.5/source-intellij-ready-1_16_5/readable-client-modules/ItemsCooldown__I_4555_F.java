/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import lightning.product.I_686_h;
import lightning.product.N_4463_r;
import lightning.product.O_1795_e;
import lightning.product.O_2761_o;
import lightning.product.V_4557_X;
import lightning.product.W_3729_Q;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_2625_m;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_3115_L;
import lightning.product.q_4592_V;
import lightning.product.y_2603_k;
import lombok.Generated;

public class I_4555_F
extends X_3546_T {
    private static final N_4463_r v_4262_N = new N_4463_r("\u041f\u0440\u0438\u043c\u0435\u043d\u044f\u0442\u044c \u043d\u0430", new p_1977_n("\u0417\u043e\u043b\u043e\u0442\u043e\u0435 \u044f\u0431\u043b\u043e\u043a\u043e", true), new p_1977_n("\u0417\u043e\u043b\u043e\u0442\u043e\u0435 \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0435 \u044f\u0431\u043b\u043e\u043a\u043e", true), new p_1977_n("\u042d\u043d\u0434\u0435\u0440 \u0436\u0435\u043c\u0447\u0443\u0433", true), new p_1977_n("\u0425\u043e\u0440\u0443\u0441", true));
    private static final I_686_h w_1484_f = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0437\u043e\u043b\u043e\u0442\u043e\u0433\u043e \u044f\u0431\u043b\u043e\u043a\u0430", 4.5f, 0.5f, 15.0f, 0.05f, () -> v_4262_N.n_1700_B(0).t_148_a());
    private static final I_686_h t_148_a = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0437\u043e\u043b\u043e\u0442\u043e\u0433\u043e \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u043d\u043e\u0433\u043e \u044f\u0431\u043b\u043e\u043a\u0430", 4.5f, 0.5f, 15.0f, 0.05f, () -> v_4262_N.n_1700_B(3).t_148_a());
    private static final I_686_h s_956_w = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u044d\u043d\u0434\u0435\u0440 \u0436\u0435\u043c\u0447\u0443\u0433\u0430", 14.05f, 0.5f, 15.0f, 0.05f, () -> v_4262_N.n_1700_B(1).t_148_a());
    private static final I_686_h u_2550_I = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0445\u043e\u0440\u0443\u0441\u0430", 2.3f, 0.5f, 15.0f, 0.05f, () -> v_4262_N.n_1700_B(2).t_148_a());
    private static final p_1977_n M_588_G = new p_1977_n("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043f\u0432\u043f", true);
    private final Map<q_1613_l, V_4557_X> P_4830_p = new HashMap<q_1613_l, V_4557_X>();

    public I_4555_F() {
        super("ItemsCooldown", y_2603_k.G_564_y);
        this.n_1700_B(v_4262_N, w_1484_f, s_956_w, u_2550_I, t_148_a, M_588_G);
    }

    @Y_1740_V
    public void n_1700_B(W_3729_Q e) {
        if (M_588_G.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        q_1613_l item = e.J_1907_R().J_1907_R();
        if (lightning.product.I_4555_F$n_1700_B.n_1700_B(item) != null) {
            this.P_4830_p.put(item, new V_4557_X());
        }
    }

    @Y_1740_V
    public void n_1700_B(O_2761_o e) {
        if (M_588_G.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        q_1613_l item = e.J_1907_R().J_1907_R();
        if (lightning.product.I_4555_F$n_1700_B.n_1700_B(item) != null) {
            this.P_4830_p.put(item, new V_4557_X());
        }
    }

    @Y_1740_V
    public void n_1700_B(O_1795_e e) {
        if (M_588_G.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        q_1613_l item = e.J_1907_R();
        V_4557_X timer = this.P_4830_p.get(item);
        if (timer == null) {
            return;
        }
        float cooldownMs = lightning.product.I_4555_F$n_1700_B.n_1700_B(item).J_1907_R() * 1000.0f;
        long elapsed = timer.J_1907_R();
        if ((float)elapsed >= cooldownMs) {
            this.P_4830_p.remove(item);
            return;
        }
        e.n_1700_B((float)elapsed / cooldownMs);
    }

    @Y_1740_V
    public void n_1700_B(b_2625_m e) {
        if (M_588_G.t_148_a().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        q_1613_l item = e.J_1907_R().J_1907_R();
        n_1700_B type = lightning.product.I_4555_F$n_1700_B.n_1700_B(item);
        if (type != null && this.P_4830_p.containsKey(type.n_1700_B()) && I_4555_F.c_3005_b.Y_259_p.p_1458_L().n_1700_B(type.n_1700_B())) {
            e.n_1700_B(true);
        }
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(q_4592_V.p_863_D, ((Float)w_1484_f.J_1907_R()).floatValue());
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(q_4592_V.g_2810_l, ((Float)u_2550_I.J_1907_R()).floatValue());
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(q_4592_V.v_2746_S, ((Float)s_956_w.J_1907_R()).floatValue());
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(q_4592_V.E_4612_l, ((Float)t_148_a.J_1907_R()).floatValue());
        private final q_1613_l P_1922_E;
        private final float u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        public static n_1700_B n_1700_B(q_1613_l item) {
            for (n_1700_B type : lightning.product.I_4555_F$n_1700_B.values()) {
                if (type.P_1922_E != item) continue;
                return type;
            }
            return null;
        }

        @Generated
        public q_1613_l n_1700_B() {
            return this.P_1922_E;
        }

        @Generated
        public float J_1907_R() {
            return this.u_1723_Y;
        }

        @Generated
        private n_1700_B(q_1613_l item, float cooldownSeconds) {
            this.P_1922_E = item;
            this.u_1723_Y = cooldownSeconds;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.I_4555_F$n_1700_B.R_4764_Y();
        }
    }
}

