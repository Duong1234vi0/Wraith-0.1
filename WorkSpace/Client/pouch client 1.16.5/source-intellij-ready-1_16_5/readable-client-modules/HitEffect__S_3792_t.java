/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 */
package lightning.product;

import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.H_2506_c;
import lightning.product.I_3710_B;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.U_679_Y;
import lightning.product.V_4286_F;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.h_2739_B;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.p_1977_n;
import lightning.product.q_3148_R;
import lightning.product.q_366_O;
import lightning.product.r_4811_B;
import lightning.product.s_4405_m;
import lightning.product.u_530_F;
import lightning.product.v_2826_q;
import lightning.product.w_4351_J;
import lightning.product.y_2603_k;
import org.joml.Vector2f;

public class S_3792_t
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("Mode", "Marker", "Marker", "Pulse", "Penta", "Text");
    private final q_366_O w_1484_f = new q_366_O("Color", "Client", () -> !this.v_4262_N.J_1907_R("Text"), "Client", "Static", "By HP");
    private final h_2367_h t_148_a = new h_2367_h("Effect Color", true, -1, () -> this.w_1484_f.J_1907_R("Static") && !this.v_4262_N.J_1907_R("Text"));
    private final h_2367_h s_956_w = new h_2367_h("Crit Color", true, H_2506_c.n_1700_B(255, 215, 0), () -> this.w_1484_f.J_1907_R("Static") && !this.v_4262_N.J_1907_R("Text"));
    private final I_686_h u_2550_I = new I_686_h("Size", 0.45f, 0.15f, 1.2f, 0.05f);
    private final I_686_h M_588_G = new I_686_h("Duration, ms", 600.0f, 200.0f, 1800.0f, 50.0f);
    private final p_1977_n P_4830_p = new p_1977_n("Crit Effect", true);
    private final p_1977_n h_1847_R = new p_1977_n("Sound", true);
    private final I_686_h Q_4569_t = new I_686_h("Volume", 0.4f, 0.1f, 1.0f, 0.05f, this.h_1847_R::t_148_a);
    private final p_1977_n M_182_A = new p_1977_n("Floating Damage", true, () -> !this.v_4262_N.J_1907_R("Text"));
    private final CopyOnWriteArrayList<n_1700_B> t_1786_h = new CopyOnWriteArrayList();

    public S_3792_t() {
        super("HitEffect", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A);
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.t_1786_h.clear();
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B event) {
        if (S_3792_t.c_3005_b.Y_601_j == null || S_3792_t.c_3005_b.Y_259_p == null) {
            return;
        }
        N_4263_v target = event.J_1907_R();
        if (!(target instanceof r_4811_B)) {
            return;
        }
        r_4811_B living = (r_4811_B)target;
        if (target == S_3792_t.c_3005_b.Y_259_p) {
            return;
        }
        float healthBefore = living.g_46_E();
        boolean crit = S_3792_t.c_3005_b.Y_259_p.U_1241_n > 0.0f && !S_3792_t.c_3005_b.Y_259_p.M_1641_O() && !S_3792_t.c_3005_b.Y_259_p.e_() && !S_3792_t.c_3005_b.Y_259_p.a_2180_A() && !S_3792_t.c_3005_b.Y_259_p.J_1907_R(J_588_u.Q_4569_t) && !S_3792_t.c_3005_b.Y_259_p.y_2772_m();
        e_2866_D pos = this.n_1700_B(target);
        this.t_1786_h.add(new n_1700_B(pos, System.currentTimeMillis(), ((Float)this.M_588_G.J_1907_R()).intValue(), crit && this.P_4830_p.t_148_a() != false, healthBefore, target.j_276_v()));
        if (this.h_1847_R.t_148_a().booleanValue()) {
            S_3792_t.c_3005_b.Y_259_p.n_1700_B(crit ? V_4286_F.h_3066_J : V_4286_F.m_4644_u, ((Float)this.Q_4569_t.J_1907_R()).floatValue(), 1.1f + (float)Math.random() * 0.2f);
        }
    }

    private e_2866_D n_1700_B(N_4263_v target) {
        w_4351_J entityHit;
        I_3710_B mouseOver = S_3792_t.c_3005_b.Z_875_P;
        if (mouseOver instanceof w_4351_J && (entityHit = (w_4351_J)mouseOver).n_1700_B() == target && entityHit.P_1922_E() != null) {
            return entityHit.P_1922_E();
        }
        e_2866_D eye = S_3792_t.c_3005_b.Y_259_p.u_2550_I(1.0f);
        I_4817_s bb = target.i_601_W().grow(0.08);
        double x = u_530_F.n_1700_B(eye.J_1907_R, bb.minX, bb.maxX);
        double y = u_530_F.n_1700_B(eye.R_4764_Y, bb.minY, bb.maxY);
        double z = u_530_F.n_1700_B(eye.G_564_y, bb.minZ, bb.maxZ);
        return new e_2866_D(x, y, z);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (this.t_1786_h.isEmpty() || S_3792_t.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.J_1907_R("Marker")) {
            this.n_1700_B(System.currentTimeMillis());
            return;
        }
    }

    private void n_1700_B(long now) {
        this.J_1907_R(now);
        if (this.t_1786_h.isEmpty()) {
            return;
        }
        e_2866_D cam = S_3792_t.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        float yaw = S_3792_t.c_3005_b.O_508_d().J_1907_R.P_1922_E();
        float pitch = S_3792_t.c_3005_b.O_508_d().J_1907_R.G_564_y();
        boolean useShader = s_4405_m.q_2307_F.n_1700_B();
        c_4037_x.Y_601_j();
        c_4037_x.J_1907_R(770, 771, 1, 0);
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.q_2307_F();
        c_4037_x.u_2550_I();
        for (n_1700_B mark : this.t_1786_h) {
            float progress = mark.n_1700_B(now);
            float fadeIn = S_3792_t.n_1700_B(progress * 6.0f);
            float fadeOut = 1.0f - S_3792_t.R_4764_Y(Math.max(progress - 0.5f, 0.0f) * 2.0f);
            float alpha = fadeIn * fadeOut;
            float popScale = 0.6f + 0.4f * S_3792_t.G_564_y(Math.min(progress * 4.0f, 1.0f));
            float shrink = 1.0f - S_3792_t.R_4764_Y(Math.max(progress - 0.7f, 0.0f) * 3.33f) * 0.3f;
            float scale = ((Float)this.u_2550_I.J_1907_R()).floatValue() * popScale * shrink;
            float yOffset = S_3792_t.J_1907_R(progress) * 0.4f;
            int color = this.n_1700_B(mark);
            float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
            float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
            float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
            g_221_o ms = new g_221_o();
            ms.n_1700_B();
            ms.n_1700_B(mark.n_1700_B.J_1907_R - cam.J_1907_R, mark.n_1700_B.R_4764_Y - cam.R_4764_Y + (double)yOffset, mark.n_1700_B.G_564_y - cam.G_564_y);
            ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-yaw));
            ms.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(pitch));
            ms.n_1700_B(scale, scale, scale);
            if (useShader) {
                s_4405_m.q_2307_F.J_1907_R();
                s_4405_m.q_2307_F.J_1907_R("time", progress);
                s_4405_m.q_2307_F.J_1907_R("alpha", alpha);
                s_4405_m.q_2307_F.J_1907_R("color", r, g, b, 1.0f);
                s_4405_m.q_2307_F.J_1907_R("isCrit", mark.G_564_y ? 1.0f : 0.0f);
            }
            D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
            float half = 0.65f;
            buffer.n_1700_B(7, E_688_b.k_2293_S);
            buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), -half, -half, 0.0f).tex(0.0f, 0.0f).n_1700_B(r, g, b, alpha).endVertex();
            buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), -half, half, 0.0f).tex(0.0f, 1.0f).n_1700_B(r, g, b, alpha).endVertex();
            buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), half, half, 0.0f).tex(1.0f, 1.0f).n_1700_B(r, g, b, alpha).endVertex();
            buffer.n_1700_B(ms.R_4764_Y().n_1700_B(), half, -half, 0.0f).tex(1.0f, 0.0f).n_1700_B(r, g, b, alpha).endVertex();
            buffer.u_1723_Y();
            o_2840_r.n_1700_B(buffer);
            if (useShader) {
                s_4405_m.q_2307_F.R_4764_Y();
            }
            ms.J_1907_R();
        }
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.J_1907_R(true);
        c_4037_x.N_4405_n();
        c_4037_x.Y_259_p();
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (this.t_1786_h.isEmpty() || S_3792_t.c_3005_b.Y_601_j == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.J_1907_R(now);
        if (this.v_4262_N.J_1907_R("Marker")) {
            this.n_1700_B(event, now);
        } else if (this.v_4262_N.J_1907_R("Pulse")) {
            this.J_1907_R(event, now);
        } else if (this.v_4262_N.J_1907_R("Text")) {
            this.P_1922_E(event, now);
        } else if (this.v_4262_N.J_1907_R("Penta")) {
            this.R_4764_Y(event, now);
        }
        if (this.M_182_A.t_148_a().booleanValue() && !this.v_4262_N.J_1907_R("Text")) {
            this.u_1723_Y(event, now);
        }
    }

    private void n_1700_B(b_3528_u event, long now) {
        float cx = (float)c_3005_b.a_2085_x().Q_4569_t() / 2.0f;
        float cy = (float)c_3005_b.a_2085_x().M_182_A() / 2.0f;
        g_221_o stack = event.J_1907_R();
        for (n_1700_B mark : this.t_1786_h) {
            float p = mark.n_1700_B(now);
            float alpha = S_3792_t.n_1700_B(p * 6.0f) * (1.0f - S_3792_t.R_4764_Y(Math.max(p - 0.5f, 0.0f) * 2.0f));
            float pop = S_3792_t.G_564_y(Math.min(p * 4.0f, 1.0f));
            float spread = ((Float)this.u_2550_I.J_1907_R()).floatValue() * 6.0f * (0.8f + 0.2f * pop) * (1.0f + p * 0.3f);
            int color = H_2506_c.n_1700_B(this.n_1700_B(mark), alpha);
            int shadow = H_2506_c.n_1700_B(H_2506_c.n_1700_B(0, 0, 0), alpha * 0.5f);
            float lineLen = 4.5f * ((Float)this.u_2550_I.J_1907_R()).floatValue() * (0.9f + 0.1f * pop);
            float lineThick = u_530_F.n_1700_B(1.2f * ((Float)this.u_2550_I.J_1907_R()).floatValue(), 0.8f, 2.5f);
            this.n_1700_B(stack, cx - spread, cy - spread, cx - spread - lineLen, cy - spread - lineLen, lineThick, color, shadow);
            this.n_1700_B(stack, cx + spread, cy - spread, cx + spread + lineLen, cy - spread - lineLen, lineThick, color, shadow);
            this.n_1700_B(stack, cx - spread, cy + spread, cx - spread - lineLen, cy + spread + lineLen, lineThick, color, shadow);
            this.n_1700_B(stack, cx + spread, cy + spread, cx + spread + lineLen, cy + spread + lineLen, lineThick, color, shadow);
        }
    }

    private void J_1907_R(b_3528_u event, long now) {
        if (!s_4405_m.k_2293_S.n_1700_B()) {
            this.G_564_y(event, now);
            return;
        }
        U_679_Y mw = c_3005_b.a_2085_x();
        g_221_o stack = event.J_1907_R();
        int theme = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        for (n_1700_B mark : this.t_1786_h) {
            float p = mark.n_1700_B(now);
            float alpha = (1.0f - p) * 0.95f;
            Vector2f screen = v_2826_q.n_1700_B(new e_2866_D(mark.n_1700_B.J_1907_R, mark.n_1700_B.R_4764_Y + 0.45 + (double)p * 0.25, mark.n_1700_B.G_564_y));
            if (screen.x == Float.MAX_VALUE) continue;
            float radius = 8.0f + ((Float)this.u_2550_I.J_1907_R()).floatValue() * 22.0f * S_3792_t.J_1907_R(p);
            float r = (float)H_2506_c.n_1700_B(theme) / 255.0f;
            float g = (float)H_2506_c.J_1907_R(theme) / 255.0f;
            float b = (float)H_2506_c.R_4764_Y(theme) / 255.0f;
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.e_4240_b();
            s_4405_m.k_2293_S.J_1907_R();
            s_4405_m.k_2293_S.n_1700_B("size", new float[]{mw.Q_4569_t(), mw.M_182_A()});
            s_4405_m.k_2293_S.n_1700_B("center", screen.x, screen.y);
            s_4405_m.k_2293_S.n_1700_B("progress", p);
            s_4405_m.k_2293_S.n_1700_B("radius", radius);
            s_4405_m.k_2293_S.n_1700_B("color", r, g, b, alpha);
            this.n_1700_B(stack, mw.Q_4569_t(), mw.M_182_A());
            s_4405_m.k_2293_S.R_4764_Y();
            c_4037_x.x_607_J();
        }
    }

    private void R_4764_Y(b_3528_u event, long now) {
        if (!s_4405_m.t_4043_B.n_1700_B()) {
            this.G_564_y(event, now);
            return;
        }
        g_221_o stack = event.J_1907_R();
        int theme = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        for (n_1700_B mark : this.t_1786_h) {
            float p = mark.n_1700_B(now);
            float alpha = (1.0f - p) * 0.92f;
            Vector2f screen = v_2826_q.n_1700_B(new e_2866_D(mark.n_1700_B.J_1907_R, mark.n_1700_B.R_4764_Y + 0.45 + (double)p * 0.25, mark.n_1700_B.G_564_y));
            if (screen.x == Float.MAX_VALUE) continue;
            float half = 22.0f + ((Float)this.u_2550_I.J_1907_R()).floatValue() * 34.0f * S_3792_t.J_1907_R(p);
            float cr = (float)H_2506_c.n_1700_B(theme) / 255.0f;
            float cg = (float)H_2506_c.J_1907_R(theme) / 255.0f;
            float cb = (float)H_2506_c.R_4764_Y(theme) / 255.0f;
            float critMix = mark.G_564_y ? 1.0f : 0.0f;
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.e_4240_b();
            s_4405_m.t_4043_B.J_1907_R();
            s_4405_m.t_4043_B.n_1700_B("u_Color", cr, cg, cb, 1.0f);
            s_4405_m.t_4043_B.n_1700_B("u_Alpha", alpha);
            s_4405_m.t_4043_B.n_1700_B("u_Time", p * 2.7f + (float)(mark.J_1907_R % 1000L) * 0.001f);
            s_4405_m.t_4043_B.n_1700_B("u_Crit", critMix);
            stack.n_1700_B();
            stack.n_1700_B((double)screen.x, (double)screen.y, 0.0);
            stack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f * p));
            this.n_1700_B(stack, -half, -half, half * 2.0f, half * 2.0f);
            stack.J_1907_R();
            s_4405_m.t_4043_B.R_4764_Y();
            c_4037_x.x_607_J();
        }
    }

    private void G_564_y(b_3528_u event, long now) {
        g_221_o stack = event.J_1907_R();
        for (n_1700_B mark : this.t_1786_h) {
            float p = mark.n_1700_B(now);
            float alpha = (1.0f - p) * 0.95f;
            Vector2f screen = v_2826_q.n_1700_B(new e_2866_D(mark.n_1700_B.J_1907_R, mark.n_1700_B.R_4764_Y + 0.45 + (double)p * 0.25, mark.n_1700_B.G_564_y));
            if (screen.x == Float.MAX_VALUE) continue;
            float radius = 5.0f + ((Float)this.u_2550_I.J_1907_R()).floatValue() * 20.0f * S_3792_t.J_1907_R(p);
            float thickness = Math.max(1.2f, 2.2f * (1.0f - p));
            int color = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.J_1907_R), alpha);
            this.n_1700_B(stack, screen.x, screen.y, radius, thickness, color);
        }
    }

    private void P_1922_E(b_3528_u event, long now) {
        g_221_o stack = event.J_1907_R();
        int green = H_2506_c.n_1700_B(60, 255, 60);
        int red = H_2506_c.n_1700_B(255, 60, 60);
        int neutral = H_2506_c.n_1700_B(200, 200, 200);
        for (n_1700_B mark : this.t_1786_h) {
            String text;
            int rgb;
            float p = mark.n_1700_B(now);
            float alpha = S_3792_t.n_1700_B(p * 8.0f) * (1.0f - S_3792_t.R_4764_Y(Math.max(p - 0.6f, 0.0f) * 2.5f));
            Vector2f screen = v_2826_q.n_1700_B(new e_2866_D(mark.n_1700_B.J_1907_R, mark.n_1700_B.R_4764_Y + 0.3 + (double)S_3792_t.J_1907_R(p) * 0.7, mark.n_1700_B.G_564_y));
            if (screen.x == Float.MAX_VALUE) continue;
            N_4263_v entity = S_3792_t.c_3005_b.Y_601_j.J_1907_R(mark.u_1723_Y);
            if (entity instanceof r_4811_B) {
                float before;
                r_4811_B living = (r_4811_B)entity;
                float cur = living.g_46_E();
                if (cur < (before = mark.P_1922_E) - 0.001f) {
                    rgb = red;
                    text = String.format("%.1f", Float.valueOf(before - cur));
                } else if (cur > before + 0.001f) {
                    rgb = green;
                    text = String.format("+%.1f", Float.valueOf(cur - before));
                } else {
                    rgb = neutral;
                    text = mark.w_1484_f && mark.v_4262_N > 0.0f ? String.format("%.1f", Float.valueOf(mark.v_4262_N)) : "0";
                }
            } else {
                rgb = mark.w_1484_f && mark.v_4262_N > 0.0f ? red : neutral;
                text = mark.w_1484_f && mark.v_4262_N > 0.0f ? String.format("%.1f", Float.valueOf(mark.v_4262_N)) : "?";
            }
            int textColor = H_2506_c.n_1700_B(rgb, alpha);
            int shadowCol = H_2506_c.n_1700_B(H_2506_c.n_1700_B(0, 0, 0), alpha * 0.7f);
            float textScale = (1.15f + 0.2f * (1.0f - p)) * (0.55f + ((Float)this.u_2550_I.J_1907_R()).floatValue() * 1.1f);
            float tw = l_3370_o.J_1907_R[22].n_1700_B(text);
            stack.n_1700_B();
            stack.n_1700_B((double)screen.x, (double)screen.y, 0.0);
            stack.n_1700_B(textScale, textScale, 1.0f);
            l_3370_o.J_1907_R[22].n_1700_B(stack, text, (double)(-tw / 2.0f + 0.5f), 0.5, shadowCol);
            l_3370_o.J_1907_R[22].n_1700_B(stack, text, (double)(-tw / 2.0f), 0.0, textColor);
            stack.J_1907_R();
        }
    }

    private void u_1723_Y(b_3528_u event, long now) {
        g_221_o stack = event.J_1907_R();
        for (n_1700_B mark : this.t_1786_h) {
            if (mark.v_4262_N <= 0.0f) continue;
            float p = mark.n_1700_B(now);
            float alpha = S_3792_t.n_1700_B(p * 8.0f) * (1.0f - S_3792_t.R_4764_Y(Math.max(p - 0.6f, 0.0f) * 2.5f));
            Vector2f screen = v_2826_q.n_1700_B(new e_2866_D(mark.n_1700_B.J_1907_R, mark.n_1700_B.R_4764_Y + 0.3 + (double)S_3792_t.J_1907_R(p) * 0.7, mark.n_1700_B.G_564_y));
            if (screen.x == Float.MAX_VALUE) continue;
            String text = String.format("%.1f", Float.valueOf(mark.v_4262_N));
            int textColor = H_2506_c.n_1700_B(mark.G_564_y ? ((Integer)this.s_956_w.J_1907_R()).intValue() : this.n_1700_B(mark), alpha);
            int shadowCol = H_2506_c.n_1700_B(H_2506_c.n_1700_B(0, 0, 0), alpha * 0.7f);
            float textScale = 0.75f + (mark.G_564_y ? 0.25f : 0.0f);
            float popScale = 1.0f + 0.2f * (1.0f - S_3792_t.J_1907_R(Math.min(p * 5.0f, 1.0f)));
            float tw = l_3370_o.J_1907_R[14].n_1700_B(text);
            stack.n_1700_B();
            stack.n_1700_B((double)screen.x, (double)screen.y, 0.0);
            stack.n_1700_B(textScale *= popScale, textScale, 1.0f);
            l_3370_o.J_1907_R[14].n_1700_B(stack, text, (double)(-tw / 2.0f + 0.5f), 0.5, shadowCol);
            l_3370_o.J_1907_R[14].n_1700_B(stack, text, (double)(-tw / 2.0f), 0.0, textColor);
            stack.J_1907_R();
        }
    }

    private void n_1700_B(g_221_o stack, float x1, float y1, float x2, float y2, float thickness, int color, int shadowColor) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float len = (float)Math.sqrt(dx * dx + dy * dy);
        if (len < 0.001f) {
            return;
        }
        float nx = -dy / len * thickness * 0.5f;
        float ny = dx / len * thickness * 0.5f;
        if (shadowColor != 0) {
            this.n_1700_B(stack, x1 + nx + 0.5f, y1 + ny + 0.5f, x1 - nx + 0.5f, y1 - ny + 0.5f, x2 - nx + 0.5f, y2 - ny + 0.5f, x2 + nx + 0.5f, y2 + ny + 0.5f, shadowColor);
        }
        this.n_1700_B(stack, x1 + nx, y1 + ny, x1 - nx, y1 - ny, x2 - nx, y2 - ny, x2 + nx, y2 + ny, color);
    }

    private void n_1700_B(g_221_o stack, float cx, float cy, float radius, float thickness, int color) {
        int segments = 48;
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Y_601_j);
        float inner = Math.max(0.1f, radius - thickness * 0.5f);
        float outer = radius + thickness * 0.5f;
        for (int i = 0; i < segments; ++i) {
            float a0 = (float)(Math.PI * 2 * (double)i / (double)segments);
            float a1 = (float)(Math.PI * 2 * (double)(i + 1) / (double)segments);
            float x0i = cx + (float)Math.cos(a0) * inner;
            float y0i = cy + (float)Math.sin(a0) * inner;
            float x0o = cx + (float)Math.cos(a0) * outer;
            float y0o = cy + (float)Math.sin(a0) * outer;
            float x1i = cx + (float)Math.cos(a1) * inner;
            float y1i = cy + (float)Math.sin(a1) * inner;
            float x1o = cx + (float)Math.cos(a1) * outer;
            float y1o = cy + (float)Math.sin(a1) * outer;
            buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x0o, y0o, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x0i, y0i, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x1i, y1i, 0.0f).n_1700_B(r, g, b, a).endVertex();
            buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x1o, y1o, 0.0f).n_1700_B(r, g, b, a).endVertex();
        }
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        c_4037_x.x_607_J();
    }

    private void n_1700_B(g_221_o stack, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Y_601_j);
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x1, y1, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x2, y2, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x3, y3, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x4, y4, 0.0f).n_1700_B(r, g, b, a).endVertex();
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
        c_4037_x.x_607_J();
    }

    private void n_1700_B(g_221_o stack, float width, float height) {
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Q_2552_b);
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), 0.0f, 0.0f, 0.0f).tex(0.0f, 0.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), 0.0f, height, 0.0f).tex(0.0f, 1.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), width, height, 0.0f).tex(1.0f, 1.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), width, 0.0f, 0.0f).tex(1.0f, 0.0f).endVertex();
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
    }

    private void n_1700_B(g_221_o stack, float x, float y, float w, float h) {
        D_3318_r buffer = l_3747_P.n_1700_B().R_4764_Y();
        buffer.n_1700_B(7, E_688_b.Q_2552_b);
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x, y, 0.0f).tex(0.0f, 0.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x, y + h, 0.0f).tex(0.0f, 1.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x + w, y + h, 0.0f).tex(1.0f, 1.0f).endVertex();
        buffer.n_1700_B(stack.R_4764_Y().n_1700_B(), x + w, y, 0.0f).tex(1.0f, 0.0f).endVertex();
        buffer.u_1723_Y();
        o_2840_r.n_1700_B(buffer);
    }

    private int n_1700_B(n_1700_B mark) {
        if (this.w_1484_f.J_1907_R("Static")) {
            return mark.G_564_y ? (Integer)this.s_956_w.J_1907_R() : (Integer)this.t_148_a.J_1907_R();
        }
        if (this.w_1484_f.J_1907_R("By HP")) {
            N_4263_v entity = S_3792_t.c_3005_b.Y_601_j.J_1907_R(mark.u_1723_Y);
            float hp = 1.0f;
            if (entity instanceof r_4811_B) {
                r_4811_B living = (r_4811_B)entity;
                hp = u_530_F.n_1700_B(living.g_46_E() / living.L_1733_J(), 0.0f, 1.0f);
            }
            int red = H_2506_c.n_1700_B(255, 60, 60);
            int green = H_2506_c.n_1700_B(60, 255, 60);
            int base = H_2506_c.n_1700_B(red, green, hp);
            if (mark.G_564_y) {
                return H_2506_c.n_1700_B(base, H_2506_c.n_1700_B(255, 215, 0), 0.5f);
            }
            return base;
        }
        int base = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        if (mark.G_564_y) {
            return H_2506_c.n_1700_B(base, H_2506_c.n_1700_B(255, 255, 255), 0.3f);
        }
        return base;
    }

    private void J_1907_R(long now) {
        this.t_1786_h.removeIf(m -> m.J_1907_R(now));
        this.h_1847_R();
    }

    private void h_1847_R() {
        for (n_1700_B mark : this.t_1786_h) {
            r_4811_B living;
            float currentHp;
            N_4263_v entity;
            if (mark.w_1484_f || !((entity = S_3792_t.c_3005_b.Y_601_j.J_1907_R(mark.u_1723_Y)) instanceof r_4811_B) || !((currentHp = (living = (r_4811_B)entity).g_46_E()) < mark.P_1922_E)) continue;
            mark.v_4262_N = mark.P_1922_E - currentHp;
            mark.w_1484_f = true;
        }
    }

    private static float n_1700_B(float t) {
        return u_530_F.n_1700_B(t, 0.0f, 1.0f);
    }

    private static float J_1907_R(float t) {
        return 1.0f - (1.0f - t) * (1.0f - t);
    }

    private static float R_4764_Y(float t) {
        return t * t;
    }

    private static float G_564_y(float t) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        return 1.0f + c3 * (float)Math.pow((double)t - 1.0, 3.0) + c1 * (float)Math.pow((double)t - 1.0, 2.0);
    }

    private static class n_1700_B {
        final e_2866_D n_1700_B;
        final long J_1907_R;
        final long R_4764_Y;
        final boolean G_564_y;
        final float P_1922_E;
        final int u_1723_Y;
        float v_4262_N;
        boolean w_1484_f;

        n_1700_B(e_2866_D pos, long startTime, long duration, boolean crit, float healthBefore, int entityId) {
            this.n_1700_B = pos;
            this.J_1907_R = startTime;
            this.R_4764_Y = duration;
            this.G_564_y = crit;
            this.P_1922_E = healthBefore;
            this.u_1723_Y = entityId;
        }

        float n_1700_B(long now) {
            return u_530_F.n_1700_B((float)(now - this.J_1907_R) / (float)this.R_4764_Y, 0.0f, 1.0f);
        }

        boolean J_1907_R(long now) {
            return now - this.J_1907_R > this.R_4764_Y;
        }
    }
}

