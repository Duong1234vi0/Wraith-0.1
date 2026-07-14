/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lightning.product.A_2226_Q;
import lightning.product.P_225_f;
import lightning.product.Q_2753_H;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.k_1836_E;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.t_3138_Z;
import lightning.product.y_2603_k;

public class z_2733_k
extends X_3546_T {
    private static final Pattern v_4262_N = Pattern.compile("^\\w{3,16}$");
    private final q_366_O w_1484_f = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "Ball", "Ball", "Shield", "Spikes", "Netherite", "CheatParadise", "Bow", "Classic", "Totems", "NoDebuff");
    private final q_3206_W t_148_a = new q_3206_W("\u041a\u043b\u0430\u0432\u0438\u0448\u0430 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u044f");
    private final List<String> s_956_w = Lists.newArrayList();
    private final V_4557_X u_2550_I = new V_4557_X();
    private int M_588_G = 0;

    public z_2733_k() {
        super("AutoDuel", y_2603_k.P_1922_E);
        this.n_1700_B(this.w_1484_f, this.t_148_a);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (((Integer)this.t_148_a.J_1907_R()).intValue() == e.n_1700_B() && e.J_1907_R()) {
            this.R_4764_Y();
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        List players = z_2733_k.c_3005_b.Y_259_p.n_1700_B.P_1922_E().stream().map(A_2226_Q::n_1700_B).map(GameProfile::getName).filter(profileName -> v_4262_N.matcher((CharSequence)profileName).matches()).collect(Collectors.toList());
        if (this.u_2550_I.J_1907_R(800L * (long)players.size())) {
            this.s_956_w.clear();
            this.M_588_G = 0;
            this.u_2550_I.n_1700_B();
        }
        if (!players.isEmpty()) {
            a_2900_S a_2900_S2;
            if (this.u_2550_I.J_1907_R(1000L)) {
                String player;
                if (this.M_588_G >= players.size()) {
                    this.M_588_G = 0;
                }
                if (!this.s_956_w.contains(player = (String)players.get(this.M_588_G)) && !player.equals(z_2733_k.c_3005_b.w_1484_f.P_1922_E().getName())) {
                    z_2733_k.c_3005_b.Y_259_p.n_1700_B("/duel " + player);
                    this.s_956_w.add(player);
                }
                ++this.M_588_G;
                this.u_2550_I.n_1700_B();
            }
            if ((a_2900_S2 = z_2733_k.c_3005_b.Y_259_p.H_1873_g) instanceof P_225_f) {
                P_225_f chest = (P_225_f)a_2900_S2;
                if (z_2733_k.c_3005_b.Y_1740_V.getTitle().getString().contains("\u0412\u044b\u0431\u043e\u0440 \u043d\u0430\u0431\u043e\u0440\u0430 (1/1)")) {
                    if (this.u_2550_I.J_1907_R(150L)) {
                        z_2733_k.c_3005_b.w_1457_N.windowClick(chest.u_1723_Y, lightning.product.z_2733_k$n_1700_B.valueOf((String)this.w_1484_f.J_1907_R()).ordinal(), 0, a_408_T.J_1907_R, z_2733_k.c_3005_b.Y_259_p);
                        this.u_2550_I.n_1700_B();
                    }
                } else if (z_2733_k.c_3005_b.Y_1740_V.getTitle().getString().contains("\u041d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 \u043f\u043e\u0435\u0434\u0438\u043d\u043a\u0430") && this.u_2550_I.J_1907_R(150L)) {
                    z_2733_k.c_3005_b.w_1457_N.windowClick(chest.u_1723_Y, 0, 0, a_408_T.J_1907_R, z_2733_k.c_3005_b.Y_259_p);
                    this.u_2550_I.n_1700_B();
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        k_1836_E chat;
        String text;
        t_3138_Z<?> t_3138_Z2 = e.G_564_y();
        if (t_3138_Z2 instanceof k_1836_E && ((text = (chat = (k_1836_E)t_3138_Z2).J_1907_R().getString().toLowerCase()).contains("\u043d\u0430\u0447\u0430\u043b\u043e") && text.contains("\u0447\u0435\u0440\u0435\u0437") && text.contains("\u0441\u0435\u043a\u0443\u043d\u0434!") || text.isEmpty())) {
            this.R_4764_Y();
        }
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
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] s_956_w;

        public static n_1700_B[] values() {
            return (n_1700_B[])s_956_w.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a};
        }

        static {
            s_956_w = lightning.product.z_2733_k$n_1700_B.n_1700_B();
        }
    }
}

