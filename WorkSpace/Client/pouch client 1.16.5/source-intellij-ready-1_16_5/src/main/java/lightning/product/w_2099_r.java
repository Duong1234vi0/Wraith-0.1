/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.E_3343_g;
import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.f_691_R;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.l_4627_h;
import lightning.product.n_421_x;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_2989_N;
import lightning.product.ModuleCategory;

public class w_2099_r
extends Module {
    private static final String v_4262_N = "Custom Totem Effect";
    private static final String w_1484_f = "Change Hitbox Opacity and Colors";
    private static final String t_148_a = "Ender Pearl trail effect";
    private static final String s_956_w = "Player Death Effect";
    private final MultiBooleanSetting u_2550_I = new MultiBooleanSetting("Effects", new BooleanSetting("Custom Totem Effect", true), new BooleanSetting("Change Hitbox Opacity and Colors", true), new BooleanSetting("Ender Pearl trail effect", true), new BooleanSetting("Player Death Effect", true));
    private final NumberSetting M_588_G = new NumberSetting("Totem Opacity", 1.0f, 0.0f, 1.0f, 0.05f, () -> this.R_4764_Y(v_4262_N));
    private final NumberSetting P_4830_p = new NumberSetting("Pearl Trail Opacity", 0.5f, 0.0f, 1.0f, 0.05f, () -> this.R_4764_Y(t_148_a));
    private final NumberSetting h_1847_R = new NumberSetting("Death Effect Opacity", 1.0f, 0.0f, 1.0f, 0.05f, () -> this.R_4764_Y(s_956_w));
    private final NumberSetting Q_4569_t = new NumberSetting("Player Hitbox Opacity", 0.8f, 0.0f, 1.0f, 0.05f, () -> this.R_4764_Y(w_1484_f));
    private final NumberSetting M_182_A = new NumberSetting("Mob Hitbox Opacity", 0.3f, 0.0f, 1.0f, 0.05f, () -> this.R_4764_Y(w_1484_f));
    private final NumberSetting t_1786_h = new NumberSetting("Hitbox Fade Distance", 15.0f, 1.0f, 64.0f, 1.0f, () -> this.R_4764_Y(w_1484_f));
    private final NumberSetting multiplayerClientSuggestionProvider = new NumberSetting("Projectile Fade Distance", 5.0f, 0.0f, 32.0f, 1.0f, () -> this.R_4764_Y(w_1484_f));
    private final h_2367_h w_1457_N = new h_2367_h("Player Hitbox Color", true, -1, () -> this.R_4764_Y(w_1484_f));
    private final h_2367_h Y_601_j = new h_2367_h("Mob Hitbox Color", true, -1, () -> this.R_4764_Y(w_1484_f));
    private final h_2367_h Y_259_p = new h_2367_h("Own Pearl Color", true, -65281, () -> this.R_4764_Y(w_1484_f));
    private final h_2367_h Q_2552_b = new h_2367_h("Friend Pearl Color", true, 0xAAFFFF, () -> this.R_4764_Y(w_1484_f));
    private final h_2367_h C_2741_M = new h_2367_h("Enemy Pearl Color", true, -12171521, () -> this.R_4764_Y(w_1484_f));
    private final Set<UUID> k_2293_S = new HashSet<UUID>();

    public w_2099_r() {
        super("MasEffects", "Port of visual PvP effects from MasEffects", ModuleCategory.R_4764_Y);
        this.n_1700_B(this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.multiplayerClientSuggestionProvider, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M);
    }

    private boolean R_4764_Y(String option) {
        Boolean enabled = this.u_2550_I.J_1907_R(option);
        return enabled != null && enabled != false;
    }

    @Nullable
    private static w_2099_r Y_259_p() {
        w_2099_r masEffects;
        ClientBootstrap pouch = ClientBootstrap.Y_601_j();
        if (pouch == null || pouch.J_1907_R() == null) {
            return null;
        }
        Module module = pouch.J_1907_R().n_1700_B(w_2099_r.class);
        if (!(module instanceof w_2099_r) || !(masEffects = (w_2099_r)module).w_1484_f()) {
            return null;
        }
        return masEffects;
    }

    public static boolean h_1847_R() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(v_4262_N);
    }

    public static boolean Q_4569_t() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(v_4262_N);
    }

    public static boolean M_182_A() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(t_148_a);
    }

    public static boolean t_1786_h() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(s_956_w);
    }

    public static float multiplayerClientSuggestionProvider() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(v_4262_N) ? ((Float)module.M_588_G.J_1907_R()).floatValue() : 0.0f;
    }

    public static float w_1457_N() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(t_148_a) ? ((Float)module.P_4830_p.J_1907_R()).floatValue() : 0.0f;
    }

    public static float Y_601_j() {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null && module.R_4764_Y(s_956_w) ? ((Float)module.h_1847_R.J_1907_R()).floatValue() : 0.0f;
    }

    @Nullable
    public static n_1700_B n_1700_B(N_4263_v entity) {
        w_2099_r module = w_2099_r.Y_259_p();
        return module != null ? module.J_1907_R(entity) : null;
    }

    @Nullable
    private n_1700_B J_1907_R(N_4263_v entity) {
        if (entity == null || w_2099_r.c_3005_b.Y_259_p == null || !this.R_4764_Y(w_1484_f)) {
            return null;
        }
        if (entity instanceof w_2989_N) {
            w_2989_N pearl = (w_2989_N)entity;
            float distance = w_2099_r.c_3005_b.Y_259_p.R_4764_Y(entity);
            float fade = u_530_F.n_1700_B((distance - ((Float)this.multiplayerClientSuggestionProvider.J_1907_R()).floatValue()) / 20.0f, 0.0f, 1.0f);
            if (fade <= 0.0f) {
                return null;
            }
            return this.n_1700_B(this.n_1700_B(pearl), fade);
        }
        if (entity instanceof a_3913_L) {
            float distance = w_2099_r.c_3005_b.Y_259_p.R_4764_Y(entity);
            float fade = 1.0f - u_530_F.n_1700_B((distance - ((Float)this.t_1786_h.J_1907_R()).floatValue()) / 20.0f, 0.0f, 1.0f);
            float opacity = fade * ((Float)this.Q_4569_t.J_1907_R()).floatValue();
            return opacity > 0.0f ? this.n_1700_B((Integer)this.w_1457_N.J_1907_R(), opacity) : null;
        }
        if (entity instanceof r_4811_B) {
            float distance = w_2099_r.c_3005_b.Y_259_p.R_4764_Y(entity);
            float fade = 1.0f - u_530_F.n_1700_B((distance - ((Float)this.t_1786_h.J_1907_R()).floatValue()) / 20.0f, 0.0f, 1.0f);
            float opacity = fade * ((Float)this.M_182_A.J_1907_R()).floatValue();
            return opacity > 0.0f ? this.n_1700_B((Integer)this.Y_601_j.J_1907_R(), opacity) : null;
        }
        return null;
    }

    private int n_1700_B(w_2989_N pearl) {
        N_4263_v owner = pearl.Y_601_j();
        if (!(owner instanceof a_3913_L)) {
            return (Integer)this.C_2741_M.J_1907_R();
        }
        a_3913_L playerOwner = (a_3913_L)owner;
        if (w_2099_r.c_3005_b.Y_259_p != null && playerOwner.w_2705_t().equals(w_2099_r.c_3005_b.Y_259_p.w_2705_t())) {
            return (Integer)this.Y_259_p.J_1907_R();
        }
        if (ClientBootstrap.Y_601_j() != null && ClientBootstrap.Y_601_j().v_4262_N() != null && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(playerOwner.y_4642_Y().getName())) {
            return (Integer)this.Q_2552_b.J_1907_R();
        }
        return (Integer)this.C_2741_M.J_1907_R();
    }

    private n_1700_B n_1700_B(int color, float opacity) {
        float alpha = (float)(color & 0xFF) / 255.0f;
        float finalAlpha = u_530_F.n_1700_B(alpha * opacity, 0.0f, 1.0f);
        return new n_1700_B((float)(color >> 24 & 0xFF) / 255.0f, (float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, finalAlpha);
    }

    @Y_1740_V
    public void n_1700_B(f_691_R event) {
        if (!this.R_4764_Y(v_4262_N) || w_2099_r.c_3005_b.Y_601_j == null || event.J_1907_R() == null) {
            return;
        }
        this.R_4764_Y(event.J_1907_R());
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (w_2099_r.c_3005_b.Y_601_j == null || w_2099_r.c_3005_b.Y_259_p == null) {
            this.k_2293_S();
            return;
        }
        if (this.R_4764_Y(t_148_a)) {
            this.Q_2552_b();
        }
        if (this.R_4764_Y(s_956_w)) {
            this.C_2741_M();
        } else {
            this.k_2293_S.clear();
        }
    }

    @Y_1740_V
    public void n_1700_B(l_4627_h event) {
        this.k_2293_S();
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g event) {
        if (event.J_1907_R() == E_3343_g.n_1700_B.J_1907_R || event.J_1907_R() == E_3343_g.n_1700_B.n_1700_B) {
            this.k_2293_S();
        }
    }

    @Override
    public void n_1700_B() {
        this.k_2293_S();
        super.n_1700_B();
    }

    @Override
    public void J_1907_R() {
        this.k_2293_S();
        super.J_1907_R();
    }

    private void R_4764_Y(N_4263_v entity) {
        int i;
        if (!n_421_x.J_1907_R()) {
            return;
        }
        e_2866_D center = this.G_564_y(entity);
        for (i = 0; i < 18; ++i) {
            w_2099_r.c_3005_b.Y_601_j.n_1700_B(n_421_x.J_1907_R, true, center.J_1907_R, center.R_4764_Y, center.G_564_y, 2.5, (double)entity.j_276_v(), 0.0);
        }
        for (i = 0; i < 100; ++i) {
            w_2099_r.c_3005_b.Y_601_j.n_1700_B(n_421_x.R_4764_Y, true, center.J_1907_R, center.R_4764_Y, center.G_564_y, (double)entity.j_276_v(), 0.0, 0.0);
        }
    }

    private void Q_2552_b() {
        for (N_4263_v entity : w_2099_r.c_3005_b.Y_601_j.J_1907_R()) {
            if (!(entity instanceof w_2989_N)) continue;
            w_2989_N pearl = (w_2989_N)entity;
            e_2866_D motion = pearl.I_4348_c();
            e_2866_D normalized = motion.v_4262_N() > 1.0E-6 ? motion.G_564_y().n_1700_B(0.05) : e_2866_D.n_1700_B;
            double x = pearl.O_3598_v();
            double y = pearl.X_2960_b() + (double)pearl.v_165_F() * 0.5;
            double z = pearl.l_2647_k();
            for (int i = 0; i < 3; ++i) {
                w_2099_r.c_3005_b.Y_601_j.n_1700_B(n_421_x.u_1723_Y, true, x, y, z, normalized.J_1907_R, normalized.R_4764_Y, normalized.G_564_y);
            }
        }
    }

    private void C_2741_M() {
        HashSet<UUID> currentlyDead = new HashSet<UUID>();
        for (a_3913_L a_3913_L2 : w_2099_r.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
            boolean dead;
            boolean bl = dead = a_3913_L2.g_46_E() <= 0.0f || a_3913_L2.O_2151_c > 0 || !a_3913_L2.RealmsLongRunningMcoTaskScreen();
            if (!dead) continue;
            currentlyDead.add(a_3913_L2.w_2705_t());
            if (!this.k_2293_S.add(a_3913_L2.w_2705_t())) continue;
            this.n_1700_B(a_3913_L2);
        }
        this.k_2293_S.retainAll(currentlyDead);
    }

    private void n_1700_B(a_3913_L player) {
        e_2866_D base = new e_2866_D(player.O_3598_v(), player.X_2960_b() + (double)player.v_165_F() * 0.6, player.l_2647_k());
        w_2099_r.c_3005_b.Y_601_j.n_1700_B(n_421_x.P_1922_E, true, base.J_1907_R, base.R_4764_Y, base.G_564_y, 0.0, 0.0, 0.0);
        for (int i = 0; i < 30; ++i) {
            w_2099_r.c_3005_b.Y_601_j.n_1700_B(n_421_x.G_564_y, true, base.J_1907_R, base.R_4764_Y, base.G_564_y, 0.0, 0.0, 0.0);
        }
    }

    private e_2866_D G_564_y(N_4263_v entity) {
        return new e_2866_D(entity.O_3598_v(), entity.X_2960_b() + (double)entity.v_165_F() * 0.5, entity.l_2647_k());
    }

    private void k_2293_S() {
        this.k_2293_S.clear();
    }

    public static class n_1700_B {
        public final float n_1700_B;
        public final float J_1907_R;
        public final float R_4764_Y;
        public final float G_564_y;

        public n_1700_B(float red, float green, float blue, float alpha) {
            this.n_1700_B = red;
            this.J_1907_R = green;
            this.R_4764_Y = blue;
            this.G_564_y = alpha;
        }
    }
}



