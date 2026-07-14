/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lightning.product.A_4115_X;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.MultiBooleanSetting;
import lightning.product.P_2295_B;
import lightning.product.P_3504_Q;
import lightning.product.R_3213_X;
import lightning.product.Interface;
import lightning.product.S_4258_d;
import lightning.product.T_2971_J;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.f_2787_O;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.h_973_D;
import lightning.product.Setting;
import lightning.product.Easing;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_3729_r;
import lightning.product.ClientBootstrap;
import lightning.product.o_82_k;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.r_2478_U;
import lightning.product.u_4724_w;
import lightning.product.u_530_F;
import lombok.Generated;

public class J_3635_s
implements MinecraftAccess {
    private String n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private final float u_1723_Y;
    private final float v_4262_N;
    private boolean w_1484_f;
    private boolean t_148_a;
    private boolean s_956_w;
    private float u_2550_I;
    private float M_588_G;
    private float P_4830_p;
    private float h_1847_R;
    private float Q_4569_t;
    private float M_182_A;
    private final MultiBooleanSetting t_1786_h;
    private final Animation multiplayerClientSuggestionProvider = new Animation(0.0f, 10.0f, Easing.u_1723_Y);
    private final Animation w_1457_N = new Animation(0.0f, 10.0f, Easing.u_1723_Y);
    private static J_3635_s Y_601_j = null;
    private final List<Setting<?>> Y_259_p = new ArrayList();
    private final List<N_4006_T> Q_2552_b = new ArrayList<N_4006_T>();
    private final o_82_k C_2741_M;
    private final boolean k_2293_S;
    private float q_2307_F;
    private float Z_875_P;
    private final List<Float> t_4043_B = new ArrayList<Float>();
    private final List<Float> x_607_J = new ArrayList<Float>();

    public J_3635_s(String name, float x, float y, MultiBooleanSetting elements, boolean draggable) {
        this.n_1700_B = name;
        this.J_1907_R = x;
        this.R_4764_Y = y;
        this.u_1723_Y = x;
        this.v_4262_N = y;
        this.t_1786_h = elements;
        this.k_2293_S = draggable;
        this.C_2741_M = ClientBootstrap.Y_601_j().M_182_A();
        this.C_2741_M.n_1700_B(this);
        A_4115_X.n_1700_B(this);
    }

    public void n_1700_B(Setting<?> ... settings) {
        for (Setting<?> s : settings) {
            this.Y_259_p.add(s);
            if (s instanceof BooleanSetting) {
                BooleanSetting bs = (BooleanSetting)s;
                this.Q_2552_b.add(new h_973_D(bs));
            }
            if (s instanceof ModeSetting) {
                ModeSetting ms = (ModeSetting)s;
                this.Q_2552_b.add(new P_2295_B(ms));
            }
            if (!(s instanceof h_2367_h)) continue;
            h_2367_h cs = (h_2367_h)s;
            this.Q_2552_b.add(new f_2787_O(cs));
        }
        this.C_2741_M.G_564_y(this);
    }

    @Y_1740_V
    public void n_1700_B(S_4258_d event) {
        if (!(J_3635_s.c_3005_b.Y_1740_V instanceof h_4412_P) && !(J_3635_s.c_3005_b.Y_1740_V instanceof u_4724_w) && (J_3635_s.c_3005_b.Y_1740_V != null || !ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class).w_1484_f()) || J_3635_s.c_3005_b.P_4830_p.r_3651_U) {
            return;
        }
        P_3504_Q mouse = l_3729_r.n_1700_B((int)event.R_4764_Y(), (int)event.G_564_y());
        float mx = mouse.n_1700_B();
        float my = mouse.J_1907_R();
        if (event.J_1907_R() == 1 && F_747_P.n_1700_B(mx, my, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E) && this.n_1700_B(mx, my)) {
            if (this.Q_2552_b.isEmpty()) {
                event.n_1700_B(true);
                return;
            }
            this.C_2741_M.R_4764_Y(this);
            this.C_2741_M.J_1907_R(this);
            if (!this.s_956_w) {
                this.Q_4569_t = mx - this.J_1907_R;
                this.M_182_A = my - this.R_4764_Y;
                this.P_4830_p = this.J_1907_R + this.Q_4569_t;
                this.h_1847_R = this.R_4764_Y + this.M_182_A;
                for (N_4006_T el : this.Q_2552_b) {
                    el.J_1907_R();
                }
                this.w_1457_N.J_1907_R(0.0f);
            }
            this.s_956_w = !this.s_956_w;
            event.n_1700_B(true);
            return;
        }
        J_3635_s open = null;
        for (J_3635_s d : this.C_2741_M.h_1847_R()) {
            if (d == null || !d.s_956_w) continue;
            open = d;
            break;
        }
        if (open != null && open != this) {
            boolean overHeader = F_747_P.n_1700_B(mx, my, open.J_1907_R, open.R_4764_Y, open.G_564_y, open.P_1922_E);
            boolean overSettings = F_747_P.n_1700_B(mx, my, open.P_4830_p, open.h_1847_R, open.H_2857_Y(), open.A_4115_X());
            if (!overHeader && !overSettings) {
                event.n_1700_B(true);
                return;
            }
            return;
        }
        if (this.s_956_w && this.n_1700_B(mx, my, event)) {
            return;
        }
        if (event.J_1907_R() == 1 && F_747_P.n_1700_B(mx, my, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E)) {
            if (this.Q_2552_b.isEmpty()) {
                event.n_1700_B(true);
                return;
            }
            this.C_2741_M.R_4764_Y(this);
            this.C_2741_M.J_1907_R(this);
            if (!this.s_956_w) {
                this.Q_4569_t = mx - this.J_1907_R;
                this.M_182_A = my - this.R_4764_Y;
                this.P_4830_p = this.J_1907_R + this.Q_4569_t;
                this.h_1847_R = this.R_4764_Y + this.M_182_A;
                for (N_4006_T el : this.Q_2552_b) {
                    el.J_1907_R();
                }
                this.w_1457_N.J_1907_R(0.0f);
            }
            this.s_956_w = !this.s_956_w;
            event.n_1700_B(true);
            return;
        }
        if (this.k_2293_S && event.J_1907_R() == 0 && Y_601_j == null && this.n_1700_B(mx, my)) {
            this.s_956_w = false;
            this.w_1484_f = true;
            Y_601_j = this;
            this.u_2550_I = mx - this.J_1907_R;
            this.M_588_G = my - this.R_4764_Y;
            this.q_2307_F = this.J_1907_R;
            this.Z_875_P = this.R_4764_Y;
            this.C_2741_M.J_1907_R(this);
            event.n_1700_B(true);
        }
        if (this.k_2293_S && event.J_1907_R() == 2 && F_747_P.n_1700_B(mx, my, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E)) {
            if (this.s_956_w && F_747_P.n_1700_B(mx, my, this.P_4830_p, this.h_1847_R, this.H_2857_Y(), this.A_4115_X())) {
                event.n_1700_B(true);
                return;
            }
            if (!u_530_F.n_1700_B(this.J_1907_R, this.u_1723_Y) || !u_530_F.n_1700_B(this.R_4764_Y, this.v_4262_N)) {
                this.J_1907_R = this.u_1723_Y;
                this.R_4764_Y = this.v_4262_N;
                this.multiplayerClientSuggestionProvider.J_1907_R(0.0f);
                this.C_2741_M.G_564_y();
            }
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(T_2971_J event) {
        if (this.s_956_w) {
            for (N_4006_T element : this.Q_2552_b) {
                f_2787_O colorEl;
                R_3213_X picker;
                if (!(element instanceof f_2787_O) || !(picker = (colorEl = (f_2787_O)element).G_564_y()).G_564_y()) continue;
                picker.n_1700_B();
                this.C_2741_M.G_564_y();
            }
        }
        if (this.k_2293_S && (J_3635_s.c_3005_b.Y_1740_V instanceof h_4412_P || J_3635_s.c_3005_b.Y_1740_V instanceof u_4724_w || J_3635_s.c_3005_b.Y_1740_V == null) && !J_3635_s.c_3005_b.P_4830_p.r_3651_U && event.J_1907_R() == 0 && this.w_1484_f) {
            this.w_1484_f = false;
            Y_601_j = null;
            this.t_4043_B.clear();
            this.x_607_J.clear();
            this.C_2741_M.G_564_y();
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(r_2478_U e) {
        boolean chatOpen;
        P_3504_Q mouse = l_3729_r.n_1700_B((int)e.J_1907_R(), (int)e.R_4764_Y());
        float mx = mouse.t_148_a;
        float my = mouse.s_956_w;
        boolean bl = chatOpen = (J_3635_s.c_3005_b.Y_1740_V instanceof h_4412_P || J_3635_s.c_3005_b.Y_1740_V instanceof u_4724_w || J_3635_s.c_3005_b.Y_1740_V == null) && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class).w_1484_f() && !J_3635_s.c_3005_b.P_4830_p.r_3651_U;
        if (!(chatOpen && this.k_2293_S && this.w_1484_f)) {
            return;
        }
        J_3635_s.c_3005_b.s_956_w.n_1700_B(2.0f);
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        float newX = u_530_F.n_1700_B(mx - this.u_2550_I, 0.0f, (float)screenWidth - this.G_564_y);
        float newY = u_530_F.n_1700_B(my - this.M_588_G, 0.0f, (float)screenHeight - this.P_1922_E);
        this.q_2307_F = this.J_1907_R;
        this.Z_875_P = this.R_4764_Y;
        this.J_1907_R = newX;
        this.R_4764_Y = newY;
        this.t_4043_B.clear();
        this.x_607_J.clear();
        if (!j_1654_T.J_1907_R(j_1654_T.p_178_J.J_1907_R()) || j_1654_T.J_1907_R(j_1654_T.RealmsClientConfig.J_1907_R())) {
            this.n_1700_B(screenWidth, screenHeight);
        }
        if (this.w_1457_N.n_1700_B() > 0.0f) {
            this.P_4830_p = this.J_1907_R + this.Q_4569_t;
            this.h_1847_R = this.R_4764_Y + this.M_182_A;
        }
        J_3635_s.c_3005_b.s_956_w.R_4764_Y();
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u.G_564_y e) {
        boolean chatOpen;
        P_3504_Q mouse = l_3729_r.n_1700_B((int)J_3635_s.c_3005_b.h_1847_R.G_564_y(), (int)J_3635_s.c_3005_b.h_1847_R.P_1922_E());
        float mx = mouse.t_148_a / 2.0f;
        float my = mouse.s_956_w / 2.0f;
        if (!this.k_2293_S && this.n_1700_B.equals("\u0423\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u044f")) {
            this.G_564_y = l_3370_o.u_1723_Y[16].n_1700_B("K") + l_3370_o.R_4764_Y[12].n_1700_B("\u042d\u0442\u043e \u0443\u0432\u0435\u0434\u043e\u043c\u043b\u0435\u043d\u0438\u0435, \u043a\u043b\u0438\u043a\u043d\u0438 \u043d\u0430 \u043c\u0435\u043d\u044f \u0434\u043b\u044f \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438") + 28.0f;
            this.J_1907_R = ((float)c_3005_b.RealmsServerPing().Q_4569_t() - this.G_564_y) / 2.0f;
            this.R_4764_Y = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f + 13.0f;
        }
        boolean canInteract = (J_3635_s.c_3005_b.Y_1740_V instanceof h_4412_P || J_3635_s.c_3005_b.Y_1740_V instanceof u_4724_w || J_3635_s.c_3005_b.Y_1740_V == null) && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class).w_1484_f() && !J_3635_s.c_3005_b.P_4830_p.r_3651_U;
        boolean bl = chatOpen = J_3635_s.c_3005_b.Y_1740_V instanceof h_4412_P && !J_3635_s.c_3005_b.P_4830_p.r_3651_U;
        if (!chatOpen && this.s_956_w) {
            this.s_956_w = false;
            for (N_4006_T el : this.Q_2552_b) {
                f_2787_O colorEl;
                R_3213_X picker;
                if (!(el instanceof f_2787_O) || !(picker = (colorEl = (f_2787_O)el).G_564_y()).G_564_y()) continue;
                picker.R_4764_Y();
            }
        }
        if (!canInteract) {
            if (this.s_956_w || this.w_1484_f) {
                this.s_956_w = false;
                if (this.w_1484_f) {
                    this.w_1484_f = false;
                    Y_601_j = null;
                    this.t_4043_B.clear();
                    this.x_607_J.clear();
                    this.C_2741_M.G_564_y();
                }
            }
            this.multiplayerClientSuggestionProvider.J_1907_R(0.0f);
            this.w_1457_N.J_1907_R(0.0f);
            return;
        }
        this.t_148_a = this.n_1700_B(mx, my) && this.C_2741_M.h_1847_R().stream().noneMatch(d -> d != null && d.s_956_w) && (Y_601_j == null || Y_601_j == this);
        this.multiplayerClientSuggestionProvider.n_1700_B(chatOpen && (this.t_148_a || this.k_2293_S && this.w_1484_f && Y_601_j == this) ? 1.0f : 0.0f);
        if (chatOpen && this.multiplayerClientSuggestionProvider.n_1700_B() > 0.0f && (this.t_1786_h == null || this.t_1786_h.J_1907_R(this.n_1700_B).booleanValue()) && !this.n_1700_B.equals("\u041b\u043e\u0433\u043e\u0442\u0438\u043f")) {
            this.R_4764_Y(e);
        }
        this.w_1457_N.n_1700_B(this.s_956_w ? 1.0f : 0.0f);
        if (this.w_1457_N.n_1700_B() > 0.0f && !this.Q_2552_b.isEmpty()) {
            this.n_1700_B(e, mx, my);
        }
        if (this.w_1484_f && this.k_2293_S && !j_1654_T.J_1907_R(j_1654_T.p_178_J.J_1907_R()) || j_1654_T.J_1907_R(j_1654_T.RealmsClientConfig.J_1907_R())) {
            this.J_1907_R(e);
        }
    }

    private boolean n_1700_B(float mx, float my) {
        List<J_3635_s> renderOrder = this.C_2741_M.h_1847_R();
        for (int i = renderOrder.size() - 1; i >= 0; --i) {
            J_3635_s d = renderOrder.get(i);
            if (d == null || d.t_1786_h != null && !d.t_1786_h.J_1907_R(d.n_1700_B).booleanValue() || !F_747_P.n_1700_B(mx, my, d.J_1907_R, d.R_4764_Y, d.G_564_y, d.P_1922_E)) continue;
            return d == this;
        }
        return false;
    }

    private void J_1907_R(b_3528_u.G_564_y e) {
        float thickness = 0.5f;
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        int color = H_2506_c.n_1700_B(255, 255, 255, 100);
        for (float lineX : this.t_4043_B) {
            F_489_x.n_1700_B(e.J_1907_R(), lineX - thickness, 0.0f, thickness, (float)screenHeight, color);
        }
        for (float lineY : this.x_607_J) {
            F_489_x.n_1700_B(e.J_1907_R(), 0.0f, lineY - thickness, (float)screenWidth, thickness, color);
        }
    }

    private void n_1700_B(int screenWidth, int screenHeight) {
        float snapDistance = 2.0f;
        n_1700_B snapX = this.n_1700_B(this.J_1907_R, this.G_564_y, screenWidth, true);
        n_1700_B snapY = this.n_1700_B(this.R_4764_Y, this.P_1922_E, screenHeight, false);
        if (snapX.J_1907_R <= snapDistance) {
            this.J_1907_R = u_530_F.n_1700_B(snapX.n_1700_B, 0.0f, (float)screenWidth - this.G_564_y);
            this.t_4043_B.add(Float.valueOf(snapX.R_4764_Y));
        }
        if (snapY.J_1907_R <= snapDistance) {
            this.R_4764_Y = u_530_F.n_1700_B(snapY.n_1700_B, 0.0f, (float)screenHeight - this.P_1922_E);
            this.x_607_J.add(Float.valueOf(snapY.R_4764_Y));
        }
    }

    private n_1700_B n_1700_B(float pos, float size, int screenSize, boolean isX) {
        float minDist = Float.MAX_VALUE;
        float closestPos = pos;
        float closestLine = 0.0f;
        List<Float> lines = isX ? this.n_1700_B(screenSize) : this.J_1907_R(screenSize);
        for (float line : lines) {
            float dist1 = Math.abs(pos - line);
            float dist2 = Math.abs(pos + size / 2.0f - line);
            float dist3 = Math.abs(pos + size - line);
            float minLineDist = Math.min(Math.min(dist1, dist2), dist3);
            if (!(minLineDist < minDist)) continue;
            minDist = minLineDist;
            closestLine = line;
            if (dist1 == minLineDist) {
                closestPos = line;
                continue;
            }
            if (dist2 == minLineDist) {
                closestPos = line - size / 2.0f;
                continue;
            }
            closestPos = line - size;
        }
        return new n_1700_B((int)closestPos, minDist, closestLine);
    }

    private List<Float> n_1700_B(int screenWidth) {
        ArrayList<Float> lines = new ArrayList<Float>();
        for (J_3635_s d : this.C_2741_M.h_1847_R()) {
            if (d == null || d == this || d.t_1786_h != null && !d.t_1786_h.J_1907_R(d.n_1700_B).booleanValue()) continue;
            lines.add(Float.valueOf(d.J_1907_R));
            lines.add(Float.valueOf(d.J_1907_R + d.G_564_y));
        }
        lines.add(Float.valueOf((float)screenWidth / 2.0f));
        return lines;
    }

    private List<Float> J_1907_R(int screenHeight) {
        ArrayList<Float> lines = new ArrayList<Float>();
        for (J_3635_s d : this.C_2741_M.h_1847_R()) {
            if (d == null || d == this || d.t_1786_h != null && !d.t_1786_h.J_1907_R(d.n_1700_B).booleanValue()) continue;
            lines.add(Float.valueOf(d.R_4764_Y));
            lines.add(Float.valueOf(d.R_4764_Y + d.P_1922_E));
        }
        lines.add(Float.valueOf((float)screenHeight / 2.0f));
        return lines;
    }

    private void n_1700_B(b_3528_u.G_564_y e, float mx, float my) {
        float w = this.H_2857_Y();
        float h = this.A_4115_X();
        float alpha = this.w_1457_N.n_1700_B();
        if (alpha <= 0.0f) {
            return;
        }
        F_489_x.n_1700_B(this.P_4830_p, this.h_1847_R, w, h, 2.0f, q_3148_R.n_1700_B(K_1200_E.C_2741_M), alpha);
        float yPos = this.h_1847_R + 1.0f;
        for (N_4006_T el : this.Q_2552_b) {
            f_2787_O colorEl;
            R_3213_X picker;
            if (!el.n_1700_B()) continue;
            el.n_1700_B(this.P_4830_p);
            el.J_1907_R(yPos);
            el.R_4764_Y(w);
            el.n_1700_B(e.J_1907_R(), mx, my, alpha);
            if (el instanceof f_2787_O && ((picker = (colorEl = (f_2787_O)el).G_564_y()).G_564_y() || picker.M_182_A().G_564_y())) {
                picker.n_1700_B(el.u_1723_Y() + el.w_1484_f() - 7.0f);
                picker.J_1907_R(el.v_4262_N() - 3.0f);
                picker.n_1700_B(e.J_1907_R(), mx, my, alpha);
            }
            yPos += el.t_148_a();
        }
    }

    private boolean n_1700_B(float mx, float my, S_4258_d event) {
        f_2787_O colorEl;
        R_3213_X picker;
        for (N_4006_T element : this.Q_2552_b) {
            if (!(element instanceof f_2787_O) || !(picker = (colorEl = (f_2787_O)element).G_564_y()).G_564_y()) continue;
            float pickerWidth = 93.0f + (picker.w_1484_f().P_1922_E ? 0.0f : -7.0f);
            float pickerHeight = 90.0f;
            if (!F_747_P.n_1700_B(mx, my, picker.u_1723_Y() + 7.0f, picker.v_4262_N() - 2.0f, pickerWidth, pickerHeight + 1.0f)) continue;
            picker.n_1700_B(mx, my);
            this.C_2741_M.G_564_y();
            event.n_1700_B(true);
            return true;
        }
        if (F_747_P.n_1700_B(mx, my, this.P_4830_p, this.h_1847_R, this.H_2857_Y(), this.A_4115_X())) {
            if (event.J_1907_R() == 0) {
                for (N_4006_T element : this.Q_2552_b) {
                    if (!(element instanceof f_2787_O) || !(picker = (colorEl = (f_2787_O)element).G_564_y()).G_564_y() || element.n_1700_B(mx, my)) continue;
                    picker.R_4764_Y();
                }
                for (N_4006_T element : this.Q_2552_b) {
                    if (!element.n_1700_B(mx, my)) continue;
                    element.n_1700_B(mx, my, event.J_1907_R());
                    this.C_2741_M.G_564_y();
                    event.n_1700_B(true);
                    return true;
                }
            }
            if (event.J_1907_R() == 1 || event.J_1907_R() == 2) {
                event.n_1700_B(true);
                return true;
            }
        } else if (event.J_1907_R() <= 2) {
            for (N_4006_T element : this.Q_2552_b) {
                if (!(element instanceof f_2787_O) || !(picker = (colorEl = (f_2787_O)element).G_564_y()).G_564_y()) continue;
                picker.R_4764_Y();
            }
            this.s_956_w = false;
            event.n_1700_B(true);
            return true;
        }
        return false;
    }

    private float H_2857_Y() {
        float width = 75.0f;
        for (N_4006_T el : this.Q_2552_b) {
            String settingName;
            if (el instanceof h_973_D) {
                settingName = ((h_973_D)el).R_4764_Y().n_1700_B();
            } else if (el instanceof P_2295_B) {
                settingName = ((P_2295_B)el).R_4764_Y().n_1700_B();
            } else {
                if (!(el instanceof f_2787_O)) continue;
                settingName = ((f_2787_O)el).R_4764_Y().n_1700_B();
            }
            float textW = l_3370_o.J_1907_R[12].n_1700_B(settingName);
            width = Math.max(width, textW + 24.0f);
        }
        return width;
    }

    private float A_4115_X() {
        float height = 4.0f;
        for (N_4006_T el : this.Q_2552_b) {
            height += el.t_148_a();
        }
        return height;
    }

    private void R_4764_Y(b_3528_u.G_564_y e) {
        float startY;
        boolean renderAbove;
        String settingsText = "\u041f\u041a\u041c - \u0414\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u044b\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438";
        String resetText = "\u0421\u041a\u041c - \u0421\u0431\u0440\u043e\u0441\u0438\u0442\u044c \u0440\u0430\u0441\u043f\u043e\u043b\u043e\u0436\u0435\u043d\u0438\u0435";
        String ctrlText = "\u0423\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0439\u0442\u0435 CTRL \u0434\u043b\u044f \u0441\u0432\u043e\u0431\u043e\u0434\u043d\u043e\u0433\u043e \u043f\u0435\u0440\u0435\u043c\u0435\u0449\u0435\u043d\u0438\u044f";
        float lineH = l_3370_o.J_1907_R[10].h_1847_R();
        float screenW = c_3005_b.RealmsServerPing().Q_4569_t();
        float screenH = c_3005_b.RealmsServerPing().M_182_A();
        float alpha = this.multiplayerClientSuggestionProvider.n_1700_B();
        int color = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255), alpha);
        List<String> tooltipLines = this.k_2293_S ? List.of(settingsText, resetText, ctrlText) : List.of(settingsText);
        double[] widthsD = tooltipLines.stream().mapToDouble(text -> l_3370_o.J_1907_R[10].n_1700_B((String)text)).toArray();
        float[] widths = new float[widthsD.length];
        for (int i = 0; i < widthsD.length; ++i) {
            widths[i] = (float)widthsD[i];
        }
        float totalHeight = (float)tooltipLines.size() * lineH + (float)Math.max(0, tooltipLines.size() - 1) * 2.5f;
        if (this.R_4764_Y >= totalHeight + 4.0f) {
            renderAbove = true;
        } else if (screenH - (this.R_4764_Y + this.P_1922_E) >= totalHeight + 2.5f) {
            renderAbove = false;
        } else {
            boolean bl = renderAbove = this.R_4764_Y - 1.0f > screenH - (this.R_4764_Y + this.P_1922_E) - 2.5f;
        }
        if (renderAbove) {
            startY = this.R_4764_Y - totalHeight;
            if (startY < 0.0f) {
                startY = 0.0f;
            }
        } else {
            startY = this.R_4764_Y + this.P_1922_E + 3.0f;
            if (startY + totalHeight > screenH) {
                startY = screenH - totalHeight;
            }
        }
        boolean right = (double)this.J_1907_R + Arrays.stream(widthsD).max().orElse(0.0) + 2.0 < (double)screenW;
        float leftX = this.J_1907_R + 2.0f;
        float rightX = this.J_1907_R + this.G_564_y - 2.0f;
        for (int i = 0; i < tooltipLines.size(); ++i) {
            float yPos = startY + (float)i * (lineH + 2.5f);
            if (yPos + lineH > screenH) {
                yPos = screenH - lineH;
            }
            if (yPos < 0.0f) {
                yPos = 0.0f;
            }
            float xPos = u_530_F.n_1700_B(right ? leftX : rightX - widths[i], 0.0f, screenW - widths[i]);
            l_3370_o.J_1907_R[10].n_1700_B(e.J_1907_R(), tooltipLines.get(i), xPos, yPos, color, false, true, false, true, H_2506_c.n_1700_B(H_2506_c.n_1700_B(0, 0, 0), 0.5f * alpha));
        }
    }

    @Generated
    public void n_1700_B(String name) {
        this.n_1700_B = name;
    }

    @Generated
    public void n_1700_B(float x) {
        this.J_1907_R = x;
    }

    @Generated
    public void J_1907_R(float y) {
        this.R_4764_Y = y;
    }

    @Generated
    public void R_4764_Y(float width) {
        this.G_564_y = width;
    }

    @Generated
    public void G_564_y(float height) {
        this.P_1922_E = height;
    }

    @Generated
    public void n_1700_B(boolean isDragging) {
        this.w_1484_f = isDragging;
    }

    @Generated
    public void J_1907_R(boolean isHovered) {
        this.t_148_a = isHovered;
    }

    @Generated
    public void R_4764_Y(boolean settingsVisible) {
        this.s_956_w = settingsVisible;
    }

    @Generated
    public void P_1922_E(float offsetX) {
        this.u_2550_I = offsetX;
    }

    @Generated
    public void u_1723_Y(float offsetY) {
        this.M_588_G = offsetY;
    }

    @Generated
    public void v_4262_N(float settingsX) {
        this.P_4830_p = settingsX;
    }

    @Generated
    public void w_1484_f(float settingsY) {
        this.h_1847_R = settingsY;
    }

    @Generated
    public void t_148_a(float settingsOffsetX) {
        this.Q_4569_t = settingsOffsetX;
    }

    @Generated
    public void s_956_w(float settingsOffsetY) {
        this.M_182_A = settingsOffsetY;
    }

    @Generated
    public void u_2550_I(float lastX) {
        this.q_2307_F = lastX;
    }

    @Generated
    public void M_588_G(float lastY) {
        this.Z_875_P = lastY;
    }

    @Generated
    public String n_1700_B() {
        return this.n_1700_B;
    }

    @Generated
    public float J_1907_R() {
        return this.J_1907_R;
    }

    @Generated
    public float R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public float G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public float P_1922_E() {
        return this.P_1922_E;
    }

    @Generated
    public float u_1723_Y() {
        return this.u_1723_Y;
    }

    @Generated
    public float v_4262_N() {
        return this.v_4262_N;
    }

    @Generated
    public boolean w_1484_f() {
        return this.w_1484_f;
    }

    @Generated
    public boolean t_148_a() {
        return this.t_148_a;
    }

    @Generated
    public boolean s_956_w() {
        return this.s_956_w;
    }

    @Generated
    public float u_2550_I() {
        return this.u_2550_I;
    }

    @Generated
    public float M_588_G() {
        return this.M_588_G;
    }

    @Generated
    public float P_4830_p() {
        return this.P_4830_p;
    }

    @Generated
    public float h_1847_R() {
        return this.h_1847_R;
    }

    @Generated
    public float Q_4569_t() {
        return this.Q_4569_t;
    }

    @Generated
    public float M_182_A() {
        return this.M_182_A;
    }

    @Generated
    public MultiBooleanSetting t_1786_h() {
        return this.t_1786_h;
    }

    @Generated
    public Animation multiplayerClientSuggestionProvider() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Generated
    public Animation w_1457_N() {
        return this.w_1457_N;
    }

    @Generated
    public List<Setting<?>> Y_601_j() {
        return this.Y_259_p;
    }

    @Generated
    public List<N_4006_T> Y_259_p() {
        return this.Q_2552_b;
    }

    @Generated
    public o_82_k Q_2552_b() {
        return this.C_2741_M;
    }

    @Generated
    public boolean C_2741_M() {
        return this.k_2293_S;
    }

    @Generated
    public float k_2293_S() {
        return this.q_2307_F;
    }

    @Generated
    public float q_2307_F() {
        return this.Z_875_P;
    }

    @Generated
    public List<Float> Z_875_P() {
        return this.t_4043_B;
    }

    @Generated
    public List<Float> c_3005_b() {
        return this.x_607_J;
    }

    private record n_1700_B(float n_1700_B, float J_1907_R, float R_4764_Y) {
        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{n_1700_B.class, "position;distance;linePos", "n_1700_B", "J_1907_R", "R_4764_Y"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{n_1700_B.class, "position;distance;linePos", "n_1700_B", "J_1907_R", "R_4764_Y"}, this);
        }

        @Override
        public final boolean equals(Object o) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{n_1700_B.class, "position;distance;linePos", "n_1700_B", "J_1907_R", "R_4764_Y"}, this, o);
        }
    }
}



