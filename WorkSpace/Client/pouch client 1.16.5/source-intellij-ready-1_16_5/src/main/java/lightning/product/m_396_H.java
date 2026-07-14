/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Z_2491_A;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModuleCategory;

public class m_396_H
implements ServerHandshakePacketListener {
    public static final MultiBooleanSetting n_1700_B = new MultiBooleanSetting("\u041a\u0430\u0442\u0435\u0433\u043e\u0440\u0438\u0438", new BooleanSetting("Combat", true), new BooleanSetting("Movement", true), new BooleanSetting("Visuals", true), new BooleanSetting("Player", true), new BooleanSetting("Misc", true));
    private final J_3635_s J_1907_R;
    private final Map<Module, Animation[]> R_4764_Y = new HashMap<Module, Animation[]>();
    private final Map<Module, Float> G_564_y = new HashMap<Module, Float>();
    private final List<Module> P_1922_E = new ArrayList<Module>();
    private final List<Module> u_1723_Y = new ArrayList<Module>();
    private final List<Module> v_4262_N = new ArrayList<Module>();
    private final Map<Module, Boolean> w_1484_f = new HashMap<Module, Boolean>();
    private float[] t_148_a = new float[0];
    private float[] s_956_w = new float[0];
    private int u_2550_I;
    private boolean M_588_G = true;

    public m_396_H(J_3635_s dragging) {
        this.J_1907_R = dragging;
    }

    @Override
    public void n_1700_B(b_3528_u event) {
        Module module;
        int i;
        g_221_o stack = event.J_1907_R();
        int textColor = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        int bgColor = q_3148_R.n_1700_B(K_1200_E.C_2741_M);
        int outlineColor = q_3148_R.n_1700_B(K_1200_E.h_1847_R);
        int glowColor = q_3148_R.n_1700_B(K_1200_E.q_2307_F);
        int accentA = q_3148_R.n_1700_B(K_1200_E.w_1457_N);
        float padding = 4.0f;
        float textYOffset = 1.5f;
        float rowHeight = 12.0f;
        this.n_1700_B(padding);
        float targetY = 0.0f;
        for (Module x_3546_T : this.P_1922_E) {
            float currentTargetY = targetY;
            Animation[] anims = this.R_4764_Y.computeIfAbsent(x_3546_T, k -> new Animation[]{new Animation(-15.0f, 10.0f), new Animation(currentTargetY, 10.0f)});
            anims[0].n_1700_B(0.0f);
            anims[1].n_1700_B(targetY);
            targetY += rowHeight;
        }
        this.v_4262_N.clear();
        for (Map.Entry entry : this.R_4764_Y.entrySet()) {
            if (this.w_1484_f.getOrDefault(entry.getKey(), false).booleanValue()) continue;
            ((Animation[])entry.getValue())[0].n_1700_B(-15.0f);
            if (!((Animation[])entry.getValue())[0].R_4764_Y()) continue;
            this.v_4262_N.add((Module)entry.getKey());
        }
        for (Module x_3546_T : this.v_4262_N) {
            this.R_4764_Y.remove(x_3546_T);
        }
        this.u_1723_Y.clear();
        this.u_1723_Y.addAll(this.R_4764_Y.keySet());
        if (this.u_1723_Y.isEmpty()) {
            this.J_1907_R.R_4764_Y(90.0f);
            this.J_1907_R.G_564_y(12.0f);
            return;
        }
        this.u_1723_Y.sort(Comparator.comparingDouble(m -> this.R_4764_Y.get(m)[1].n_1700_B()));
        float screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        float f = c_3005_b.RealmsServerPing().M_182_A();
        float dragX = this.J_1907_R.J_1907_R();
        float dragY = this.J_1907_R.R_4764_Y();
        boolean leftSide = dragX < screenWidth / 2.0f;
        float maxWidth = 0.0f;
        for (Module module3 : this.u_1723_Y) {
            float w = this.n_1700_B(module3, padding);
            if (!(w > maxWidth)) continue;
            maxWidth = w;
        }
        if (dragX <= 0.0f) {
            dragX = screenWidth - maxWidth - 3.0f;
        }
        dragX = Math.max(0.0f, Math.min(dragX, screenWidth - maxWidth));
        dragY = Math.max(3.0f, Math.min(dragY, f - (float)this.u_1723_Y.size() * rowHeight));
        this.J_1907_R.n_1700_B(dragX);
        this.J_1907_R.J_1907_R(dragY);
        float radius = 3.0f;
        int last = this.u_1723_Y.size() - 1;
        this.n_1700_B(this.u_1723_Y.size());
        for (i = 0; i < this.u_1723_Y.size(); ++i) {
            float x;
            module = this.u_1723_Y.get(i);
            float width = this.n_1700_B(module, padding);
            Animation[] anims = this.R_4764_Y.get(module);
            float slide = anims[0].n_1700_B();
            float y = dragY + anims[1].n_1700_B();
            this.t_148_a[i] = x = leftSide ? dragX + slide : dragX + maxWidth - width - slide;
            this.s_956_w[i] = y;
            boolean hasBelow = i < last;
            float topLeft = i == 0 ? radius : 0.0f;
            float bottomLeft = hasBelow ? 0.0f : radius;
            float topRight = i == 0 ? radius : 0.0f;
            float bottomRight = 3.0f;
            Z_2491_A rad = new Z_2491_A(topLeft, bottomLeft, topRight, bottomRight);
            if (!leftSide) {
                rad = new Z_2491_A(topRight, bottomRight, topLeft, bottomLeft);
            }
            float drawY = y - 1.0f;
            float drawH = rowHeight + 2.0f;
            float glowAlpha = (float)H_2506_c.G_564_y(glowColor) / 255.0f;
            F_489_x.n_1700_B(x - 1.0f, drawY - 1.0f, width + 2.0f, drawH + 2.0f, radius, glowColor, glowColor, glowColor, glowColor, glowAlpha * 0.45f, 6.0f);
            F_489_x.n_1700_B(x, drawY, width, drawH, rad, bgColor, 1.0f);
            F_489_x.J_1907_R(x, drawY, width, drawH, radius, outlineColor, H_2506_c.G_564_y(outlineColor));
        }
        for (i = 0; i < this.u_1723_Y.size(); ++i) {
            module = this.u_1723_Y.get(i);
            float textX = this.t_148_a[i] + padding;
            int perRowText = H_2506_c.n_1700_B(textColor, accentA, 0.15f);
            l_3370_o.J_1907_R[13].n_1700_B(stack, module.G_564_y(), (double)textX, (double)(this.s_956_w[i] - textYOffset + rowHeight / 2.0f + 0.5f), perRowText);
        }
        this.J_1907_R.R_4764_Y(maxWidth);
        this.J_1907_R.G_564_y((float)this.u_1723_Y.size() * rowHeight);
    }

    private void n_1700_B(float padding) {
        int hash = 1;
        List<Module> modules = ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y();
        for (Module module : modules) {
            if (module == null) continue;
            boolean visible = module.w_1484_f() && this.n_1700_B(module.u_1723_Y());
            hash = 31 * hash + System.identityHashCode(module);
            hash = 31 * hash + (visible ? 1 : 0);
            Boolean lastVisible = this.w_1484_f.put(module, visible);
            if (lastVisible != null && lastVisible == visible) continue;
            this.M_588_G = true;
        }
        if (!this.M_588_G && hash == this.u_2550_I) {
            return;
        }
        this.P_1922_E.clear();
        for (Module module : modules) {
            if (module == null || !this.w_1484_f.getOrDefault(module, false).booleanValue()) continue;
            this.P_1922_E.add(module);
            this.n_1700_B(module, padding);
        }
        this.P_1922_E.sort(Comparator.comparingDouble(m -> -this.n_1700_B((Module)m, padding)));
        this.u_2550_I = hash;
        this.M_588_G = false;
    }

    private float n_1700_B(Module module, float padding) {
        return this.G_564_y.computeIfAbsent(module, m -> Float.valueOf(l_3370_o.J_1907_R[13].n_1700_B(m.G_564_y()) + padding * 2.0f)).floatValue();
    }

    private void n_1700_B(int size) {
        if (this.t_148_a.length < size) {
            this.t_148_a = new float[size];
            this.s_956_w = new float[size];
        }
    }

    private boolean n_1700_B(ModuleCategory category) {
        if (category == null) {
            return true;
        }
        Boolean visible = n_1700_B.J_1907_R(category.name());
        return visible == null || visible != false;
    }
}



