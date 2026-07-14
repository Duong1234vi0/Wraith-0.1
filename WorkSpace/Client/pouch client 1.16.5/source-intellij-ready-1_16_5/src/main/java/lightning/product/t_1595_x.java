/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2714_y;
import lightning.product.AxeItem;
import lightning.product.F_1573_j;
import lightning.product.NumberSetting;
import lightning.product.J_2061_p;
import lightning.product.M_1336_P;
import lightning.product.Module;
import lightning.product.X_3955_y;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.DiggerItem;
import lightning.product.g_221_o;
import lightning.product.h_1015_G;
import lightning.product.i_789_Q;
import lightning.product.k_4231_L;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.AttackAura;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class t_1595_x
extends Module {
    public final ModeSetting v_4262_N = new ModeSetting("\u041c\u043e\u0434", "\u041c\u043e\u0434 1", "\u041c\u043e\u0434 1", "\u041c\u043e\u0434 2", "\u041c\u043e\u0434 3", "\u041c\u043e\u0434 4", "\u041c\u043e\u0434 5", "\u041c\u043e\u0434 6", "\u041c\u043e\u0434 7", "360", "Slant", "New", "Fade", "SpinSlash");
    public final ModeSetting w_1484_f = new ModeSetting("\u0420\u0430\u0431\u043e\u0442\u0430\u0442\u044c \u043d\u0430", "\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430", "\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430", "\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430");
    public final NumberSetting t_148_a = new NumberSetting("\u0423\u0433\u043e\u043b", 100.0f, 0.0f, 360.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u041c\u043e\u0434 2") || this.v_4262_N.J_1907_R("\u041c\u043e\u0434 4"));
    public final NumberSetting s_956_w = new NumberSetting("\u0421\u0438\u043b\u0430 \u0432\u0437\u043c\u0430\u0445\u0430", 8.0f, 1.0f, 10.0f, 1.0f, () -> this.v_4262_N.J_1907_R("\u041c\u043e\u0434 1") || this.v_4262_N.J_1907_R("\u041c\u043e\u0434 2") || this.v_4262_N.J_1907_R("\u041c\u043e\u0434 3") || this.v_4262_N.J_1907_R("\u041c\u043e\u0434 4") || this.v_4262_N.J_1907_R("\u041c\u043e\u0434 6"));
    public final NumberSetting u_2550_I = new NumberSetting("\u0421\u0438\u043b\u0430 \u0432\u0437\u043c\u0430\u0445\u0430 \u0432\u043d\u0438\u0437", 0.0f, 0.0f, 1.0f, 0.1f);
    public final NumberSetting M_588_G = new NumberSetting("\u041f\u043b\u0430\u0432\u043d\u043e\u0441\u0442\u044c \u0432\u0437\u043c\u0430\u0445\u0430", 11.0f, 1.0f, 20.0f, 1.0f);
    public final BooleanSetting P_4830_p = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u0430\u043a\u0442\u0438\u0432\u043d\u043e\u0439 AttackAura", false);
    public final BooleanSetting h_1847_R = new BooleanSetting("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430", false, () -> this.v_4262_N.J_1907_R("New"));
    private boolean Q_4569_t;
    private boolean M_182_A;
    private String t_1786_h = "\u041f\u0440\u0430\u0432\u0430\u044f \u0440\u0443\u043a\u0430";
    private k_4231_L multiplayerClientSuggestionProvider = k_4231_L.J_1907_R;

    public t_1595_x() {
        super("Sword Animations", "\u041f\u043e\u0437\u0432\u043e\u043b\u044f\u0435\u0442 \u0438\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u044e \u0443\u0434\u0430\u0440\u0430", ModuleCategory.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.M_588_G, this.u_2550_I, this.h_1847_R, this.P_4830_p);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (t_1595_x.c_3005_b.P_4830_p != null) {
            this.multiplayerClientSuggestionProvider = t_1595_x.c_3005_b.P_4830_p.multiplayerClientSuggestionProvider;
        }
        this.h_1847_R();
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        if (t_1595_x.c_3005_b.P_4830_p != null) {
            t_1595_x.c_3005_b.P_4830_p.multiplayerClientSuggestionProvider = this.multiplayerClientSuggestionProvider;
            if (t_1595_x.c_3005_b.Y_259_p != null) {
                t_1595_x.c_3005_b.P_4830_p.R_4764_Y();
            }
        }
    }

    private void h_1847_R() {
        if (t_1595_x.c_3005_b.P_4830_p != null) {
            k_4231_L newHand;
            t_1595_x.c_3005_b.P_4830_p.multiplayerClientSuggestionProvider = newHand = this.w_1484_f.J_1907_R("\u041b\u0435\u0432\u0430\u044f \u0440\u0443\u043a\u0430") ? k_4231_L.n_1700_B : k_4231_L.J_1907_R;
            if (t_1595_x.c_3005_b.Y_259_p != null) {
                t_1595_x.c_3005_b.P_4830_p.R_4764_Y();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G eventUpdate) {
        String currentHand = (String)this.w_1484_f.J_1907_R();
        if (!currentHand.equals(this.t_1786_h)) {
            this.t_1786_h = currentHand;
            this.h_1847_R();
        }
    }

    private float n_1700_B(float x) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;
        if (x < 0.5f) {
            return (float)(Math.pow(2.0f * x, 2.0) * (double)((c2 + 1.0f) * 2.0f * x - c2) / 2.0);
        }
        return (float)((Math.pow(2.0f * x - 2.0f, 2.0) * (double)((c2 + 1.0f) * (x * 2.0f - 2.0f) + c2) + 2.0) / 2.0);
    }

    private void J_1907_R(float swingProgress) {
        boolean swinging;
        boolean bl = swinging = swingProgress > 0.01f;
        if (swinging && !this.M_182_A) {
            this.Q_4569_t = !this.Q_4569_t;
        }
        this.M_182_A = swinging;
    }

    private boolean n_1700_B(i_789_Q event, boolean isLeft) {
        boolean primarySlash;
        Z_1993_T stack = event.J_1907_R().R_4764_Y(event.G_564_y());
        if (stack.n_1700_B()) {
            return false;
        }
        this.J_1907_R(event.R_4764_Y());
        g_221_o matrixStack = event.P_1922_E();
        float direction = isLeft ? -1.0f : 1.0f;
        float swingProgress = event.R_4764_Y();
        float swingRot = swingProgress < 0.6f ? u_530_F.n_1700_B(u_530_F.n_1700_B(swingProgress, 0.0f, 0.12506f) * 12.56f) : u_530_F.n_1700_B(u_530_F.n_1700_B(swingProgress, 0.62532f, 0.75038f) * 12.56f);
        float swing = this.n_1700_B(u_530_F.n_1700_B(swingProgress * (float)Math.PI));
        boolean sword = stack.J_1907_R() instanceof SwordItem;
        boolean axe = stack.J_1907_R() instanceof AxeItem;
        boolean shovel = stack.J_1907_R() instanceof X_3955_y;
        boolean tool = stack.J_1907_R() instanceof DiggerItem;
        boolean spear = stack.M_588_G() == F_1573_j.u_1723_Y;
        boolean block = stack.M_588_G() == F_1573_j.G_564_y;
        boolean bl = primarySlash = this.Q_4569_t || axe || spear || block;
        if (primarySlash && !shovel) {
            if (sword || axe) {
                matrixStack.n_1700_B((double)(0.8f * direction * swingRot), (double)(0.3f * swingRot), (double)(-0.5f * swing));
                matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(15.0f * swingRot * direction));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-20.0f * swingRot));
                matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-70.0f * swingRot * direction));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y((sword ? 40.0f : 30.0f) * swing));
                return true;
            }
            if (spear) {
                matrixStack.n_1700_B(0.0, 0.0, 0.45 * (double)swingRot);
                matrixStack.n_1700_B(-0.25 * (double)direction * (double)swing, -0.35 * (double)swingRot, -0.6 * (double)swing);
                matrixStack.n_1700_B(0.0, 0.1 * (double)swing, 0.0);
                matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(15.0f * swingRot * direction));
                matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(30.0f * swingRot * direction));
                return true;
            }
            if (tool && !block) {
                matrixStack.n_1700_B((double)(0.1f * direction * swingRot), (double)(0.1f * swingRot), (double)(-0.5f * swing));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-30.0f * swingRot));
                matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-20.0f * swingRot * direction));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(40.0f * swing));
                return true;
            }
            if (!block) {
                matrixStack.n_1700_B((double)(0.1f * direction * swingRot), (double)(0.1f * swingRot), (double)(-0.1f * swing));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-30.0f * swingRot));
                matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-10.0f * swingRot * direction));
                matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(40.0f * swing));
                matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(10.0f * swing * direction));
                return true;
            }
            matrixStack.n_1700_B((double)(0.1f * direction * swingRot), (double)(0.1f * swingRot), (double)(-0.2f * swing));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-10.0f * swingRot));
            matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-10.0f * swingRot * direction));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(20.0f * swing));
            return true;
        }
        if (shovel) {
            matrixStack.n_1700_B(0.0, 0.15 * (double)swingRot, -0.25 * (double)swingRot);
            matrixStack.n_1700_B(0.0, 0.0, -0.2 * (double)swing);
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(15.0f * swingRot));
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-35.0f * swingRot));
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(30.0f * swing));
            return true;
        }
        if (sword) {
            matrixStack.n_1700_B((double)(-0.55f * direction * swingRot), (double)(-0.8f * swingRot), (double)(-0.77f * swing));
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(5.0f * swingRot * direction));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-30.0f * swingRot));
            matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(70.0f * swingRot * direction));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(50.0f * swing));
            return true;
        }
        if (tool) {
            matrixStack.n_1700_B((double)(0.1f * direction * swingRot), (double)(0.1f * swingRot), (double)(-0.5f * swing));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-30.0f * swingRot));
            matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-20.0f * swingRot * direction));
            matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(40.0f * swing));
            return true;
        }
        matrixStack.n_1700_B((double)(0.1f * direction * swingRot), (double)(0.1f * swingRot), (double)(-0.1f * swing));
        matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(-30.0f * swingRot));
        matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-10.0f * swingRot * direction));
        matrixStack.n_1700_B(M_1336_P.n_1700_B.R_4764_Y(40.0f * swing));
        matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(10.0f * swing * direction));
        return true;
    }

    @Y_1740_V
    public void n_1700_B(i_789_Q event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R();
        if (this.P_4830_p.t_148_a().booleanValue() && aura.v_4262_N == null) {
            return;
        }
        g_221_o matrixStack = event.P_1922_E();
        float anim = u_530_F.n_1700_B(u_530_F.R_4764_Y(event.R_4764_Y()) * (float)Math.PI);
        String mode = (String)this.v_4262_N.J_1907_R();
        boolean shouldCancel = true;
        if (event.G_564_y() == x_1688_C.n_1700_B) {
            boolean isLeft = event.J_1907_R().d_2169_p() == k_4231_L.n_1700_B;
            switch (mode) {
                case "\u041c\u043e\u0434 1": {
                    float swingSqrt = u_530_F.R_4764_Y(event.R_4764_Y());
                    float direction = isLeft ? -1.0f : 1.0f;
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * (20.0f + u_530_F.n_1700_B(event.R_4764_Y() * event.R_4764_Y() * (float)Math.PI) / 4.0f * -10.0f)));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * u_530_F.n_1700_B(swingSqrt * (float)Math.PI) * -20.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(u_530_F.n_1700_B(swingSqrt * (float)Math.PI) * -((Float)this.s_956_w.J_1907_R()).floatValue() * 10.0f));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-direction * 45.0f));
                    break;
                }
                case "\u041c\u043e\u0434 2": {
                    matrixStack.n_1700_B(0.0, 0.15, (double)-0.3f);
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(isLeft ? -90.0f : 90.0f));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(isLeft ? 60.0f : -60.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-((Float)this.t_148_a.J_1907_R()).floatValue() - ((Float)this.s_956_w.J_1907_R()).floatValue() * 10.0f * anim));
                    break;
                }
                case "\u041c\u043e\u0434 3": {
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(event.R_4764_Y() * (float)Math.PI - ((Float)this.s_956_w.J_1907_R()).floatValue() * 10.0f * anim));
                    break;
                }
                case "\u041c\u043e\u0434 4": {
                    matrixStack.n_1700_B(0.0, 0.15, (double)-0.3f);
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(isLeft ? -70.0f : 70.0f));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(isLeft ? 30.0f : -30.0f));
                    matrixStack.n_1700_B(0.9f, 0.9f, 0.9f);
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-((Float)this.t_148_a.J_1907_R()).floatValue() - ((Float)this.s_956_w.J_1907_R()).floatValue() * 10.0f * anim));
                    break;
                }
                case "\u041c\u043e\u0434 5": {
                    float direction = isLeft ? -1.0f : 1.0f;
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * 45.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(anim * -20.0f));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * anim * -20.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(anim * -80.0f));
                    matrixStack.n_1700_B((double)(direction * 0.4f), (double)0.2f, (double)0.2f);
                    matrixStack.n_1700_B((double)(direction * -0.5f), (double)0.08f, 0.0);
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * 20.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-80.0f));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * 20.0f));
                    break;
                }
                case "\u041c\u043e\u0434 7": {
                    float direction = isLeft ? -1.0f : 1.0f;
                    matrixStack.n_1700_B(1.0f, 1.0f, 1.0f);
                    matrixStack.n_1700_B((double)(direction * (0.4f - anim * 0.3f)), 0.0, (double)(-0.0f - anim * 0.2f));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * 90.0f));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * -30.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-70.0f - 30.0f * anim));
                    break;
                }
                case "360": {
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-360.0f * event.R_4764_Y()));
                    break;
                }
                case "SpinSlash": {
                    float direction = isLeft ? -1.0f : 1.0f;
                    float swing = event.R_4764_Y();
                    float wave = u_530_F.n_1700_B(swing * (float)Math.PI);
                    matrixStack.n_1700_B((double)(direction * 0.15f), (double)(-0.1f * wave), (double)(-0.35f * wave));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * (110.0f * wave)));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * (-65.0f * wave)));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-95.0f * wave));
                    break;
                }
                case "Stab": {
                    float direction = isLeft ? -1.0f : 1.0f;
                    float swing = event.R_4764_Y();
                    float push = u_530_F.n_1700_B(u_530_F.R_4764_Y(swing) * (float)Math.PI);
                    matrixStack.n_1700_B((double)(direction * 0.05f), (double)(0.02f - 0.05f * push), (double)(-0.7f * push));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * (16.0f - 24.0f * push)));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-22.0f * push));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * -8.0f * push));
                    break;
                }
                case "Fade": {
                    float swingProgress = event.R_4764_Y();
                    float direction = isLeft ? -1.0f : 1.0f;
                    float f2 = u_530_F.n_1700_B(swingProgress * swingProgress * (float)Math.PI);
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * (45.0f + f2 * -5.0f)));
                    float f13 = u_530_F.n_1700_B(u_530_F.R_4764_Y(swingProgress * swingProgress) * (float)Math.PI);
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(direction * f13 * -20.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f13 * -(((Float)this.s_956_w.J_1907_R()).floatValue() * 8.0f)));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(direction * -45.0f));
                    break;
                }
                case "\u041c\u043e\u0434 6": {
                    float i = isLeft ? -1.0f : 1.0f;
                    float f = u_530_F.n_1700_B(event.R_4764_Y() * event.R_4764_Y() * (float)Math.PI);
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(i * (45.0f + f * -20.0f)));
                    float f1 = u_530_F.n_1700_B(u_530_F.R_4764_Y(event.R_4764_Y()) * (float)Math.PI);
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(i * f1 * -20.0f));
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(f1 * -((Float)this.s_956_w.J_1907_R()).floatValue() * 10.0f));
                    matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(i * -45.0f));
                    break;
                }
                case "Slant": {
                    float rotate = 35.0f;
                    matrixStack.n_1700_B(0.0, 0.0, -0.3 * (double)anim);
                    matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(anim * -rotate));
                    matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(anim * rotate));
                    break;
                }
                case "New": {
                    if (this.n_1700_B(event, isLeft)) break;
                    shouldCancel = false;
                }
            }
        }
        if (shouldCancel) {
            event.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(J_2061_p event) {
        if (event.R_4764_Y() != x_1688_C.n_1700_B) {
            return;
        }
        event.n_1700_B(((Float)this.M_588_G.J_1907_R()).intValue());
        event.n_1700_B(true);
    }

    @Y_1740_V
    public void n_1700_B(A_2714_y event) {
        if (event.n_1700_B() != t_1595_x.c_3005_b.Y_259_p.d_2169_p()) {
            return;
        }
        if ("New".equals(this.v_4262_N.J_1907_R()) && t_1595_x.c_3005_b.Y_259_p.R_4764_Y(x_1688_C.n_1700_B).n_1700_B()) {
            return;
        }
        event.n_1700_B(((Float)this.u_2550_I.J_1907_R()).floatValue());
    }
}



